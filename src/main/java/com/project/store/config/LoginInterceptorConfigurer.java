package com.project.store.config;

import com.project.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器拦截器的注册
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    //addInterceptors将自定义的拦截器进行注册
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置白名单->不需要登录就可访问的页面(login.html...)
        List<String> patterns=new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/products/**");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/users/districts/**");
        InterceptorRegistration registration = registry.addInterceptor(interceptor);
        registration.addPathPatterns("/**").excludePathPatterns(patterns);
    }
}















