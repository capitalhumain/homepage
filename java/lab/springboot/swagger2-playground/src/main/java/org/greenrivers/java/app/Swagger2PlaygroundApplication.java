package org.greenrivers.java.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication(scanBasePackages = {"org.greenrivers.java"})
@EnableSwagger2
public class Swagger2PlaygroundApplication {
	// May be move to spring configure
	private static final String AppTitle = "Store Service";
	private static final String AppDesc = "Online Store RESTFul Service";
	private static final String AppVer = "1.0.0";
	private static final String TermsOfServiceUrl = "";
	private static final String License = "GPLv3";
	private static final String LicenseUrl = "https://www.gnu.org/licenses/gpl-3.0.en.html";

	public static void main(String[] args) {
		SpringApplication.run(Swagger2PlaygroundApplication.class, args);
	}


	@Bean
	public Docket swaggerApiDocInit() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.greenrivers.java.controller"))
				.build();
		docket.apiInfo(new ApiInfo(AppTitle, AppDesc, AppVer, TermsOfServiceUrl, ApiInfo.DEFAULT_CONTACT, License, LicenseUrl, Collections.EMPTY_LIST));
		return docket;
	}
}
