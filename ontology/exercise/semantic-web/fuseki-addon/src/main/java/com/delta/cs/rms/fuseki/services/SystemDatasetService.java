package com.delta.cs.rms.fuseki.services;

import com.delta.cs.rms.fuseki.helper.Fuseki2Utils;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import static org.apache.jena.fuseki.Fuseki.serverLog;

import org.apache.jena.fuseki.server.DataAccessPoint;
import org.apache.jena.fuseki.server.DataAccessPointRegistry;
import org.apache.jena.fuseki.server.DataService;
import org.apache.jena.fuseki.server.FusekiEnv;
import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.riot.RDFWriterRegistry;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.tdb.transaction.DatasetGraphTransaction;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 *
 * @author tzuyichao
 */
public class SystemDatasetService {

    public final static String TYPE_ANY = "any";

    public final static String system_dataset_key = "/system";
    public final static String system_dataset = "system";

    private static final String kawari_prefix = "http://delta.com/it/kawari#";
    private static final String PREDICATE_GRAPH = "http://delta.com/it/kawari#hasGraph";
    private Node node_hasGraph;

    private static final SystemDatasetService instance = new SystemDatasetService();

    private SystemDatasetService() {
        node_hasGraph = NodeFactory.createURI(PREDICATE_GRAPH);
    }

    public static SystemDatasetService getInstance() {
        if (isSystemDatasetExist()) {
            return instance;
        } else {
            throw new IllegalStateException("system dataset doesnot exist");
        }
    }

    public static boolean isSystemDatasetExist() {
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        return registry.isRegistered(system_dataset_key);
    }

    /**
     *
     * @param clientId
     * @param type
     * @param dataset
     * @throws IllegalArgumentException 如果有一個參數是null或空字串就會收到這個例外
     */
    public void addRecord(String clientId, String type, String dataset) {
        if (StringUtils.isBlank(clientId) || StringUtils.isBlank(type) || StringUtils.isBlank(dataset)) {
            throw new IllegalArgumentException("all arguments are required");
        }
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        DataAccessPoint systemDataAccessPoint = registry.get(system_dataset_key);
        DataService ds = systemDataAccessPoint.getDataService();

        try {
            ds.startTxn(ReadWrite.WRITE);

            DatasetGraph datasetGraph = ds.getDataset();
            //Resource client = defaultModel.createResource(kawari_prefix.concat(id));
            //Property hasGraph = defaultModel.createProperty(PREDICATE_GRAPH);
            Node nodeClientId = NodeFactory.createURI(kawari_prefix.concat(clientId));
            Node nodeDataset = NodeFactory.createLiteral(dataset);

            if (datasetGraph instanceof DatasetGraphTransaction) {
                DatasetGraphTransaction dsgtxn = (DatasetGraphTransaction) datasetGraph;
                try {
                    dsgtxn.begin(ReadWrite.WRITE);
                    dsgtxn.getDefaultGraph().add(Triple.create(nodeClientId, node_hasGraph, nodeDataset));
                } finally {
                    dsgtxn.commit();
                }
            } else {
                datasetGraph.getDefaultGraph().add(Triple.create(nodeClientId, node_hasGraph, nodeDataset));
            }
        } finally {
            ds.finishTxn(ReadWrite.WRITE);
        }
    }

    /**
     * 從system dataset查詢clientid的dataset清單
     *
     * @param clientId
     * @param type
     * @return
     */
    public String[] listDataset(String clientId, String type) {
        if (StringUtils.isBlank(clientId) || StringUtils.isBlank(type)) {
            throw new IllegalArgumentException("all arguments are required");
        }
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        DataAccessPoint systemDataAccessPoint = registry.get(system_dataset_key);
        DataService ds = systemDataAccessPoint.getDataService();

        try {
            ds.startTxn(ReadWrite.READ);

            DatasetGraph datasetGraph = ds.getDataset();
            Node nodeClientId = NodeFactory.createURI(kawari_prefix.concat(clientId));

            if (datasetGraph instanceof DatasetGraphTransaction) {
                DatasetGraphTransaction dsgtxn = (DatasetGraphTransaction) datasetGraph;
                try {
                    dsgtxn.begin(ReadWrite.READ);
                    ExtendedIterator<Triple> tripleIter = dsgtxn.getDefaultGraph().find(nodeClientId, node_hasGraph, Node.ANY); //.add(Triple.create(nodeClientId, node_hasGraph, nodeDataset));
                    return convertExtendedIteratorObjectToArray(tripleIter);
                } finally {
                    dsgtxn.commit();
                }
            } else {
                ExtendedIterator<Triple> tripleIter = datasetGraph.getDefaultGraph().find(nodeClientId, node_hasGraph, Node.ANY);
                return convertExtendedIteratorObjectToArray(tripleIter);
            }
        } finally {
            ds.finishTxn(ReadWrite.READ);
        }
    }

