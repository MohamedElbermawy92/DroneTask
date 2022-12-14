package com.musala.drone.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {

        Contact contact = new Contact("Drone", "http://www.musala.com/", "mohamed.elbermawy92@gmail.com");

        List<VendorExtension> vendorExtensions = new ArrayList<>();

        ApiInfo apiInfo = new ApiInfo("drone Documentation", "This pages documents drone task endpoints", "1.0",
                "http://www.musala.com/", contact, "", "", vendorExtensions);

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.musala.drone.controller"))
                .paths(PathSelectors.any())
                .build();

        return docket;

    }


}
