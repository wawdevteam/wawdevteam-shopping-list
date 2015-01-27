package com.kk.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableSwagger
public class AppConfig {

	private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

	public static void main(String[] args) {
		LOG.info("Starting Simple Shopping List application.");

		SpringApplication.run(AppConfig.class, args);

	}
}
