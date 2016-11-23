package com.deltaww.dms.fuseki.model;

import java.util.Date;

import com.deltaww.dms.fuseki.model.annotation.Predicate;

/**
 * 覺得Dataset和Knowledge base可以共用才對
 * 
 * 
 * @author tzuyichao
 *
 */
public class DatasetMetadata implements OntologyModel {
	public static final String DATASET_PREFIX = "http://dms.deltaww.com/dataset/";
	@Predicate(uri="http://dms.deltaww.com/metadata/id")
	private String id;
	@Predicate(uri="http://dms.deltaww.com/metadata/type")
	private String type;
	@Predicate(uri="http://dms.deltaww.com/metadata/librarySpace")
	private String librarySpace;
	@Predicate(uri="http://dms.deltaww.com/metadata/graphId")
	private String graphId;
	@Predicate(uri="http://www.w3.org/2000/01/rdf-schema#label")
	private String label;
	@Predicate(uri="http://www.w3.org/2000/01/rdf-schema#comment")
	private String comment;
	@Predicate(uri="http://dms.deltaww.com/metadata/status")
	private String status;
	@Predicate(uri="http://dms.deltaww.com/metadata/createdTime")
	private Date createdTimestamp;
	private String subjectURI = null;
	
	public DatasetMetadata() {}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setLibrarySpace(String librarySpace) {
		this.librarySpace = librarySpace;
	}
	public String getLibrarySpace() {
		return librarySpace;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public String getGraphId() {
		return graphId;
	}
	public void setGraphId(String graphId) {
		this.graphId = graphId;
	}

	@Override
	public String getSubjectURI() {
		if(subjectURI == null) {
			subjectURI = "<" + DATASET_PREFIX + id + ">";
		}
		return subjectURI;
	}
}
