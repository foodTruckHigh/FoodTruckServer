package com.high.food.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.high.food.common.intercepter.AuthIntercepterHandler;

@Configuration 
@EnableWebMvc 
public class IntercepterConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private AuthIntercepterHandler authIntercepterHandler;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authIntercepterHandler);
    }
}