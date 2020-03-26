package com.ps;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ProductApplication.class);
		Environment environment = app.run(args).getEnvironment();
		System.out.println("Application Started with port " + environment.getProperty("server.port"));
	}

	@Bean
	public Docket demoApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.protocols(new HashSet<>(Arrays.asList("http", "https"))).select()
				.apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.build()
				.apiInfo(buildApiInfo());
	}

	public ApiInfo buildApiInfo() {
		return new ApiInfoBuilder().title("Product Management Application")
				.description("curd operation for product management")
				.license("License by Dhananjay")
				.licenseUrl("www.realspeed.com/license")
				.version("1.0.0")
				.termsOfServiceUrl("www.realspeed.com/terms")
				.contact(new Contact("Realspeed Team", "www.realspeed.com", "contactus@realspeed.com"))
				.build();
	}

}
