package com.narra.learn.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Slf4jMDCFilterConfiguration {

	public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "Slf4jMDCFilter.UUID";
	private String mdcTokenKey = DEFAULT_MDC_UUID_TOKEN_KEY;
	private String requestHeader = null;

	@Bean
	public FilterRegistrationBean<Slf4jMDCFilter> servletRegistrationBean() {
		final FilterRegistrationBean<Slf4jMDCFilter> registrationBean = new FilterRegistrationBean<>();
		final Slf4jMDCFilter log4jMDCFilterFilter = new Slf4jMDCFilter(mdcTokenKey, requestHeader);
		registrationBean.setFilter(log4jMDCFilterFilter);
		registrationBean.setOrder(2);
		return registrationBean;
	}
}