package lab;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import static org.apache.jena.fuseki.Fuseki.serverLog;

/**
 *
 * @author tzuyichao
 */
public class UploadServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        serverLog.info("upload servlet called");
        response.setContentType("application/json");
        
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if ( ! isMultipart ) {
            serverLog.info("not multipart request");
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }
        
        ServletFileUpload upload = new ServletFileUpload();
        try {
            FileItemIterator iter = upload.getItemIterator(request) ;
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                String fieldName = item.getFieldName();
                
                serverLog.info("fieldName:" + fieldName);
                InputStream stream = item.openStream();
                String value = Streams.asString(stream, "UTF-8") ;
                switch(fieldName) {
                    case "file":
                        serverLog.info("content:\n" + value + "\n");
                        break;
                    case "param":
                        serverLog.info("content:" + value);
                        break;
                    default:
                        serverLog.info("field not support");
                }
                response.getWriter().println("{\"msg\": \"Done\"}");
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
