package handler;

import lab1.HelloHandler;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ShutdownHandler;

/**
 *
 * @author tzuyichao
 */
public class ShutdownLab2 {
    public static void main(String... argv) throws Exception {
        Server server = new Server();
        
        // Service Connector
        ServerConnector httpConnector = new ServerConnector(server);
        httpConnector.setName("Service");
        httpConnector.setHost("localhost");
        httpConnector.setPort(8080);
        httpConnector.setIdleTimeout(30000);
        server.addConnector(httpConnector);
        
        // Mgmt Connector (actually Shutdown Connector in this case)
        ServerConnector adminConnector = new ServerConnector(server);
        adminConnector.setPort(8009);
        httpConnector.setHost("localhost");
        adminConnector.setName("Mgmt");
        server.addConnector(adminConnector);
        
        // Handler for Service Connector
        ContextHandler servicecontext = new ContextHandler();
        servicecontext.setContextPath( "/hello" );
        servicecontext.setHandler( new HelloHandler() );
        servicecontext.setVirtualHosts(new String[] {"@Service"});
        
        // Shutdown Handler for Mgmt Connector
        ContextHandler context = new ContextHandler();
        context.setContextPath( "/mgmt" );
        context.setHandler(new ShutdownHandler("shutdown_token_here", false, true));
        context.setVirtualHosts(new String[] {"@Mgmt"});
        
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {context, servicecontext});
        server.setHandler(handlers);
        
        // Server go!go!go!
        server.start();
        server.join();
    }
}
