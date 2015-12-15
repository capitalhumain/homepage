package lab1;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class OneConnector {
    public static void main(String... argv) throws Exception {
        Server server = new Server();

        ServerConnector httpConnector = new ServerConnector(server);
        httpConnector.setHost("localhost");
        httpConnector.setPort(8080);
        httpConnector.setIdleTimeout(30000);

        server.addConnector(httpConnector);
        
        server.setHandler(new HelloHandler());        

        server.start();
        server.join();
    }
}
