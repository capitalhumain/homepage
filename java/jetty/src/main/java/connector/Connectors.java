package connector;

import lab1.HelloHandler;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;

import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.Request;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;

public class Connectors {
    public static void main(String... argv) throws Exception {
        Server server = new Server();

        ServerConnector httpConnector = new ServerConnector(server);
        httpConnector.setName("Service");
        httpConnector.setHost("localhost");
        httpConnector.setPort(8080);
        httpConnector.setIdleTimeout(30000);

        server.addConnector(httpConnector);

        ServerConnector adminConnector = new ServerConnector(server);
        adminConnector.setPort(8081);
        adminConnector.setName("Admin");
        server.addConnector(adminConnector);
        
        ContextHandler context = new ContextHandler();
        context.setContextPath( "/mgmt" );
        context.setHandler( new MgmtHandler(server) );
        context.setVirtualHosts(new String[] {"@Admin"});
        
        ContextHandler servicecontext = new ContextHandler();
        servicecontext.setContextPath( "/hello" );
        servicecontext.setHandler( new HelloHandler() );
        servicecontext.setVirtualHosts(new String[] {"@Service"});
        
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {context, servicecontext});
        server.setHandler(handlers);
        
        // 有設這個按control-c的時候會看到jetty stop的log
        server.setStopAtShutdown(true);

        server.start();
        server.join();
    }
}

class MgmtHandler extends AbstractHandler {
    private Server server = null;

    public MgmtHandler(Server server) {
        this.server = server;
    }

    public void handle(String target, 
                       Request baseRequest, 
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        System.out.println("MgmtHandler Called");
        response.setStatus(HttpServletResponse.SC_OK);
        // 看不到
        //PrintWriter out = response.getWriter();
        //out.println("<h1>Server shutdown in 3000 miliseconds</h1>");
        baseRequest.setHandled(true);
//        try {
//            //server.stop();
//            
//            for(Connector connector : server.getConnectors()) {
//                connector.stop(); //.shutdown();
//            }
//            
//            // 有設這個按control-c的時候會看到jetty stop的log
//            //server.setStopAtShutdown(true);
//            
//            //server.setStopTimeout(3000);
//            //System.exit(1);
//        } catch(Exception e) {
//            e.printStackTrace();
//            //System.exit(1);
//        }
    }
}
