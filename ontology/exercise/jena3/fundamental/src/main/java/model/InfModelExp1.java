package model;

import java.io.InputStream;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfModelExp1 {
	private static Logger log = LoggerFactory.getLogger(InfModelExp1.class);
	
	public static void main(String[] args) {
		InputStream in = DefaultModelExp1.class.getClassLoader().getResourceAsStream("fileB.ttl");
		Model model = ModelFactory.createDefaultModel();
		model.read(in, null, "TTL");
		
		InfModel infModel = ModelFactory.createRDFSModel(model);
		infModel.write(System.out, "TURTLE");
		
		Resource a = infModel.getResource("http://learningsparql.com/ns/data#i0432");
	    System.out.println("===> RESULT: (list statements of i0432)");
	    StmtIterator iter = a.listProperties();
	    while(iter.hasNext()) {
	      Statement stmt = iter.next();
	      log.info(stmt.toString());
	    }

	    a = infModel.getResource("http://learningsparql.com/ns/addressbook#vacuumCleaner");
	    System.out.println("===> RESULT: (list statements of vacuumCleaner)");
	    iter = a.listProperties();
	    while(iter.hasNext()) {
	      Statement stmt = iter.next();
	      log.info(stmt.toString());
	    }
	}

}
