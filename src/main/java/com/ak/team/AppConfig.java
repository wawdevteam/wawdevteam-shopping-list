package com.ak.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import scala.App;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AppConfig {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		LOG.info("Starting Simple Shopping List application");

		SpringApplication.run(AppConfig.class, args);
	}
}
