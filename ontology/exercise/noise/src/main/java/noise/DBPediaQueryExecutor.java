package noise;

import java.io.IOException;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.utils.URIBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class DBPediaQueryExecutor {
    private static final Log log = LogFactory.getLog(DBPediaQueryExecutor.class);

    public void execute(String sparql, String fieldName, String resultType, final DBPediaResultExtractor extractor) {
        if(null == sparql || null == fieldName || null == resultType || null == extractor) {
            throw new IllegalArgumentException("All parameters are required.");
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            URI uri = new URIBuilder()
                            .setScheme("http")
                            .setHost("dbpedia.org")
                            .setPath("/sparql")
                            .setParameter("default-graph-uri", "http://dbpedia.org")
                            .setParameter("query", sparql)
                            .setParameter("format", resultType) //"application/sparql-results+json")
                            .setParameter("timeout", "30000")
                            .setParameter("debug", "on")
                            .build();
            HttpGet httpget = new HttpGet(uri);

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            log.info(responseBody);
            extractor.apply(responseBody, fieldName);
        } catch(URISyntaxException e) {
            log.error(e);
        } catch(IOException e) {
            log.error(e);
        } finally {
            if(httpclient != null) {
                try {
                    httpclient.close();
                } catch(Exception e) {
                    log.error(e);
                }
            }
        }
    }

}
