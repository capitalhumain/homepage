package noise;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class DBPediaURIExtractor implements DBPediaResultExtractor<List<String> > {
    private static final Log log = LogFactory.getLog(DBPediaURIExtractor.class);
    private List<String> result = new ArrayList<String>();
    public String getName() {
        return "uri";
    }
    public String responseType() {
        return "application/sparql-results+json";
    }
    public void apply(String source, String fieldName) {
        if(null == source || null == fieldName) {
            throw new IllegalArgumentException("source cannot be null");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            // File json = new File("/Users/tzuyichao/lab/java/jackson/data/virtuoso.json");
            JsonNode root = mapper.readTree(source);
            log.info(root);
            JsonNode bindings = root.at("/results/bindings");
            log.info(bindings);
            Iterator<JsonNode> iter = bindings.elements();
            while(iter.hasNext()) {
                JsonNode node = iter.next();
                log.info(node);
                String value = node.at(String.format("/%s/value", fieldName)).asText();
                log.info(value);
                result.add(value);
            }
        } catch(IOException e) {
            log.error(e);
        }finally {

        }
    }
    public List<String> result() {
        return result;
    }
}
