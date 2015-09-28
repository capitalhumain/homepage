package model;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Node_URI;
import org.apache.jena.graph.Triple;
import org.apache.jena.graph.impl.TripleStore;
import org.apache.jena.mem.BunchMap;
import org.apache.jena.mem.GraphMem;
import org.apache.jena.mem.GraphTripleStoreMem;
import org.apache.jena.mem.NodeToTriplesMapMem;
import org.apache.jena.mem.TripleBunch;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
//import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.log4j.Logger;

public class ListSubjects {
	private static Logger log = Logger.getLogger(ListSubjects.class);
	
	public static void hardcore(Model model) {
		Graph graph = model.getGraph();
    	GraphMem graphMem = (GraphMem) graph;
    	TripleStore store = graphMem.store;
    	GraphTripleStoreMem storemem = (GraphTripleStoreMem) store;
    	NodeToTriplesMapMem subjects = storemem.getSubjects();
    	BunchMap map = subjects.bunchMap;
    	
    	ExtendedIterator<Object> iter = map.keyIterator();
    	while(iter.hasNext()) {
    		Object key = iter.next();
    		// System.out.println(key instanceof Node_URI);
    		System.out.println(key);
    	}
	}
	
	public static void extractNodeURI(final BunchMap map, final Collection<String> storage) {
		ExtendedIterator<Object> iter = map.keyIterator();
    	while(iter.hasNext()) {
    		Object key = iter.next();
    		//log.info(key);
    		if(key instanceof Node_URI) {
    			storage.add(key.toString());
    		}
    	}
	}
	
	public static Set<TripleBunch> extractEdge(final BunchMap map) {
		Set<TripleBunch> edges = new HashSet<TripleBunch>();
		ExtendedIterator<Object> iter = map.keyIterator();
    	while(iter.hasNext()) {
    		Object key = iter.next();
    		//log.info(key);
    		if(key instanceof Node_URI) {
    			edges.add(map.get(key));
    		}
    	}
    	return edges;
	}
	
	/**
	 * 由model中取出subjects, objects而且為Node_URI的為vertices
	 * @param model
	 * @return
	 */
	public static Set<String> getVertices(Model model) {
		Set<String> vertices = new HashSet<String>();
		
		Graph graph = model.getGraph();
    	GraphMem graphMem = (GraphMem) graph;
    	TripleStore store = graphMem.store;
    	GraphTripleStoreMem storemem = (GraphTripleStoreMem) store;
    	NodeToTriplesMapMem subjects = storemem.getSubjects();
    	NodeToTriplesMapMem objects = storemem.getObjects();
    	BunchMap map = subjects.bunchMap;
    	
    	extractNodeURI(map, vertices);
    	Set<TripleBunch> edgedesc = extractEdge(map);
    	for(TripleBunch tripleBunch : edgedesc) {
    		ExtendedIterator<Triple> iter = tripleBunch.iterator();
    		while(iter.hasNext()) {
    			Triple triple = iter.next();
    			log.info(triple.getSubject());
    			log.info(triple.getPredicate());
    			log.info(triple.getObject());
    		}
    	}
    	
    	map = objects.bunchMap;
    	extractNodeURI(map, vertices);
		
		return vertices;
	}
	
	public static AdjancyMatrix getAdjancyMatrix(Model model) {
		Set<String> vertices = new HashSet<String>();
		
		Graph graph = model.getGraph();
    	GraphMem graphMem = (GraphMem) graph;
    	TripleStore store = graphMem.store;
    	GraphTripleStoreMem storemem = (GraphTripleStoreMem) store;
    	NodeToTriplesMapMem subjects = storemem.getSubjects();
    	NodeToTriplesMapMem objects = storemem.getObjects();
    	BunchMap map = subjects.bunchMap;
    	
    	extractNodeURI(map, vertices);
    	
    	map = objects.bunchMap;
    	extractNodeURI(map, vertices);
    	
    	AdjancyMatrix matrix = new AdjancyMatrix(vertices);
    	
    	Set<TripleBunch> edgedesc = extractEdge(map);
    	for(TripleBunch tripleBunch : edgedesc) {
    		ExtendedIterator<Triple> iter = tripleBunch.iterator();
    		while(iter.hasNext()) {
    			Triple triple = iter.next();
    			matrix.addEdge(triple.getSubject().toString(), triple.getObject().toString());
    		}
    	}
    	
		return matrix;
	}
	
