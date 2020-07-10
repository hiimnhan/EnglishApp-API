package com.group1.EnglishApp.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Hai Dang
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Value("${application.version}")
    private String version;

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build()
                .pathProvider(new CustomPathProvider())
                .globalOperationParameters(commonParameters())
                .produces(produces())
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(Pageable.class)
                .apiInfo(infoData());
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder().build();
    }

    private ApiInfo infoData() {
        return new ApiInfoBuilder()
                .title("Group 1 - English Application")
                .description("Group 1 - English Application")
                .license("Group 1 License")
                .version(version)
                .build();
    }

    private Set<String> produces() {
        Set<String> produces = new HashSet<>();
        produces.add(MediaType.APPLICATION_JSON_VALUE);
        return produces;
    }

    private List<Parameter> commonParameters() {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new ParameterBuilder()
                .name("access_token")
                .description("Token for authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("query")
                .required(true)
                .build());

        return parameters;
    }

    public class CustomPathProvider extends AbstractPathProvider {

        @Override
        protected String applicationPath() {
            return contextPath;
        }

        @Override
        protected String getDocumentationPath() {
            return contextPath;
        }
    }
}
