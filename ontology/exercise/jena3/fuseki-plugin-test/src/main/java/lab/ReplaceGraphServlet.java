package lab;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jena.fuseki.server.DataAccessPoint;
import org.apache.jena.fuseki.server.DataAccessPointRegistry;
import org.apache.jena.fuseki.server.DataService;
import org.apache.jena.graph.Graph;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.sparql.core.DatasetGraph;

/**
 * replace or import source dataset default graph into target dataset named graph.
 * 
 * @author tzuyichao
 */
public class ReplaceGraphServlet extends HttpServlet {
    private String makeRegistryKey(String name) {
        if(name.startsWith("/")) {
            return name;
        } else {
            return String.format("/%s", name);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String sourceDbName = request.getParameter("source");
        String targetDbName = request.getParameter("target");
        String named_graph_uri = request.getParameter("named_graph_uri");
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        
        if(null == sourceDbName || null == targetDbName || null == named_graph_uri) {
            // 這是不行的
            responseHelper(response, HttpServletResponse.SC_NOT_ACCEPTABLE, "source, target and named_graph_uri are requied parameter(s)");
        } else {
            String sourceDbKey = makeRegistryKey(sourceDbName);
            String targetDbKey = makeRegistryKey(targetDbName);
            if(registry.isRegistered(sourceDbKey) && registry.isRegistered(targetDbKey)) {
                // Go Go Go !!!
                DataAccessPoint sourceDAP = registry.get(sourceDbKey);
                DataAccessPoint targetDAP = registry.get(targetDbKey);
                if(sourceDAP != null && targetDAP != null && sourceDAP.getDataService() != null && targetDAP.getDataService() != null) {
                    // check targetDAP is allow update, and online
                    DataService targetDS = targetDAP.getDataService();
                    DataService sourceDS = sourceDAP.getDataService();
                    if(targetDS.allowUpdate()) {
                        try {
                            // 要使用transaction
                            targetDS.startTxn(ReadWrite.WRITE);
                        
                            DatasetGraph sourceDatasetGraph = sourceDS.getDataset();
                            DatasetGraph targetDatasetGraph = targetDS.getDataset();
        
                            Graph graph = sourceDatasetGraph.getDefaultGraph();
                            
                            // 這個寫法是replace，要用</code>GraphUtil#addInto(target: Graph, source: Graph)</code>才會merge到target graph
                            targetDatasetGraph.addGraph(NodeFactory.createURI(named_graph_uri), graph);
                            
                            response.setStatus(HttpServletResponse.SC_OK);
                        } finally {
                            targetDS.finishTxn(ReadWrite.WRITE);
                        }
                    } else {
                        // targetDAP is not allow update
                        responseHelper(response, HttpServletResponse.SC_NOT_ACCEPTABLE, "The target database cannot update");
                    }
                } else {
                    // DataAccessPoint is null?! fatal error
                    responseHelper(response, HttpServletResponse.SC_SERVICE_UNAVAILABLE, "DataAccessPoint is missing");
                }
            } else {
                // 有一個DB不存在勒，不能做
                responseHelper(response, HttpServletResponse.SC_NOT_ACCEPTABLE, "database does not exist");
            }
        }
    }
    
    private void responseHelper(HttpServletResponse response, int statusCode, String msg) throws IOException {
        response.setStatus(statusCode);
        if(msg != null) {
            PrintWriter writer = response.getWriter();
            writer.println(msg);
        }
    }
}
