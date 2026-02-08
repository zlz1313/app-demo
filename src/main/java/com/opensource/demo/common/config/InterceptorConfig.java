package com.opensource.demo.common.config;

import com.opensource.demo.infra.filter.ApiFilter;
import com.opensource.demo.infra.filter.LogFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<Filter> builderApiFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiFilter());
        registrationBean.addUrlPatterns("/openapi/*");
        registrationBean.setName("apiFilter");
        registrationBean.setOrder(-1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> builderLogFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/openapi/*");
        registrationBean.setName("logFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
