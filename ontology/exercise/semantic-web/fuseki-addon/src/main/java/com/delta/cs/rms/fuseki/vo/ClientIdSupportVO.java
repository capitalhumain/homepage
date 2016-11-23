package com.delta.cs.rms.fuseki.vo;

import com.delta.cs.rms.fuseki.servlets.ClientIdSupportServlet;

/**
 *
 * @author tzuyichao
 */
public class ClientIdSupportVO {
    public String clientId;
    public String type;
    public String dataset;
    public String command;
    public String named;
    
    public boolean isValid() {
        if(command == null || "".equals(command)) {
            return false;
        }
        if(command.equals(ClientIdSupportServlet.CMD_LIST_CLIENTID)) {
            return true;
        }
        if(command.equals(ClientIdSupportServlet.CMD_RETRIEVAL_NAMED_GRAPH)) {
            return (dataset != null && !"".equals(dataset) && named != null && !"".equals(named));
        }
        if(command.equals(ClientIdSupportServlet.CMD_RETRIEVAL_DEFAULT_GRAPH)) {
            return (dataset != null && !"".equals(dataset));
        }
        if(command.equals(ClientIdSupportServlet.CMD_DROP_DATASET)) {
            return (type != null && !"".equals(type) && dataset != null && !"".equals(dataset));
        }
        return (type != null && !"".equals(type) && clientId != null && !"".equals(clientId));
    }
    
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("clientId:").append(clientId).append("\n");
        ret.append("type:").append(type).append("\n");
        ret.append("dataset:").append(dataset).append("\n");
        ret.append("command:").append(command).append("\n");
        return ret.toString();
    }
}
