package com.delta.cs.rms.fuseki.helper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author tzuyichao
 */
public final class Fuseki2Utils {
    public static String makeRegistryKey(String name) {
        if(name.startsWith("/")) {
            return name;
        } else {
            return String.format("/%s", name);
        }
    }
    
    public static final int STATUS_NAME_ALREADY_REGISTERED = 409;

    public static final String SPARQL_UPDATE_PARAMETER_NAME = "update";

    public static final String SPARQL_QUERY_PARAMETER_NAME = "query";
    
    public static final String DATASET_MGR_CREATE_NAME = "dbName";
    
    public static final String DATASET_MGR_CREATE_TYPE = "dbType";
    
    public static final String JENA_TDB = "tdb";

    public static boolean parseFuseki2SPARQLUpdateHTMLResult(String html) {
        try {
            SAXParserFactory _SAXParserFactory = SAXParserFactory.newInstance();
            Fuseki2Utils.SAXHandler handler = new Fuseki2Utils.SAXHandler();
            _SAXParserFactory.newSAXParser().parse(new InputSource(new StringReader(html)), handler);
            return handler.isSuccess;
        } catch(IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return false;
    }

    static class SAXHandler extends DefaultHandler {

        private Stack<String> elementStack = new Stack<>();
        public boolean isSuccess = false;

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            this.elementStack.push(qName);
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            this.elementStack.pop();
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            String value = new String(ch, start, length).trim();
            if ("h1".equals(currentElement())) {
                if ("Success".equals(value)) {
                    isSuccess = true;
                }
            }
        }

        private String currentElement() {
            return this.elementStack.peek();
        }

        public boolean isSuccess() {
            return isSuccess;
        }
    }
}
