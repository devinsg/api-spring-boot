package com.demo.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.context.annotation.FilterType.CUSTOM;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = CUSTOM, classes = TypeExcludeFilter.class)})
@EnableConfigurationProperties(value = AirlineProperties.class)
public class AirlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineApplication.class, args);
	}

}
