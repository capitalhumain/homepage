package com.deltaww.dms.fuseki.helper;

import org.apache.http.client.methods.HttpPost;

public class HTTPClientHelper<T> {
	public T postRawJson(String url, String jsonStr) {
		HttpPost post = new HttpPost(url);
		return null;
	}
}
