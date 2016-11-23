package com.delta.cs.rms.fuseki.services;

import com.delta.cs.rms.fuseki.helper.Fuseki2Utils;
import com.delta.cs.rms.fuseki.helper.HttpConst;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author tzuyichao
 */
public class Fuseki2Operator {
    private String host;
    private int port;
    
    // TODO: fuseki connection url, port to config file
    public static final String query_endpoint_pattern = "http://%s:%d/%s/query";
    public static final String update_endpoint_pattern = "http://%s:%d/%s/update";
    public static final String graph_store_endpoint_pattern = "http://%s:%d/%s/get";
    /**
     * 建立新dataset之類使用這個pattern
     */
    public static final String DATASET_MGRT_PATTERN1 = "http://%s:%d/$/datasets";
    /**
     * 針對既有dataset控制的API會使用這個pattern
     */
    public static final String DATASET_MGRT_PATTERN2 = "http://%s:%d/$/datasets/%s";
    
    public Fuseki2Operator() {
        this.host = "localhost";
        this.port = 3030;
    }
    
    public Fuseki2Operator(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public boolean createDataset(String id) {
        String url = String.format(DATASET_MGRT_PATTERN1, host, port);
        
        HttpPost post = new HttpPost(url);
        // TODO: refactoring me
        HttpClient httpclient = new DefaultHttpClient();
        
        try {
            post.addHeader(HttpConst.HEADER_CONTENTTYPE, HttpConst.FORM_URLENCODED);

            List <NameValuePair> nvps = new ArrayList <>();
            nvps.add(new BasicNameValuePair(Fuseki2Utils.DATASET_MGR_CREATE_NAME, id));
            nvps.add(new BasicNameValuePair(Fuseki2Utils.DATASET_MGR_CREATE_TYPE, Fuseki2Utils.JENA_TDB));

            post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

            HttpResponse response = httpclient.execute(post);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return true;
            } else {
                // HTTP Response status != 200
                //return "FAILED";
                // TODO: logging error
                switch(response.getStatusLine().getStatusCode()) {
                    case Fuseki2Utils.STATUS_NAME_ALREADY_REGISTERED:
                        // Error 409: Name already registered
                        return false;
                    default:
                        // TODO: log unknown error
                        return false;
                }
            }
        } catch(IOException e) {
            return false;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }
    
}
