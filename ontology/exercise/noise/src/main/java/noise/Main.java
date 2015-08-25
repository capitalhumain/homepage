package noise;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class Main {
    private static final Log log = LogFactory.getLog(Main.class);
    private static String sparql = "select ?subject where { " +
                                  "  ?subject " +
                                  "  <http://www.w3.org/2000/01/rdf-schema#label> " +
                                  "  \"Roger Federer\"@en. " +
                                  "  ?subject " +
                                  "  <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> " +
                                  "  <http://xmlns.com/foaf/0.1/Person>. " +
                                  "}";
    private static final String type = "application/sparql-results+json";

    public static void main(String[] args) {
        log.info("Running...");
        DBPediaQueryExecutor executor = new DBPediaQueryExecutor();
        DBPediaURIExtractor extractor = new DBPediaURIExtractor();
        try {
            executor.execute(sparql, "subject", type, extractor);

            log.info(extractor.result());
        } catch(IllegalArgumentException e) {
            log.error(e);
        }
    }
}
