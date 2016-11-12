package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import model.DataSetMetadata;
import model.Predicate;

public class ConvertDatasetMetadataTest {
	
	private static String getValue(DataSetMetadata obj, Field field) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = obj.getClass().getDeclaredMethod("get" + StringUtils.capitalize(field.getName()), new Class[0]);
		Object result = method.invoke(obj, new Object[0]);
		return result.toString();
	}
	
	private static String generateTriple(DataSetMetadata obj, Field field, Annotation ann) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuilder triple = new StringBuilder();
		
		if(ann instanceof Predicate) {
			Predicate predicate = (Predicate) ann;
			triple.append(obj.getSubjectURI());
			triple.append(" <");
			triple.append(predicate.uri());
			triple.append("> \"");
			triple.append(getValue(obj, field));
			triple.append("\" .");
		}
		
		return triple.toString();
	}

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DataSetMetadata test = new DataSetMetadata();
		test.setId("testid-00001");
		test.setType("General");
		test.setLabel("Demo DataSet 1");
		
		System.out.println("Start");
		
		Field[] fields = test.getClass().getDeclaredFields();
		System.out.println("Fields: " + fields.length);
		for(Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for(Annotation ann : annotations) {
				String triple = generateTriple(test, field, ann);
				System.out.println(triple);
			}
		}
		System.out.println("Done");
	}

}
