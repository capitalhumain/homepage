package lab.mgt;

import java.io.IOException;
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
 * 初始化/system dataset
 * 
 * @author tzuyichao
 */
public class InitialDeltaAddonServlet extends HttpServlet {
    private static Logger log = Fuseki.serverLog ;
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        if(!registry.isRegistered("/system")) {
            // create system dataset
        }
    }
}
