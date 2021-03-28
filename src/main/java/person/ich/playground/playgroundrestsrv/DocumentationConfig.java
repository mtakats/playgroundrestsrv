package person.ich.playground.playgroundrestsrv;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


@Configuration
public class DocumentationConfig {
    @Bean
    public Docket v1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/v1/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Playground REST service API",
                "This service is used as demo for some features",
                "0.0.1",
                "Term of service",
                new Contact("Example Author", "http://www.example.com", "example.author@example.com"),
                "Example license",
                "http://example.com",
                Collections.EMPTY_LIST
        );
    }
}
