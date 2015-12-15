package handler;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author tzuyichao
 */
public class WebAppContextLab1 {
    private static final String SERVER_BASE = "." ;
    
    public static void main(String... argv) throws Exception {
        Server server = new Server(8080) ;
        server.setHandler(createWebAppContext("/", "WebAppContextLab1")) ;
        
        server.start() ;
        server.join() ;
    }
    
    public static WebAppContext createWebAppContext(String contextPath, String displayName) {
        WebAppContext webapp = new WebAppContext() ;
        webapp.getServletContext().getContextHandler().setMaxFormContentSize(2097152) ; // Max Post Data, 相當於Tomcat的maxPostSize這個設定
        
        String resourceBase = String.format("%s/warapp", SERVER_BASE) ;
        String webxmlPath = String.format("%s/WEB-INF/web.xml", resourceBase) ;
        
        webapp.setDescriptor(webxmlPath) ;
        webapp.setResourceBase(resourceBase) ;
        webapp.setContextPath(contextPath) ;
        
        webapp.setDisplayName(displayName) ;  
        webapp.setParentLoaderPriority(true) ;
        
        return webapp ;
    }
}
