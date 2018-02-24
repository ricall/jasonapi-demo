
package au.org.rma.jsonapi.jsonapidemo.configuration;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URI;
import java.util.Collections;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("au.org.rma.jsonapi.jsonapidemo"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .directModelSubstitute(URI.class, String.class);
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfo(
                "JSON API Demo",
                "Testing custom serialisation of JSON API objects",
                "1.0.0",
                "Terms of Service URL",
                new Contact("Richard Allwood", "https://github.com/richard-allwood", "richard.m.allwood@gmail.com"),
                "License of API",
                "API License URL",
                Collections.emptyList());
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(true)
                .defaultModelsExpandDepth(10)
                .defaultModelExpandDepth(10)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(true)
                .docExpansion(DocExpansion.FULL)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(true)
                .tagsSorter(TagsSorter.ALPHA)
                .validatorUrl(null)
                .build();
    }



}