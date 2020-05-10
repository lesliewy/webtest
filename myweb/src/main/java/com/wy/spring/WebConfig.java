package com.wy.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by leslie on 2020/3/21.
 */
@Configuration
public class WebConfig {

    /*
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/statics/jsp/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
    */
}
