package test;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

/**
 * Retrieval
 * 
 * @author terence
 *
 */
public class ModelLoadData {
	final static String resourceURI = "http://dbpedia.org/resource/Roger_Federer";

	public static void main(String[] args) {
		Model model = ModelFactory.createDefaultModel();
		model.read(resourceURI);
		
		model.write(System.out, "TURTLE");
		// 如果習慣看XML就看這個吧
		// model.write(System.out, "RDF/XML");
		
		
	}

}
