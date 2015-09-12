package sparql;

import java.io.InputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import model.DefaultModelExp1;

public class Inference1 {
	public static void executeSPARQL(final String q, final Model model) {
		Query query = QueryFactory.create( q );
        QueryExecution qexec = QueryExecutionFactory.create( query, model );
        try {
            ResultSet results = qexec.execSelect();
            ResultSetFormatter.out( results, model );
        } finally {
            qexec.close();
        }
	}
	
    public static void main(String[] args) {
    	InputStream in = DefaultModelExp1.class.getClassLoader().getResourceAsStream("fileC.ttl");
		Model model = ModelFactory.createDefaultModel();
		model.read(in, null, "TTL");
		
//		String q = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
//				"PREFIX ab: <http://learningsparql.com/ns/addressbook#> " +
//                "select ?subject " +
//                "where { " +
//                "  ?subject rdf:type ab:Musician . " +
//                "}";
		String q = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX d: <http://learningsparql.com/ns/data#> " +
                "select ?subject " +
                "where { " +
                "  ?subject rdf:type d:Plantae . " +
                "}";
		
		executeSPARQL(q, model);
		
		InfModel infModel = ModelFactory.createRDFSModel(model);
		infModel.write(System.out, "TURTLE");
		executeSPARQL(q, infModel);
    }
}
