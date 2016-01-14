package lab;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab.helper.Fuseki2Utils;
import lab.vo.ReplaceGraphItem;
import lab.vo.ReplaceGraphVO;
import org.apache.jena.fuseki.server.DataAccessPoint;
import org.apache.jena.fuseki.server.DataAccessPointRegistry;
import org.apache.jena.fuseki.server.DataService;
import org.apache.jena.graph.Graph;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.tdb.StoreConnection;
import org.apache.jena.tdb.transaction.DatasetGraphTransaction;

/**
 *
 * @author tzuyichao
 */
public class ReplaceGraphV2Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        InputStream jsonIs = request.getInputStream();
        if (null != jsonIs) {
            try {
                // parse input json string to object
                ObjectMapper objectMapper = new ObjectMapper();
                ReplaceGraphVO valueObject = objectMapper.readValue(jsonIs, ReplaceGraphVO.class);
                if (valueObject != null && valueObject.target != null
                        && valueObject.sourceItems != null
                        && valueObject.sourceItems.length > 0) {
                    //response.getWriter().print(valueObject.toString());
                    DataAccessPointRegistry registry = DataAccessPointRegistry.get();
                    String targetDsKey = Fuseki2Utils.makeRegistryKey(valueObject.target);
                    // TODO: validate dataset on fuseki
                    boolean validate = true;
                    StringBuilder validate_msg = new StringBuilder();
                    if(!registry.isRegistered(targetDsKey)) {
                        validate = false;
                        validate_msg.append(valueObject.target).append(" "); //
                    }
                    for (ReplaceGraphItem item : valueObject.sourceItems) {
                        if(!registry.isRegistered(Fuseki2Utils.makeRegistryKey(item.sourceDataset))) {
                            validate = false;
                            validate_msg.append(item.sourceDataset).append(" ");
                        }
                    }

                    if(validate == false) {
                        validate_msg.append(" dataset is not registed on fuseki");
                        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                        response.getWriter().print(validate_msg.toString());
                        return;
                    }
                    DataAccessPoint targetDAP = registry.get(targetDsKey);
                    DataService targetDS = targetDAP.getDataService();
                    if (targetDS.allowUpdate()) {
                        try {
                            // 要使用transaction
                            targetDS.startTxn(ReadWrite.WRITE);
                            //targetDS.goOffline();
                            for (ReplaceGraphItem item : valueObject.sourceItems) {
                                String sourceDsKey = Fuseki2Utils.makeRegistryKey(item.sourceDataset);
                                DataAccessPoint sourceDAP = registry.get(sourceDsKey);
                                DataService sourceDS = sourceDAP.getDataService();
                                DatasetGraph sourceDatasetGraph = sourceDS.getDataset();
                                DatasetGraph targetDatasetGraph = targetDS.getDataset();
                                // source default graph
                                Graph graph = sourceDatasetGraph.getDefaultGraph();
                                
                                // 如果使用者沒有設定namedGraph，則使用dataset name當成named graph name
                                String named_graph_uri;
                                if(item.namedGraph != null) {
                                    named_graph_uri = item.namedGraph;
                                } else {
                                    named_graph_uri = item.sourceDataset;
                                }
                                
                                if(targetDatasetGraph instanceof DatasetGraphTransaction) {
                                    DatasetGraphTransaction dsgtxn = (DatasetGraphTransaction)targetDatasetGraph;
                                    try {
                                        dsgtxn.begin(ReadWrite.WRITE);
                                        dsgtxn.addGraph(NodeFactory.createURI(named_graph_uri), graph);
                                    } finally {
                                        dsgtxn.commit();
                                    }
                                } else {
                                    // go replace named graph
                                    targetDatasetGraph.addGraph(NodeFactory.createURI(named_graph_uri), graph);
                                }
                                response.setStatus(HttpServletResponse.SC_OK);
                            }
                        } finally {
                            //targetDS.goActive();
                            targetDS.finishTxn(ReadWrite.WRITE);
                        }
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                        response.getWriter().print("The target database cannot update");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                    response.getWriter().print("target, sourceItems missing or empty");
                }
            } catch (JsonParseException | JsonMappingException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(e.getMessage());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
