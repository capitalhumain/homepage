package com.deltaww.dms.fuseki.helper;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

public class RemoteSPARQLAccess {
	
	final static String resourceURI = "http://dbpedia.org/resource/Roger_Federer";
	final static String DBPediaSPARQLEndpoint = "http://dbpedia.org/sparql";

//	public static void main(String[] args) {
//		String queryString = "SELECT ?propertyName ?propertyValue " + 
//	        "WHERE {" +
//			" <" + resourceURI + "> ?propertyName ?propertyValue." +
//	        "}";
//		Query query = QueryFactory.create(queryString);
//		QueryExecution qe = QueryExecutionFactory.sparqlService(DBPediaSPARQLEndpoint, query);
//		
//		try {
//			ResultSet resultSet = qe.execSelect();
//			while(resultSet.hasNext()) {
//				QuerySolution solution = resultSet.nextSolution();
//				Resource resource = (Resource) solution.get("propertyName");
//				System.out.print(" - <" + resource.getURI() + "> : ");
//				RDFNode node = solution.get("propertyValue");
//				if(node.isLiteral()) {
//					System.out.println(((Literal)node).getLexicalForm());
//				} else if(node.isResource()) {
//					resource = (Resource) node;
//					if(resource.isAnon()) {
//						// Blank Node, Anonymous node, bnode
//						System.out.println("<ANON:" + resource.getLocalName() + ">");
//					} else {
//						System.out.println("<" + resource.getURI() + ">");
//					}
//				}
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

}
