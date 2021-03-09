package com.smart.swager.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // 方法需要有ApiOperation注解才能生存接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 路径使用any风格
                .paths(PathSelectors.any())
                .build()
                // 如何保护我们的Api，有三种验证（ApiKey, BasicAuth, OAuth）
               // .securitySchemes(ApiKey())
                // 接口文档的基本信息
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("派大星构建api文档标题")
                .description("xxx项目开发文档")
                .termsOfServiceUrl("127.0.0.1/8080/user/test")
                .version("1.0")
                .build();

    }
}

