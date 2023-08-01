package com.dev.expensetrackerapi;

import com.dev.expensetrackerapi.filters.AuthFilters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class ExpenseTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApiApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean<AuthFilters> filterRegistrationBean(){
		FilterRegistrationBean<AuthFilters> registrationBean = new FilterRegistrationBean<>();
		AuthFilters authFilters = new AuthFilters();

		registrationBean.setFilter(authFilters);
		registrationBean.addUrlPatterns("/api/categories/*");

		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean(){
		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");

		source.registerCorsConfiguration("/**", corsConfiguration);
		registrationBean.setFilter(new CorsFilter(source));
		registrationBean.setOrder(0);

		return registrationBean;
	}

}
