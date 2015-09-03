package model;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Triple;
import org.apache.jena.graph.impl.TripleStore;
import org.apache.jena.mem.ArrayBunch;
import org.apache.jena.mem.BunchMap;
import org.apache.jena.mem.GraphMem;
import org.apache.jena.mem.GraphTripleStoreMem;
import org.apache.jena.mem.NodeToTriplesMapMem;
import org.apache.jena.mem.TripleBunch;

public class DefaultModelExp1 {
	private static Logger logger = LoggerFactory.getLogger(DefaultModelExp1.class);
	public static void dumpExtendedIterator(final ExtendedIterator<Object> iter) {
		while(iter.hasNext()) {
			Object obj = iter.next();
			logger.info("\t" + obj);
		}
	}
	
	public static void dumpBunchMap(final BunchMap map) {
		ExtendedIterator<Object> iter = map.keyIterator();
		while(iter.hasNext()) {
			Object key = iter.next();
			System.out.println(String.format(">>> %s", key));
			TripleBunch tripleBunch = map.get(key);
			if(tripleBunch instanceof ArrayBunch) {
				ArrayBunch array = (ArrayBunch) tripleBunch;
				ExtendedIterator<Triple> tripleIter = array.iterator();
				while(tripleIter.hasNext()) {
					Triple triple = tripleIter.next();
					System.out.println(String.format("\t%s", triple));
				}
			} else {
			    System.out.println(tripleBunch);
			}
		}
	}

	public static void main(String[] args) {
		InputStream in = DefaultModelExp1.class.getClassLoader().getResourceAsStream("fileA.ttl");
		Model model = ModelFactory.createDefaultModel();
		model.read(in, null, "TTL");
		Graph g = model.getGraph();
		logger.info(g.toString());
		if(g instanceof GraphMem) {
			GraphMem graphMem = (GraphMem) g;
			TripleStore store = graphMem.store;
			logger.info(String.format("%s", store));
			GraphTripleStoreMem storemem = (GraphTripleStoreMem) store;
			
			NodeToTriplesMapMem subjects = storemem.getSubjects();
			logger.info(String.format("Subjects: %s", subjects.bunchMap));
			dumpBunchMap(subjects.bunchMap);
			
			NodeToTriplesMapMem predicates = storemem.getPredicates();
			logger.info(String.format("Predicates: %s", predicates.bunchMap));
			dumpBunchMap(predicates.bunchMap);
			
			NodeToTriplesMapMem objects = storemem.getObjects();
			logger.info(String.format("Objects: %s", objects.bunchMap));
			dumpBunchMap(objects.bunchMap);
		} else {
			logger.error("Is not GraphMem instance, not support now");
		}
	}

}
