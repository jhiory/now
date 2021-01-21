package doit.now.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    private HandlerInterceptor handlerInterceptor;
    @Value("${spring.mvc.locale}")
    Locale locale = null;

    @Value("${spring.messages.basename}")
    String messagesBasename = null;

    @Value("${spring.messages.encoding}")
    String messagesEncoding = null;

    @Value("${spring.messages.cache-duration.seconds}")
    int messagesCacheSeconds;

    private final int MAX_AGE_SECS = 3600;

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
//                .maxAge(MAX_AGE_SECS);

//    }
    
private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
        "classpath:/META-INF/resources/", "classpath:/resources/",
        "classpath:/static/", "classpath:/public/" };


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/m/**")   // 핸들러 추가
////                .addResourceLocations("classpath:/m/") //  클래스패스 설정시 끝에 꼭 / 넣어주자.
////                .setCachePeriod(20);                   // 초단위
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/");
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(handlerInterceptor)
                .addPathPatterns("/**/web/**")
                .excludePathPatterns("/common/web/blank")
                .excludePathPatterns("/common/web/view")
                .excludePathPatterns("/api/ProjectCommon/**")	//SIGNUP에서 사용
                .excludePathPatterns("/error/web/**")
                .excludePathPatterns("/error/web/**")
                .excludePathPatterns("/static/**")
        ;
    }
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(locale);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(messagesBasename);
        messageSource.setDefaultEncoding(messagesEncoding);
        messageSource.setCacheSeconds(messagesCacheSeconds);
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor getMessageSourceAccessor(){
        ReloadableResourceBundleMessageSource m = messageSource();
        return new MessageSourceAccessor(m);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
