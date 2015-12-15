package lab1;

import org.eclipse.jetty.server.Server;

public class CreateServer {
    public static void main(String... argv) throws Exception {
        Server server = new Server(8080);
        server.start();
        server.dumpStdErr();
        server.join();
    }
}
