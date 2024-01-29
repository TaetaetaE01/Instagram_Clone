package com.example.instargram_clone.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Instagram_Clone",
                description = "인스타 그램 클론 코딩 API 명세서",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi OpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("Instagram_Clone")
                .pathsToMatch(paths)
                .build();
    }


//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.instargram_clone"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("인스타그램 서버 API")
//                .description("인스타그램 클론 코딩")
//                .version("1.0.0")
//                .build();
//    }
}
