//package com.group1.EnglishApp.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
//@Configuration
//public class PageableConfiguration implements WebMvcConfigurer {
//    private int size=1;
//
//    private int page=0;
//
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
//        resolver.setFallbackPageable(PageRequest.of(page, size));
//        resolvers.add(resolver);
//        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
//    }
//}
