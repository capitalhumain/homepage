package reasoner;

//import org.apache.jena.rdf.model.Model;
//import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
//import org.apache.jena.rdf.model.Resource;
//import org.apache.jena.reasoner.Reasoner;
//import org.apache.jena.vocabulary.ReasonerVocabulary;
//import org.apache.jena.reasoner.rulesys.RDFSRuleReasonerFactory;

public class SimpleRDFSQuery {
    private static String q = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                              "PREFIX shop: <http://learningsparql.com/ns/shop#> " +
                              "select ?item " +
                              "where { " +
                              "  ?item rdf:type shop:Shirts . " +
                              "}";

    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println( "Need one argument to model file" );
            return;
        }
        System.out.println(String.format("SPARQL: %s", q));
        String file = args[0];
        OntModel im = ModelFactory.createOntologyModel();
        im.read( file );

        im.write(System.out, "TURTLE");

        Query query = QueryFactory.create( q );
        QueryExecution qexec = QueryExecutionFactory.create( query, im );
        try {
            ResultSet results = qexec.execSelect();
            ResultSetFormatter.out( results, im );
        } finally {
            qexec.close();
        }
    }
}
