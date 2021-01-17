package com.hcteleweb.teleweb.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig // extends WebMvcConfigurationSupport
{

    /**
     * com.hcteleweb 하위에 config package를 생성하고 SwaggerConfiguration파일을 작성합니다.
     * basePackage(“com.hcteleweb”)).paths(PathSelectors.any())
     * 
     * com.hcteleweb 하단의 내용을 읽어 mapping된 resource들을 문서화시킵니다.
     * 아래와 같이 작성해서 v1으로 시작하는 resource들만 문서화 시킬수도 있습니다.
     * PathSelectors.ant(“/ v1/**”)
     * 
     * swaggerInfo를 세팅하여 간단하게 문서에 대한 설명과 작성자 정보를 노출시킬 수 있습니다.
     * 
     * @return
     */
    @Bean
    public Docket telewebApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getTelewebInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.hcteleweb") )
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false); //기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음
    }
    
    private ApiInfo getTelewebInfo()
    {
        return new ApiInfoBuilder()
                .title("Spring API Documentation - TELEWEB 4.5 adv.")
                .description("개발시 사용되는 서버 API - TELEWEB 4.5 advenced 버전에 대한 연동 문서입니다.")
                .license("HANKOOK Corporation Inc.")
                .licenseUrl("http://www.besthc.co.kr")
                .version("4.5.0")
                .build();
    }
}
