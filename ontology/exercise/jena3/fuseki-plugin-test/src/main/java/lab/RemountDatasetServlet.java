package lab;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jena.fuseki.Fuseki;
import org.apache.jena.fuseki.build.FusekiConfig;
import org.apache.jena.fuseki.server.DataAccessPoint;
import org.apache.jena.fuseki.server.DataAccessPointRegistry;
import org.apache.jena.fuseki.server.FusekiEnv;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.slf4j.Logger;

/**
 *
 * @author tzuyichao
 */
public class RemountDatasetServlet extends HttpServlet {
    private static Logger log = Fuseki.serverLog ;
    // ../, $, *
    private static final Pattern NAME_CHECK_PATTERN = Pattern.compile("[../|$|*]");
    
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
    
    private boolean checkName(String name) {
        Matcher m = NAME_CHECK_PATTERN.matcher(name);
        return m.find();
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String dbNameParam = request.getParameter("dbName");
        if(null == dbNameParam) {
            responseHelper(response, HttpServletResponse.SC_NOT_ACCEPTABLE, "dbName is required.");
            return;
        }
        if(checkName(dbNameParam)) {
            responseHelper(response, HttpServletResponse.SC_NOT_ACCEPTABLE, "dbName is invalid.");
            return;
        }
        String dbName = makeRegistryKey(dbNameParam);
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        if(registry.isRegistered(dbName)) {
            responseHelper(response, HttpServletResponse.SC_NOT_ACCEPTABLE, "dbName " + dbName + " is registed.");
        } else {
            try {
                Model m = RDFDataMgr.loadModel(FusekiEnv.generateConfigurationFilename(dbNameParam));
                Method readConfiguration = FusekiConfig.class.getDeclaredMethod("readConfiguration", Model.class);
                readConfiguration.setAccessible(true);
                DataAccessPoint dataAccessPoint = (DataAccessPoint) readConfiguration.invoke(null, m);
                registry.put(dbName, dataAccessPoint);
                responseHelper(response, HttpServletResponse.SC_OK, null);
            } catch (NoSuchMethodException e) {
                // FIXME: just for test
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // FIXME: just for test
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // FIXME: just for test
                e.printStackTrace();
            }
        }
    }
    
}
