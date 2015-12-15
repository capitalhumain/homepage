package lab1;

import org.eclipse.jetty.server.Server;

/**
 * with hello handler
 */
public class HelloWorldServer {
    public static void main(String... argv) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new HelloHandler());
        
        server.start();
        server.join();
    }
}

