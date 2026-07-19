package com.abhinav.springBootFilters.configurations;

import com.abhinav.springBootFilters.filters.DummyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Another method to create the beans for filters

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<DummyFilter> getDummyFilterBean()
    {
        FilterRegistrationBean<DummyFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new DummyFilter());
//        filterFilterRegistrationBean.setOrder(1);

        filterFilterRegistrationBean.addUrlPatterns("/api/* , /admin/*"); // It means that dummy filter will only work for the APIs with URL starting with api.
        return  filterFilterRegistrationBean;

    }
}
