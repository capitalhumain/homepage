package connector;

import lab1.HelloHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.util.component.LifeCycle.Listener;

/**
 *
 * @author tzuyichao
 */
public class ConnectorLifecycle {
    public static void main(String... argv) throws Exception {
        Server server = new Server();

        ServerConnector httpConnector = new ServerConnector(server);
        httpConnector.setName("Service");
        httpConnector.setHost("localhost");
        httpConnector.setPort(8080);
        httpConnector.setIdleTimeout(30000);
        httpConnector.addLifeCycleListener(new Listener() {

            public void lifeCycleStarting(LifeCycle lc) {
                System.out.println("Starting...");
            }

            public void lifeCycleStarted(LifeCycle lc) {
                System.out.println("Started...");
            }

            public void lifeCycleFailure(LifeCycle lc, Throwable thrwbl) {
                System.out.println("Failure...");
            }

            public void lifeCycleStopping(LifeCycle lc) {
                System.out.println("Stoping...");
            }

            public void lifeCycleStopped(LifeCycle lc) {
                System.out.println("Stopped...");
            }
        });

        server.addConnector(httpConnector);
        
        server.setHandler(new HelloHandler());
        // 加這個才會看到用control-c中斷程式的時候有stoping...和stoped...的log
        server.setStopAtShutdown(true);

        server.start();
        server.join();
    }
}
