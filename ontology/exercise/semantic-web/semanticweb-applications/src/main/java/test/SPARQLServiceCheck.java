package test;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.sparql.engine.http.QueryExceptionHTTP;

/**
 * Monitor
 * 
 * @author terence
 *
 */
public class SPARQLServiceCheck {
	final static String askQuery = "ASK {}";
	
	public static boolean isRunning(String endpoint) {
		try(QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, askQuery)) {
			if(qe.execAsk()) {
				return true;
			} 
		} catch(QueryExceptionHTTP e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public static void main(String[] args) {
		boolean dbpediaStatus = isRunning("http://dbpedia.org/sparql");
		System.out.println("DBPedia SPARQL Endpoint Status: " + dbpediaStatus);
	}

}
