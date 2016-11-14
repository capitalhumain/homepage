package test;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

public class RemoteSPARQLUpdate {
	
	public static void execute(String remoteEndPoint, String sparql) {
		UpdateRequest update = UpdateFactory.create(sparql);
		UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(update, remoteEndPoint);
			
		try {
			updateProcessor.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// insert
		String updateString = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
 		        " insert data {" +
				" <http://delta.com/it/kawari/ds-00001> <http://delta.com/it/kawari/type> \"Demo\"." +
      		" <http://delta.com/it/kawari/ds-00001> rdfs:label \"This is label\"." +
      		"<http://dms.deltawww.com/ts/testid-00001> <http://dms.deltawww.com/ts/type> \"General\" ." +
		        "}";
		// delete
//		String updateString =  " delete data {" +
//				" <http://delta.com/it/kawari/ds-00001> <http://delta.com/it/kawari/type> \"Demo\"." +
//			        "}";
		
		// update
//		String updateString = "delete { ?s <http://delta.com/it/kawari/type> \"Demo\" . } " +
//		                      "insert { ?s <http://delta.com/it/kawari/type> \"General\". } " +
//		                      "where { ?s <http://delta.com/it/kawari/type> \"Demo\". }";
		UpdateRequest update = UpdateFactory.create(updateString);
		UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(update, "http://localhost:3030/system/update");
			
		try {
			updateProcessor.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Done.");
	}

}
