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
import lab.vo.CopyGraphVO;
import org.apache.jena.fuseki.server.DataAccessPoint;
import org.apache.jena.fuseki.server.DataAccessPointRegistry;
import org.apache.jena.fuseki.server.DataService;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.tdb.transaction.DatasetGraphTransaction;

/**
 *
 * @author tzuyichao
 */
public class ReplaceGraphV3Servlet extends HttpServlet {

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
                ObjectMapper objectMapper = new ObjectMapper();
                CopyGraphVO valueObject = objectMapper.readValue(jsonIs, CopyGraphVO.class);
                if (valueObject != null && valueObject.isValid()) {
                    DataAccessPointRegistry registry = DataAccessPointRegistry.get();
                    String targetDsKey = Fuseki2Utils.makeRegistryKey(valueObject.targetDataset);
                    String sourceDsKey = Fuseki2Utils.makeRegistryKey(valueObject.sourceDataset);
                    // TODO: validate dataset on fuseki
                    boolean validate = true;
                    StringBuilder validate_msg = new StringBuilder();
                    if (!registry.isRegistered(targetDsKey)) {
                        validate = false;
                        validate_msg.append(valueObject.targetDataset).append(" "); //
                    }
                    if (!registry.isRegistered(sourceDsKey)) {
                        validate = false;
                        validate_msg.append(valueObject.sourceDataset).append(" "); //
                    }
                    if (validate == false) {
                        validate_msg.append(" dataset is not registed on fuseki");
                        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                        response.getWriter().print(validate_msg.toString());
                    } else {
                        DataAccessPoint targetDAP = registry.get(targetDsKey);
                        DataService targetDS = targetDAP.getDataService();
                        if (targetDS.allowUpdate()) {
                            try {
                                // 要使用transaction
                                targetDS.startTxn(ReadWrite.WRITE);
                                DataAccessPoint sourceDAP = registry.get(sourceDsKey);
                                DataService sourceDS = sourceDAP.getDataService();
                                DatasetGraph sourceDatasetGraph = sourceDS.getDataset();
                                DatasetGraph targetDatasetGraph = targetDS.getDataset();
                                
                                if(targetDatasetGraph instanceof DatasetGraphTransaction) {
                                    DatasetGraphTransaction dsgtxn = (DatasetGraphTransaction)targetDatasetGraph;
                                    try {
                                        dsgtxn.begin(ReadWrite.WRITE);
                                        //java.lang.UnsupportedOperationException: Can't set default graph via GraphStore on a TDB-backed dataset
                                        //dsgtxn.setDefaultGraph(sourceDatasetGraph.getDefaultGraph());
                                        dsgtxn.addGraph(null, sourceDatasetGraph.getDefaultGraph());
                                    } finally {
                                        dsgtxn.commit();
                                    }
                                } else {
                                    // java.lang.UnsupportedOperationException: Can't set default graph via GraphStore on a TDB-backed dataset
                                    //targetDatasetGraph.setDefaultGraph(sourceDatasetGraph.getDefaultGraph());
                                    targetDatasetGraph.addGraph(null, sourceDatasetGraph.getDefaultGraph());
                                }
                            } finally {
                                targetDS.finishTxn(ReadWrite.WRITE);
                            }
                        } else {
                            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                            response.getWriter().print("The target database cannot update");
                        }
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                    response.getWriter().print("target and source are missing or empty");
                }
            } catch (JsonParseException | JsonMappingException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(e.getMessage());
            }
        }
    }
}