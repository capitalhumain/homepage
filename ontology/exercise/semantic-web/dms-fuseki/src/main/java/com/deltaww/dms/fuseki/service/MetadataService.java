package com.deltaww.dms.fuseki.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.deltaww.dms.fuseki.helper.OntologyModelToSPARQLGraphConverter;
import com.deltaww.dms.fuseki.helper.RemoteSPARQLUpdate;
import com.deltaww.dms.fuseki.model.APIResult;
import com.deltaww.dms.fuseki.model.ClientIdSupport;
import com.deltaww.dms.fuseki.model.DatasetMetadata;

@Service
public class MetadataService {
	private static final Logger log = Logger.getLogger(MetadataService.class);
	private static final String TEMPLATE_FUSEKI_ADDON_CREATE_DATASET = "{\"clientId\": \"%s\", \"type\": \"%s\", \"command\": \"create_dataset\"}";
	
	public String info() {
		DatasetMetadata test = new DatasetMetadata();
		
		test.setId("ABCASSSS");
		test.setLabel("This is test dataset");
		test.setLibrarySpace("Library");
		test.setCreatedTimestamp(new Date());
		
		try {
			log.info(OntologyModelToSPARQLGraphConverter.generateGraph(test));
			String sparql = OntologyModelToSPARQLGraphConverter.generateSPARQL(OntologyModelToSPARQLGraphConverter.SPARQL_Insert_Template, test);
			log.info(sparql);
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			log.error("create graph failed", e);
		}
		
		return "It works";
	}
	
	public APIResult createDatasetMetadata(ClientIdSupport clientIdSupport) {
		if(clientIdSupport == null) {
			throw new IllegalArgumentException("input object cannot be null");
		}
		APIResult result = new APIResult();
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			// TODO: Refactoring Me
			String createDataSetJSON = String.format(TEMPLATE_FUSEKI_ADDON_CREATE_DATASET, clientIdSupport.getClientId(), clientIdSupport.getType());
			log.info(createDataSetJSON);
			HttpPost post = new HttpPost("http://localhost:3030/clientIdSupport");
			post.addHeader("Content-Type", "application/json");
			post.setEntity(new StringEntity(createDataSetJSON));
			HttpResponse response = client.execute(post);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				String dsuuid = responseHandler.handleResponse(response).trim();
				log.info("Dataset uuid from Fuseki-Addon: " + dsuuid);
				
				DatasetMetadata obj = new DatasetMetadata();
				obj.setId(dsuuid);
				obj.setType(clientIdSupport.getType());
				obj.setLabel(clientIdSupport.getType());
				obj.setLibrarySpace(clientIdSupport.getLibrarySpace());
				obj.setGraphId(clientIdSupport.getGraphId());
				obj.setCreatedTimestamp(new Date());
				obj.setStatus("Accept");
				String sparql = OntologyModelToSPARQLGraphConverter.generateSPARQL(OntologyModelToSPARQLGraphConverter.SPARQL_Insert_Template, obj);
				
				RemoteSPARQLUpdate.execute("http://localhost:3030/system/update", sparql);
				
				result.setStatus(true);
				result.setMessage("Success");
				Map<String, Object> payload = new HashMap<>();
				payload.put("datasetId", dsuuid);
				result.setPayload(payload);
				return result;
			} else {
				result.setStatus(false);
				result.setMessage("Create Dataset Failed");
				return result;
			}
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | IOException e) {
			// TODO Auto-generated catch block
			result.setStatus(false);
			result.setMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			if(client != null) {
				try { client.close(); } catch(Exception e) {}
			}
		}
		result.setStatus(false);
		return result;
	}
	
	public APIResult createKnowledgebaseMetadata(ClientIdSupport clientIdSupport) {
		return createDatasetMetadata(clientIdSupport);
	}
	
}
