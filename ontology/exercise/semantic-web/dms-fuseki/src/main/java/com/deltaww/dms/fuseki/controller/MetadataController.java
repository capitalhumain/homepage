package com.deltaww.dms.fuseki.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deltaww.dms.fuseki.model.APIResult;
import com.deltaww.dms.fuseki.model.ClientIdSupport;
import com.deltaww.dms.fuseki.service.MetadataService;

@RestController
@RequestMapping(value="/v2")
public class MetadataController {
	private static final Logger log = Logger.getLogger(MetadataController.class);
	
	@Autowired
	private MetadataService metadataService;
	
	@RequestMapping(value="/ping", method=RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public APIResult info() {
		APIResult apiResult = new APIResult();
		
		apiResult.setStatus(true);
		apiResult.setMessage(metadataService.info());
		
		return apiResult;
	}
	
	/**
	 * id, type, library_space, graph_id(, createdTime, status)
	 * 
	 * @return
	 */
	@RequestMapping(value="/dataset", method=RequestMethod.PUT)
	public APIResult createDataset(@RequestBody ClientIdSupport clientIdSupport) {
		log.info("Create Dataset");
		
		log.info(clientIdSupport.toString());
		
		return metadataService.createDatasetMetadata(clientIdSupport);
	}
	
// not support update all metadata
//	@RequestMapping(value="/dataset", method=RequestMethod.POST)
//	public APIResult updateDataset() {
//		log.info("Update Dataset");
//		APIResult apiResult = new APIResult();
//		
//		apiResult.setStatus(true);
//		apiResult.setMessage("Success");
//		
//		return apiResult;
//	}
	
	@RequestMapping(value="/dataset", method=RequestMethod.GET)
	public APIResult listDataset(@RequestBody ClientIdSupport clientIdSupport) {
		log.info("Get Dataset");
		APIResult apiResult = new APIResult();
		
		apiResult.setStatus(true);
		apiResult.setMessage("Success");
		
		return apiResult;
	}
	
	@RequestMapping(value="/dataset", method=RequestMethod.DELETE)
	public APIResult deleteDataset(@RequestBody ClientIdSupport clientIdSupport) {
		log.info("Delete Dataset");
		APIResult apiResult = new APIResult();
		
		apiResult.setStatus(true);
		apiResult.setMessage("Success");
		
		return apiResult;
	}
	
	@RequestMapping(value="/knowledgebase", method=RequestMethod.PUT)
	public APIResult createKB(@RequestBody ClientIdSupport clientIdSupport) {
		return new APIResult();
	}
	
// not support update all metadata
//	@RequestMapping(value="/knowledgebase", method=RequestMethod.POST)
//	public APIResult updateKB() {
//		return new APIResult();
//	}
	
	@RequestMapping(value="/knowledgebase", method=RequestMethod.GET)
	public APIResult listKB(@RequestBody ClientIdSupport clientIdSupport) {
		return new APIResult();
	}
	
	@RequestMapping(value="/knowledgebase", method=RequestMethod.DELETE)
	public APIResult deleteKB(@RequestBody ClientIdSupport clientIdSupport) {
		return new APIResult();
	}
}
