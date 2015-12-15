package handler;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ShutdownHandler;

public class ShutdownLab {
    public static void main(String... argv) throws Exception {
        Server server = new Server(8080);
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { new ShutdownHandler("shutdown_token_here", false, true) });
        server.setHandler(handlers);
        server.start();
    }
}
