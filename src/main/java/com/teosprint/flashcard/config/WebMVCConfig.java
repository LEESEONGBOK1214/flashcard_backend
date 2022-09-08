package com.teosprint.flashcard.config;//package com.jsol.mcall.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    // react 파일 배포하기 위해 추가된 설정
    // thymeleaf? 추가 안 하면 template에서 반환을 못 해서..
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**") //1
                .addResourceLocations("classpath:/templates/"
//                        , "classpath:/static/"
                ) //2
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES)); //3

        /*
        1 : / 를 시작으로 하는 모든 요청을 다룬다는 것을 의미합니다.
        2 : 1번에 해당하는 요청을 처리할 자원을 찾을 위치를 나타냅니다.
        3 : 요청에 대한 Cache를 10분으로 설정
         */
    }


}