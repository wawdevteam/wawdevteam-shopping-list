package com.kk.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.plugin.EnableSwagger;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableSwagger
public class AppConfig {

	private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

	public static void main(String[] args) {
		LOG.info("Starting Simple Shopping List application.");

		SpringApplication.run(AppConfig.class, args);
	}
}
