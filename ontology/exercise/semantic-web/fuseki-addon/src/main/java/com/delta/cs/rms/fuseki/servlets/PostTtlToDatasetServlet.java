package com.delta.cs.rms.fuseki.servlets;

import com.delta.cs.rms.fuseki.helper.Fuseki2Utils;
import com.delta.cs.rms.fuseki.vo.PostTtlVO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jena.fuseki.server.DataAccessPoint;
import org.apache.jena.fuseki.server.DataAccessPointRegistry;
import org.apache.jena.fuseki.server.DataService;
import org.apache.jena.graph.Graph;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.system.StreamRDFLib;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.tdb.transaction.DatasetGraphTransaction;

/**
 *
 * @author tzuyichao
 */
public class PostTtlToDatasetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        InputStream jsonIs = request.getInputStream();
        if (null != jsonIs) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                PostTtlVO valueObject = mapper.readValue(jsonIs, PostTtlVO.class);
                if(valueObject != null && valueObject.isValid()) {
                    DataAccessPointRegistry registry = DataAccessPointRegistry.get();
                    String targetDsKey = Fuseki2Utils.makeRegistryKey(valueObject.targetDataset);
                    boolean validate = true;
                    StringBuilder validate_msg = new StringBuilder();
                    if (!registry.isRegistered(targetDsKey)) {
                        validate = false;
                        validate_msg.append(valueObject.targetDataset).append(" "); //
                    }
                    if (validate == false) {
                        validate_msg.append(" dataset is not registed on fuseki");
                        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                        response.getWriter().print(validate_msg.toString());
                    } else {
                        Graph g = ModelFactory.createDefaultModel().getGraph();
                        RDFDataMgr.parse(StreamRDFLib.graph(g), new StringReader(valueObject.content), Lang.TTL);
                        //response.getWriter().print(g);
                        DataAccessPoint targetDAP = registry.get(targetDsKey);
                        DataService targetDS = targetDAP.getDataService();
                        if (targetDS.allowUpdate() && !g.isEmpty()) {
                            try {
                                // 要使用transaction
                                targetDS.startTxn(ReadWrite.WRITE);
                                DatasetGraph targetDatasetGraph = targetDS.getDataset();
                                
                                if(targetDatasetGraph instanceof DatasetGraphTransaction) {
                                    DatasetGraphTransaction dsgtxn = (DatasetGraphTransaction)targetDatasetGraph;
                                    try {
                                        dsgtxn.begin(ReadWrite.WRITE);
                                        //java.lang.UnsupportedOperationException: Can't set default graph via GraphStore on a TDB-backed dataset
                                        //dsgtxn.setDefaultGraph(sourceDatasetGraph.getDefaultGraph());
                                        dsgtxn.addGraph(null, g);
                                    } finally {
                                        dsgtxn.commit();
                                    }
                                } else {
                                    // java.lang.UnsupportedOperationException: Can't set default graph via GraphStore on a TDB-backed dataset
                                    //targetDatasetGraph.setDefaultGraph(sourceDatasetGraph.getDefaultGraph());
                                    targetDatasetGraph.addGraph(null, g);
                                }
                            } finally {
                                targetDS.finishTxn(ReadWrite.WRITE);
                            }
                        } else {
                            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                            response.getWriter().print("The target database cannot update or source graph is empty");
                        }
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().print("value object is invalid");
                }
            } catch (JsonParseException | JsonMappingException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(e.getMessage());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("input stream is null");
        }
    }
}
