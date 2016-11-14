package model;

import java.util.Date;

public class DataSetMetadata implements OntologyModel {
	private String id;
	@Predicate(uri="http://dms.deltawww.com/ts/type")
	private String type;
	@Predicate(uri="http://www.w3.org/2000/01/rdf-schema#label")
	private String label;
	@Predicate(uri="http://dms.deltawww.com/ts/createdTime")
	private Date createdTime;
	
	public DataSetMetadata() {}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getCreatedTime() {
		return createdTime;
	}

	@Override
	public void setSubjectURI(String uri) {
		this.id = uri;
	}

	@Override
	public String getSubjectURI() {
		return "<http://dms.deltawww.com/ts/" + id + ">";
	}
}
