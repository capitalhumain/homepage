package com.delta.cs.rms.fuseki.servlets;

import com.delta.cs.rms.fuseki.helper.RequestHelper;
import com.delta.cs.rms.fuseki.services.Fuseki2Operator;
import com.delta.cs.rms.fuseki.services.SystemDatasetService;
import com.delta.cs.rms.fuseki.vo.APIResult;
import com.delta.cs.rms.fuseki.vo.ClientIdSupportVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import static org.apache.jena.fuseki.Fuseki.serverLog;
import org.apache.jena.fuseki.server.DataAccessPointRegistry;
import org.apache.jena.riot.Lang;

/**
 *
 * @author tzuyichao
 */
public class ClientIdSupportServlet extends HttpServlet {

    public final static String CMD_CREATE_DATASET = "create_dataset";
    public final static String CMD_LIST_DATASET = "list_dataset";
    public final static String CMD_DROP_DATASET = "drop_dataset";
    public final static String CMD_DEL_CLIENTID = "del_clientid";
    public final static String CMD_LIST_CLIENTID = "list_clientid";
    public final static String CMD_RETRIEVAL_NAMED_GRAPH =  "retrieval_named_graph";
    public final static String CMD_RETRIEVAL_DEFAULT_GRAPH =  "retrieval_default_graph";

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * Example1:
     * 
     * ```
     * {
     *  "clientId": "test",
     *  "type": "temp",
     *  "command": "list_dataset"
     * }
     * ```
     * 
     * Example2:
     * 
     * ```
     * {
     *  "clientId": "test",
     *  "type": "temp",
     *  "command": "create_dataset"
     * }
     * ```
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // check system dataset exist
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        if (SystemDatasetService.isSystemDatasetExist()) {
            try {
                ClientIdSupportVO vo = RequestHelper.convertClientIdSupportVO(request);
                if (vo.isValid()) {
                    serverLog.info(vo.toString());
                    SystemDatasetService systemDatasetService = SystemDatasetService.getInstance();
                    // TODO: refactoring condition
                    switch (vo.command) {
                        case CMD_CREATE_DATASET:
                        	String dsid = null;
                        	serverLog.info(vo.type + "|" + "temp".equalsIgnoreCase(vo.type));
                        	// remove temp dataset logic
//                        	if("temp".equalsIgnoreCase(vo.type)) {
//                        		dsid = String.format("TEMP_%s", vo.clientId);
//                        	}
                        	if(null == dsid) {
                        		dsid = UUID.randomUUID().toString();
                        	}
                            if(registry.isRegistered("/" + dsid)) {
                                serverLog.error("dsid conflict");
                                serverLog.error(dsid);
                                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                                response.getWriter().println("unknown error. plz try again");
                            } else {
                                // create dataset
                                Fuseki2Operator op = new Fuseki2Operator();
                                if (op.createDataset(dsid)) {
                                    // add record to system
                                    serverLog.info(vo.clientId + "|" + vo.type + "|" + dsid);
                                    systemDatasetService.addRecord(vo.clientId, vo.type, dsid);
                                    response.setStatus(HttpServletResponse.SC_OK);
                                    response.setContentType("text/plain");
                                    response.getWriter().println(dsid);
                                } else {
                                    response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                                    response.getWriter().println("create dataset failed");
                                }
                            }
                            break;
                        case CMD_LIST_DATASET:
                            String[] datasetList = systemDatasetService.listDatasetViaSPARQLEndpoint(vo.clientId, vo.type);
                            APIResult result = new APIResult();
                            result.status = true;
                            result.message = "success";
                            serverLog.info("ClientId:" + vo.clientId + " from system dataset");
                            for (String ds : datasetList) {
                                serverLog.info(ds);
                            }
                            // filter does not exisit dataset
                            // 2016-11-23 remove registry check
//                            Object[] filtered = Arrays.stream(datasetList).filter((String ds) -> { 
//                                return registry.isRegistered("/" + ds); 
//                            }).toArray();
//                            serverLog.info("ClientId:" + vo.clientId + "Filtered dataset size=" + filtered.length);                           
//                            if(datasetList.length != filtered.length) {
//                            	serverLog.warn("ClientId:" + vo.clientId + " system dataset records and registed datasets are mismatch");
//                            }
                            
                            Map<String, Object> payloads = new HashMap<>();
                            //payloads.put("datasets", filtered);
                            payloads.put("datasets", datasetList);
                            result.payload = payloads;
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("application/json");
                            ObjectMapper mapper = new ObjectMapper();
                            response.getWriter().println(mapper.writeValueAsString(result));
                            break;
                        case CMD_DROP_DATASET:
                            if (!StringUtils.isBlank(vo.dataset)) {
                                systemDatasetService.dropDataset(vo.dataset);
                                response.setStatus(HttpServletResponse.SC_OK);
                            } else {
                                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                                response.getWriter().println("dataset is required for drop dataset");
                            }
                            break;
                        case CMD_LIST_CLIENTID:
                            String[] clientIdArray = systemDatasetService.listClientId();
                            result = new APIResult();
                            result.status = true;
                            result.message = "success";
                            payloads = new HashMap<>();
                            payloads.put("clientIds", clientIdArray);
                            result.payload = payloads;
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("application/json");
                            mapper = new ObjectMapper();
                            response.getWriter().println(mapper.writeValueAsString(result));
                            break;
                        case CMD_DEL_CLIENTID:
                            systemDatasetService.deleteClientId(vo.clientId, vo.type);
                            response.setStatus(HttpServletResponse.SC_OK);
                            break;
                        case CMD_RETRIEVAL_NAMED_GRAPH:
                        	// TODO: refactoring to 303 to http://fuseki:port/dataset/get?graph=named_graph
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("text/turtle;charset=UTF-8");
                            response.getWriter().print(systemDatasetService.retrievalNamedGraph(vo.dataset, vo.named, Lang.TURTLE));
                            break;
                        case CMD_RETRIEVAL_DEFAULT_GRAPH:
                        	// TODO: refactoring to 303 to http://fuseki:port/dataset/get?graph=default
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("text/turtle;charset=UTF-8");
                            response.getWriter().print(systemDatasetService.retrievalDefaultGraph(vo.dataset, Lang.TURTLE));
                            break;
                        default:
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            response.getWriter().println("command not support");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().println("input json is missing required information");
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("input json has error");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().println("system dataset does not exist");
        }
    }
}
