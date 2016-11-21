package com.deltaww.dms.fuseki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DMSFusekiApplication {
	// 我實在厭倦設定檔然後追加設定檔放哪的問題
	public static final String FUSEKI_UPDATE_HTTP_ENDPOINT_TEMPLATE = "http://localhost:3030/%s/update";
	public static final String FUSEKI_QUERY_HTTP_ENDPOINT_TEMPLATE = "http://localhost:3030/%s/query";

	public static void main(String[] args) {
		SpringApplication.run(DMSFusekiApplication.class, args);
	}
}
