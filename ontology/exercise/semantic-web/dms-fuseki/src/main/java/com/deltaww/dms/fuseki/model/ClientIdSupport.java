package com.deltaww.dms.fuseki.model;

public class ClientIdSupport {
	private String clientId;
	private String type;
	private String librarySpace;
	private String graphId;
	private String command;
	private String dataset;
	
	public ClientIdSupport() {}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLibrarySpace() {
		return librarySpace;
	}

	public void setLibrarySpace(String librarySpace) {
		this.librarySpace = librarySpace;
	}
	
	public String getGraphId() {
		return graphId;
	}

	public void setGraphId(String graphId) {
		this.graphId = graphId;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	
	@Override
	public String toString() {
		return String.format("ClientIdSupport (clientId: %s, type: %s, librarySpace: %s, graphId: %s, command: %s)", clientId, type, librarySpace, graphId, command);
	}
}
