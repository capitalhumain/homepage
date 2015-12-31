package lab;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jena.fuseki.Fuseki;
import org.apache.jena.fuseki.server.DataAccessPoint;
import org.apache.jena.fuseki.server.DataAccessPointRegistry;
import org.apache.jena.fuseki.server.DataService;
import org.slf4j.Logger;

/**
 * Shutdown dataset
 * 
 * @author tzuyichao
 */
public class ShutdownDatasetServlet extends HttpServlet {
    private static Logger log = Fuseki.serverLog ;
    
    // FIXME:
    private String makeRegistryKey(String name) {
        return String.format("/%s", name);
    }
    
    private void responseHelper(HttpServletResponse response, int statusCode, String msg) throws IOException {
        response.setStatus(statusCode);
        if(msg != null) {
            PrintWriter writer = response.getWriter();
            writer.println(msg);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String dbNameParam = request.getParameter("dbName");
        if(null == dbNameParam) {
            responseHelper(response, HttpServletResponse.SC_NOT_ACCEPTABLE, "dbName is required.");
            return;
        }
        String dbName = makeRegistryKey(dbNameParam);
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        if(!registry.isRegistered(dbName)) {
            responseHelper(response, HttpServletResponse.SC_NOT_ACCEPTABLE, "dbName is not registed.");
        } else {
            // shutdown dataset
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
                responseHelper(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                return;
            } catch (IllegalAccessException e) {
                // FIXME: just for test
                e.printStackTrace();
                responseHelper(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                return;
            } catch (InvocationTargetException e) {
                // 會有這個錯，但是卻會真的release，即使fuseki running，也可以在另一個process使用jena-tdb開啟這個被fuseki lock的tdb 
                // FIXME: just for test
                e.printStackTrace();
                //responseHelper(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
            // just go offline can see @fuseki (web or api)
            // dataService.goOffline();
            
            // remove db from registry, after fuseki restart will mount this database again
            registry.remove(dbName);
            responseHelper(response, HttpServletResponse.SC_OK, null);
        }
    }
}