    /**
     * 刪除dataset
     *
     * 1. shutdown dataset 為了讓tdb lock release from fuseki VM 2. mv assembler to
     * $FUSEKI_BASE/recyclebin 3. remove triple from /system dataset
     *
     * @param clientId
     * @param datasetId
     */
    public void dropDataset(String datasetId) {
        if (StringUtils.isBlank(datasetId)) {
            throw new IllegalArgumentException("datasetId is required");
        }
        String dbName = "/".concat(datasetId);
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        if (registry.isRegistered(dbName)) {
            // shutdown dataset
            if (shutdownDataset(dbName)) {
                // remove registry
                registry.remove(dbName);
                // move assembler, data to recyclebin
                Path fuseki_base = FusekiEnv.FUSEKI_BASE;
                Path recyclebin = fuseki_base.resolve("recyclebin");
                if (!recyclebin.toFile().exists()) {
                    serverLog.info("Create recyclebin folder");
                    recyclebin.toFile().mkdirs();
                }
                Path ttl = fuseki_base.resolve("configuration").resolve(datasetId.concat(".ttl"));
                if (ttl.toFile().exists()) {
                    try {
                        Files.move(ttl, recyclebin.resolve(datasetId.concat(".ttl")), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        serverLog.error(e.getMessage());
                    }
                } else {
                    // should not happen
                    serverLog.warn(ttl.toString() + " does not exist?!");
                }
                // delete /system record
                String[] clientIds = findClientIdByDatasetId(datasetId);
                registry = DataAccessPointRegistry.get();
                DataAccessPoint systemDataAccessPoint = registry.get(system_dataset_key);
                DataService ds = systemDataAccessPoint.getDataService();

                try {
                    ds.startTxn(ReadWrite.WRITE);

                    DatasetGraph datasetGraph = ds.getDataset();
                    for (String clientId : clientIds) {
                        Node nodeClientId = NodeFactory.createURI(kawari_prefix.concat(clientId));
                        Node nodeDataset = NodeFactory.createLiteral(datasetId);

                        if (datasetGraph instanceof DatasetGraphTransaction) {
                            DatasetGraphTransaction dsgtxn = (DatasetGraphTransaction) datasetGraph;
                            try {
                                dsgtxn.begin(ReadWrite.WRITE);
                                dsgtxn.getDefaultGraph().delete(Triple.create(nodeClientId, node_hasGraph, nodeDataset));
                            } finally {
                                dsgtxn.commit();
                            }
                        } else {
                            datasetGraph.getDefaultGraph().delete(Triple.create(nodeClientId, node_hasGraph, nodeDataset));
                        }
                    }
                } finally {
                    ds.finishTxn(ReadWrite.WRITE);
                }
            }
        }
    }

    /**
     * 取得/system dataset中所有的client id
     *
     * @return
     */
    public String[] listClientId() {
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        DataAccessPoint systemDataAccessPoint = registry.get(system_dataset_key);
        DataService ds = systemDataAccessPoint.getDataService();

        try {
            ds.startTxn(ReadWrite.READ);

            DatasetGraph datasetGraph = ds.getDataset();

            if (datasetGraph instanceof DatasetGraphTransaction) {
                DatasetGraphTransaction dsgtxn = (DatasetGraphTransaction) datasetGraph;
                try {
                    dsgtxn.begin(ReadWrite.READ);
                    ExtendedIterator<Triple> tripleIter = dsgtxn.getDefaultGraph().find(Node.ANY, node_hasGraph, Node.ANY); //.add(Triple.create(nodeClientId, node_hasGraph, nodeDataset));
                    return extractDistinctSubject(tripleIter);
                } finally {
                    dsgtxn.commit();
                }
            } else {
                ExtendedIterator<Triple> tripleIter = datasetGraph.getDefaultGraph().find(Node.ANY, node_hasGraph, Node.ANY);
                return extractDistinctSubject(tripleIter);
            }
        } finally {
            ds.finishTxn(ReadWrite.READ);
        }
    }

    /**
     * 刪除clientId
     *
     * 同時會刪除該clientid下所有的dataset
     *
     * @param clientId
     */
    public void deleteClientId(String clientId, String type) {
        if (StringUtils.isBlank(clientId) || StringUtils.isBlank(type)) {
            throw new IllegalArgumentException("all arguments are required");
        }

        String[] datasetList = listDataset(clientId, type);
        for (String ds : datasetList) {
            serverLog.info("delete dataset: " + ds);
            if (ds != null) {
                dropDataset(ds);
            }
        }
    }

    private String[] findClientIdByDatasetId(String datasetId) {
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        DataAccessPoint systemDataAccessPoint = registry.get(system_dataset_key);
        DataService ds = systemDataAccessPoint.getDataService();

        try {
            ds.startTxn(ReadWrite.READ);

            DatasetGraph datasetGraph = ds.getDataset();
            Node nodeDataset = NodeFactory.createLiteral(datasetId);

            if (datasetGraph instanceof DatasetGraphTransaction) {
                DatasetGraphTransaction dsgtxn = (DatasetGraphTransaction) datasetGraph;
                try {
                    dsgtxn.begin(ReadWrite.READ);
                    ExtendedIterator<Triple> tripleIter = dsgtxn.getDefaultGraph().find(Node.ANY, node_hasGraph, nodeDataset); //.add(Triple.create(nodeClientId, node_hasGraph, nodeDataset));
                    return extractDistinctSubject(tripleIter);
                } finally {
                    dsgtxn.commit();
                }
            } else {
                ExtendedIterator<Triple> tripleIter = datasetGraph.getDefaultGraph().find(Node.ANY, node_hasGraph, nodeDataset);
                return extractDistinctSubject(tripleIter);
            }
        } finally {
            ds.finishTxn(ReadWrite.READ);
        }
    }

    private boolean shutdownDataset(String dbName) {
        // shutdown dataset
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        DataAccessPoint db = registry.get(dbName);
        DataService dataService = db.getDataService();
        // invoke private shutdown method of DataService
        try {
            Method shutdown = DataService.class.getDeclaredMethod("shutdown", null);
            shutdown.setAccessible(true);
            shutdown.invoke(dataService, null);
        } catch (NoSuchMethodException e) {
            // FIXME: just for test
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            // FIXME: just for test
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            // 會有這個錯，但是卻會真的release，即使fuseki running，也可以在另一個process使用jena-tdb開啟這個被fuseki lock的tdb 
            // FIXME: just for test
            e.printStackTrace();
        }
        return true;
    }

    private String[] convertExtendedIteratorObjectToArray(ExtendedIterator<Triple> tripleIter) {
        if (tripleIter != null) {
            Set<Triple> triples = tripleIter.toSet();
            String[] res = new String[triples.size()];
            int i = 0;
            for (Triple t : triples) {
                res[i] = t.getObject().getLiteralValue().toString();
                i++;
            }
            Arrays.sort(res);
            return res;
        } else {
            return null;
        }
    }

    private String[] extractDistinctSubject(ExtendedIterator<Triple> tripleIter) {
        if (tripleIter != null) {
            Set<Triple> triples = tripleIter.toSet();
            Set<String> res = new HashSet();
            triples.stream().forEach((Triple t) -> {
                res.add(t.getSubject().getURI().replace(kawari_prefix, ""));
            });
            return res.toArray(new String[0]);
        } else {
            return null;
        }
    }

    /**
     * 由DatasetId的dataset取得named graph內容
     *
     * @param datasetId dataset id
     * @param named named graph name
     * @param lang 回傳格式
     * @return
     */
    public String retrievalNamedGraph(String datasetId, String named, Lang lang) {
        Objects.requireNonNull(datasetId);
        Objects.requireNonNull(named);

        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        // check dataset and named graph exist
        String dsKey = Fuseki2Utils.makeRegistryKey(datasetId);
        if (registry.isRegistered(dsKey)) {
            DataAccessPoint dap = registry.get(dsKey);
            DataService ds = dap.getDataService();
            try {
                ds.startTxn(ReadWrite.READ);
                // export named graph to turtle
                DatasetGraph dataset = ds.getDataset();
                Node namedGraph = NodeFactory.createURI(named);
                RDFFormat fmt = (lang == Lang.RDFXML) ? RDFFormat.RDFXML_PLAIN : RDFWriterRegistry.defaultSerialization(lang);
                StringWriter out = new StringWriter();
                
                if (dataset instanceof DatasetGraphTransaction) {
                    DatasetGraphTransaction dsgtxn = (DatasetGraphTransaction) dataset;
                    try {
                        dsgtxn.begin(ReadWrite.READ);
                        Graph graph = dsgtxn.getGraph(namedGraph);
                        serverLog.debug(graph.toString());
                        serverLog.info(String.format("RMS Fuseki addon - Graph Store Protocol for Transaction Named Graph: dataset:%s, named graph: %s", datasetId, named));
                        serverLog.info(String.format("format: %s", fmt.toString()));
                        RDFDataMgr.write(out, graph, fmt);
                        return out.toString();
                    } finally {
                        dsgtxn.commit();
                    }
                } else {
                    Graph graph = dataset.getGraph(namedGraph);
                    serverLog.debug(graph.toString());
                    serverLog.info(String.format("RMS Fuseki addon - Graph Store Protocol for Named Graph: dataset:%s, named graph: %s", datasetId, named));
                    serverLog.info(String.format("format: %s", fmt.toString()));
                    RDFDataMgr.write(out, graph, fmt);
                    return out.toString();
                }
            } finally {
                ds.finishTxn(ReadWrite.READ);
            }
        }

        return "";
    }

    /**
     * 取得Dataset default graph
     * 
     * @param datasetId
     * @param lang
     * @return 
     */
    public String retrievalDefaultGraph(String datasetId, Lang lang) {
        Objects.requireNonNull(datasetId);

        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        // check dataset and named graph exist
        String dsKey = Fuseki2Utils.makeRegistryKey(datasetId);
        if (registry.isRegistered(dsKey)) {
            DataAccessPoint dap = registry.get(dsKey);
            DataService ds = dap.getDataService();
            try {
                ds.startTxn(ReadWrite.READ);
                // export named graph to turtle
                DatasetGraph dataset = ds.getDataset();
                RDFFormat fmt = (lang == Lang.RDFXML) ? RDFFormat.RDFXML_PLAIN : RDFWriterRegistry.defaultSerialization(lang);
                StringWriter out = new StringWriter();
                
                if (dataset instanceof DatasetGraphTransaction) {
                    DatasetGraphTransaction dsgtxn = (DatasetGraphTransaction) dataset;
                    try {
                        dsgtxn.begin(ReadWrite.READ);
                        Graph graph = dsgtxn.getDefaultGraph();
                        serverLog.debug(graph.toString());
                        serverLog.info(String.format("RMS Fuseki addon - Graph Store Protocol for Transaction Default Graph: dataset:%s", datasetId));
                        serverLog.info(String.format("format: %s", fmt.toString()));
                        RDFDataMgr.write(out, graph, fmt);
                        return out.toString();
                    } finally {
                        dsgtxn.commit();
                    }
                } else {
                    Graph graph = dataset.getDefaultGraph();
                    serverLog.debug(graph.toString());
                    serverLog.info(String.format("RMS Fuseki addon - Graph Store Protocol for Default Graph: dataset:%s", datasetId));
                    serverLog.info(String.format("format: %s", fmt.toString()));
                    RDFDataMgr.write(out, graph, fmt);
                    return out.toString();
                }
            } finally {
                ds.finishTxn(ReadWrite.READ);
            }
        }

        return "";
    }
}
