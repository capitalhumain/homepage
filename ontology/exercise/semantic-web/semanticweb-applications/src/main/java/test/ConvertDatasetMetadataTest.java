package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import model.DataSetMetadata;
import model.Predicate;

public class ConvertDatasetMetadataTest {
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static String generateGetterName(String fieldName) {
		return "get" + StringUtils.capitalize(fieldName);
	}
	
	private static String getValue(DataSetMetadata obj, Field field) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
	
	private static String generateTriple(DataSetMetadata obj, Field field, Annotation ann) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DataSetMetadata test = new DataSetMetadata();
		test.setId("testid-00001");
		test.setType("General");
		//test.setLabel("Demo DataSet 1");
		test.setCreatedTime(new Date());
		
		System.out.println("Start");
		
		Field[] fields = test.getClass().getDeclaredFields();
		//System.out.println("Fields: " + fields.length);
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
