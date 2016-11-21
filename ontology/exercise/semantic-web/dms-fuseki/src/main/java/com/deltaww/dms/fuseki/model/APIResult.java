package com.deltaww.dms.fuseki.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResult {
    private boolean status;
    private String message;
    private Map<String, Object> payload;
    
    public APIResult() {
    }
    
    public void setStatus(boolean status) {
    	this.status = status;
    }
    public boolean getStatus() {
    	return status;
    }
    public void setMessage(String message) {
    	this.message = message;
    }
    public String getMessage() {
    	return message;
    }
    public void setPayload(Map<String, Object> payload) {
    	this.payload = payload;
    }
    public Map<String, Object> getPayload() {
    	return payload;
    }
}
