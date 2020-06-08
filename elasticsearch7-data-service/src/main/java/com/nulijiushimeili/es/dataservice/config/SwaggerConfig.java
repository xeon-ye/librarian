package com.nulijiushimeili.es.dataservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // 启动时就要加载
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .select()
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());//.globalOperationParameters(pars);

    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder().title("elasticsearch server")
                .description("****")
                .termsOfServiceUrl("http://www.baidu.com")
                .version("1.0").build();
    }
}
