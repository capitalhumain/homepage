package lab1;

import java.io.File;
import java.lang.management.ManagementFactory;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.handler.AllowSymLinkAliasChecker;
import org.eclipse.jetty.webapp.WebAppContext;

public class OneWebApp {
    public static void main(String... argv) throws Exception {
        Server server = new Server(8080);
        
        // 因為下面要用jmx的測試web app
        MBeanContainer mbeanContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        server.addBean(mbeanContainer);

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        File warFile = new File("/Users/tzuyichao/src/org.eclipse.jetty.project/tests/test-jmx/jmx-webapp/target/jmx-webapp");
        webapp.setWar(warFile.getAbsolutePath());

        server.setHandler(webapp);
        server.start();
        server.join();
    }
}
