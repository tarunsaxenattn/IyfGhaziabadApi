package com.IyfGZB.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.List;

@ComponentScan(basePackages={"com.IyfGZB"},
		excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = ".*Test.*"))
@SpringBootApplication
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true,jsr250Enabled = true)
public class IyfGzbApplication {


	public static void main(String[] args) {
		SpringApplication.run(IyfGzbApplication.class, args);
	}
}
