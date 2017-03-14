package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import model.DataSetMetadata;
import model.OntologyModel;
import model.Predicate;

/**
 * 
 * Delete all createdTime triples whose subject has rdfs:label equals to "Demo DataSet 1"
 * <pre>
delete { ?s <http://dms.deltawww.com/ts/createdTime> ?o }
where {
  ?s <http://www.w3.org/2000/01/rdf-schema#label> "Demo DataSet 1" .
  ?s <http://dms.deltawww.com/ts/createdTime> ?o .
}
 * </pre>
 * <pre>
delete { <http://dms.deltaww.com/ts/testid-00001> ?p ?o . }
where {
  <http://dms.deltaww.com/ts/testid-00001> ?p ?o .
}
 * </pre>
 * 
 * <pre>
delete { ?s ?p ?o . }
where {
  ?s ?p ?o .
  ?s <http://dms.deltaww.com/metadata/id> "testid-00001" .
}
 * </pre>
 * 
 * @author tzuyichao
 *
 */
public class ConvertDatasetMetadataTest {
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Ignore Prefix for now create dataset use
	public static final String Insert_Template = "insert data { %s }";
	// Update dataset
	public static final String Update_Dataset_Template_Pre0 = "delete { ?s <http://dms.deltawww.com/ts/createdTime> ?o } " +
			"where { " +
			"?s <http://www.w3.org/2000/01/rdf-schema#label> \"%s\" . " +
			"?s <http://dms.deltawww.com/ts/createdTime> ?o . }";
	
	private static String generateGetterName(String fieldName) {
		return "get" + StringUtils.capitalize(fieldName);
	}
	
	private static String getValue(OntologyModel obj, Field field) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(field.getType() == java.lang.String.class) {
			Method method = obj.getClass().getDeclaredMethod(generateGetterName(field.getName()), new Class[0]);
			Object result = method.invoke(obj, new Object[0]);
			if(result == null) {
				return null;
			} else {
				return result.toString();
			}
		} if (field.getType() == java.util.Date.class) {
			Method method = obj.getClass().getDeclaredMethod(generateGetterName(field.getName()), new Class[0]);
			Object result = method.invoke(obj, new Object[0]);
			if(result == null) {
				return null;
			} else {
				return format.format((Date) result);
			}
		} else {
			// Should be not support exception
			return null;
		}
	}
	
	private static String generateTriple(OntologyModel obj, Field field, Annotation ann) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuilder triple = new StringBuilder();
		String val = getValue(obj, field);
		
		if(val != null) {
			if(ann instanceof Predicate) {
				Predicate predicate = (Predicate) ann;
				triple.append(obj.getSubjectURI());
				triple.append(" <");
				triple.append(predicate.uri());
				triple.append("> \"");
				triple.append(val);
				triple.append("\" .");
			}
		}
		
		return triple.toString();
	}
	
	private static String generateGraph(OntologyModel obj) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuilder graphStr = new StringBuilder();
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for(Annotation ann : annotations) {
				String triple = generateTriple(obj, field, ann);
				if(!triple.equals("")) {
					graphStr.append(triple).append("\n");
				}
			}
		}
		return graphStr.toString();
	}
	
	@SuppressWarnings("unused")
	private static String generateSPARQLUpdate_Insert(OntologyModel obj) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return String.format(Insert_Template, generateGraph(obj));
	}
	
	private static String generateSPARQLUpdate_Insert(String graph) {
		return String.format(Insert_Template, graph);
	}

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DataSetMetadata test = new DataSetMetadata();
		test.setId("testid-00001");
		test.setType("General");
		test.setLabel("Demo DataSet 1");
		test.setCreatedTime(new Date());
		
		System.out.println("Start");
		
		String graph = generateGraph(test);
		System.out.println(graph);
		
		String sparql = generateSPARQLUpdate_Insert(graph);
		System.out.println(sparql);
		
		if(SPARQLServiceCheck.isRunning("http://localhost:3030/system/query")) {
		    RemoteSPARQLUpdate.execute("http://localhost:3030/system/update", sparql);
		} else {
			System.out.println("Fuseki SPARQL HTTP Endpoint is not running");
		}
		System.out.println("Done");
	}

}
