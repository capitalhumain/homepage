package com.deltaww.dms.fuseki.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.deltaww.dms.fuseki.model.OntologyModel;
import com.deltaww.dms.fuseki.model.annotation.Predicate;


public class OntologyModelToSPARQLGraphConverter {
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Ignore Prefix for now create dataset use
	public static final String SPARQL_Insert_Template = "insert data { %s }";
	public static final String SPARQL_Delete_by_id_Template = "delete { ?s ?p ?o . } where { " +
													          " ?s ?p ?o . " +
													          " ?s <http://dms.deltaww.com/metadata/id> \"%s\" . " +
													          "}";
	
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
	
	public static String generateGraph(OntologyModel obj) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
	
	public static String generateSPARQL(String template, String graph) {
		return String.format(template, graph);
	}

	public static String generateSPARQL(String template, OntologyModel obj) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return String.format(template, generateGraph(obj));
	}
}
