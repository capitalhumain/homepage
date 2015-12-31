package lab;

//import java.util.function.BiConsumer;
import java.util.Collection;
//import java.util.Iterator;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jena.fuseki.build.FusekiConfig;

import org.apache.jena.fuseki.server.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;

/**
 *
 * @author tzuyichao
 */
public class PingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>It works!</h1>");
        
        DataAccessPointRegistry registry = DataAccessPointRegistry.get();
        
        //registry.isRegistered("/tdb1")
        // registry tdb1
        // 1. load dataset config file to model
        // 2. create DataAccessPoint
        // 3. registry to DataAccessPointRegistry
//        try {
//            Model m = RDFDataMgr.loadModel("/Users/tzuyichao/local/apache-jena-fuseki-2.3.0/run/tdb1.ttl");
//            Method readConfiguration = FusekiConfig.class.getDeclaredMethod("readConfiguration", Model.class);
//            readConfiguration.setAccessible(true);
//            DataAccessPoint dataAccessPoint = (DataAccessPoint)readConfiguration.invoke(null, m);
//            registry.put("/tdb1", dataAccessPoint);
//        } catch(NoSuchMethodException e) {
//            // FIXME: just for test
//            e.printStackTrace();
//        } catch(IllegalAccessException e) {
//            // FIXME: just for test
//            e.printStackTrace();
//        } catch(InvocationTargetException e) {
//            // FIXME: just for test
//            e.printStackTrace();
//        }
        
        response.getWriter().println("<ul>");
        // list DataAccessPoint s
//        Collection<String> keys = registry.keys();
//        keys.stream().forEach( (String key) -> {
//            try {
//                response.getWriter().println( "<li>" + key + ":" + registry.get(key) + "</li>" );
//            } catch(IOException e) {}
//        });
        
        /*
        Iterator<String> keyIter = keys.iterator();
        while(keyIter.hasNext()) {
            String name = keyIter.next();
            response.getWriter().println( "<li>" + name + ":" + registry.get(name) + "</li>" );
        }
        */
        
        // Registry<K, T>#forEach(action: BinConsumer<K, T>) 2.3.1才有
        BiConsumer<String, DataAccessPoint> consumer = (x, y) -> {
            try {
                response.getWriter().println( "<li>" + x + ":" + y + "</li>" );
            } catch(IOException e) {}
        };
        registry.forEach(consumer);
        
        
//        registry.forEach( (String name, DataAccessPoint accessPt) -> { 
//            try {
//                response.getWriter().println( "<li>" + name + ":" + accessPt + "</li>" );
//            } catch(IOException e) {}
//        } );
        
        
        response.getWriter().println("</ul>");
        
        PrintWriter writer = response.getWriter();
        
        writer.print( "<p> FusekiEnv.FUSEKI_BASE: " + FusekiEnv.FUSEKI_BASE + "</p>");
        writer.print( "<p> FusekiEnv.FUSEKI_HOME: " + FusekiEnv.FUSEKI_HOME + "</p>");
        writer.print( "<p> FusekiEnv db1 configuration file: " + FusekiEnv.generateConfigurationFilename("db1") + "</p>");
        
    }
}
