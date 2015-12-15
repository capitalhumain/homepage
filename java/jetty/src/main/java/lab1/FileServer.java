package lab1;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;

public class FileServer {
    public static void main(String... argv) throws Exception {
        Server server = new Server(8080);
        
        // Resource handler
        ResourceHandler resourceHandler = new ResourceHandler();
        // 應該要false
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[] { "index.html" });
        resourceHandler.setResourceBase("./webapp");

        GzipHandler gzip = new GzipHandler();
        server.setHandler(gzip);
        HandlerList handlers = new HandlerList();
        handlers.setHandlers( new Handler[] {resourceHandler, new DefaultHandler() });
        gzip.setHandler(handlers);

        server.start();
        server.join();
    }
}