	public static AdjancyList getAdjancyList(Model model) {
		Set<String> vertices = new HashSet<String>();
		
		Graph graph = model.getGraph();
    	GraphMem graphMem = (GraphMem) graph;
    	TripleStore store = graphMem.store;
    	GraphTripleStoreMem storemem = (GraphTripleStoreMem) store;
    	NodeToTriplesMapMem subjects = storemem.getSubjects();
    	NodeToTriplesMapMem objects = storemem.getObjects();
    	BunchMap map = subjects.bunchMap;
    	
    	extractNodeURI(map, vertices);
    	
    	map = objects.bunchMap;
    	extractNodeURI(map, vertices);
    	
    	AdjancyList list = new AdjancyList(vertices);
    	
    	Set<TripleBunch> edgedesc = extractEdge(map);
    	for(TripleBunch tripleBunch : edgedesc) {
    		ExtendedIterator<Triple> iter = tripleBunch.iterator();
    		while(iter.hasNext()) {
    			Triple triple = iter.next();
    			list.addEdge(triple.getSubject().toString(), triple.getObject().toString());
    		}
    	}
    	
		return list;
	}
	
    public static void sparql_query(String q, Model model) {
    	Query query = QueryFactory.create( q );
        QueryExecution qexec = QueryExecutionFactory.create( query, model );
        try {
            ResultSet results = qexec.execSelect();
            //ResultSetFormatter.out( results, model );
            while(results.hasNext()) {
            	QuerySolution solution = results.next();
            	Iterator<String> namesIter = solution.varNames();
            	while(namesIter.hasNext()) {
            		String name = namesIter.next();
            		log.debug(name);
            		log.info(solution.get(name));
            	}
            }
        } finally {
            qexec.close();
        }
    }
	
	public static Model sparql_construct(Model model) {
		String q = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			       "PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
				   "CONSTRUCT {?s ?p ?o} " + 
	               "WHERE {" +
				   " ?s ?p ?o ." +
	               " ?p rdf:type owl:ObjectProperty ." +
				   "}";
		Query query = QueryFactory.create( q );
        QueryExecution qexec = QueryExecutionFactory.create( query, model );
        try {
            Model results = qexec.execConstruct();
            results.write(System.out, "TURTLE");
            return results;
        } finally {
            qexec.close();
        }
	}
	
    public static void main(String[] args) {
    	InputStream in = ListSubjects.class.getClassLoader().getResourceAsStream("flight_simple.ttl");
    	Model model = ModelFactory.createDefaultModel();
    	model.read(in, null, "TTL");
    	
    	log.info("HardCore");
    	hardcore(model);
    	
    	log.info("SPARQL Construct");
    	Model resultModel = sparql_construct(model);
    	//hardcore(resultModel);
    	Set<String> vertices = getVertices(resultModel);
    	for(String vertice : vertices) {
    		log.info(vertice);
    	}
    	AdjancyMatrix matrix = getAdjancyMatrix(resultModel);
    	matrix.inspect();
    	
    	AdjancyList list = getAdjancyList(resultModel);
    	list.inspect();
    	
//    	log.info("SPARQL Query");
//    	String q = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
//			       "PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
//			       "PREFIX dbo: <http://dbpedia.org/ontology/> " +
//				   "SELECT ?s " + 
//	               "WHERE {" +
//				   " ?s rdf:type dbo:Place ." +
//				   "}";
//    	sparql_query(q, model);
    	
//    	System.out.println("======================");
//    	in = ListSubjects.class.getClassLoader().getResourceAsStream("fileB.ttl");
//    	model.read(in, null, "TTL");
//    	vertices = getVertices(model);
//    	for(String vertice : vertices) {
//    		log.info(vertice);
//    	}
    }
}
