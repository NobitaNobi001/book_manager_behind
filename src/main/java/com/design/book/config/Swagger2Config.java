package com.design.book.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ezuy
 * @date 21/4/8 12:02
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket adminApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/.*")))
                .build();
    }
//
//    @Bean
//    public Docket webApiConfig() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("webApi")
//                .apiInfo(webApiInfo())
//                .select()
//                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
//                .build();
//    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("图书管理系统API文档")
                .description("本文档描述了图书管理系统的各个模块的接口调用方式")
                .version("v1.0")
                .contact(new Contact("ezuy", "http://www.baidu.com", "frank.ezuy@qq.com"))
                .build();
    }

//    private ApiInfo webApiInfo() {
//        return new ApiInfoBuilder()
//                .title("校园评审网站API文档")
//                .description("本文档描述了校园评审网站的各个模块的接口调用方式")
//                .version("v1.0")
//                .contact(new Contact("ezuy", "http://www.baidu.com", "frank.ezuy@qq.com"))
//                .build();
//    }
}
