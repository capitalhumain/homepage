package com.deltaww.dms.fuseki.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deltaww.dms.fuseki.model.APIResult;
import com.deltaww.dms.fuseki.service.MetadataService;

@RestController
@RequestMapping(value="/v2")
public class SearchController {
	private static final Logger log = Logger.getLogger(SearchController.class);
	
	@Autowired
	private MetadataService metadataService;
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public APIResult search(@RequestParam(name="keyword") String keyword) {
		log.info(String.format("dms-fuseki simple search api invoked: keyword[%s]", keyword));
		
		Set<String> result = metadataService.searchDatasetIdAgainstComment(keyword);
		
		APIResult apiResult = new APIResult();
		
		apiResult.setStatus(true);
		apiResult.setMessage("Success");
		Map<String, Object> payload = new HashMap<>();
		payload.put("keyword", keyword);
		payload.put("DatasetIdArray", result);
		apiResult.setPayload(payload);
		
		return apiResult;
	}
}
