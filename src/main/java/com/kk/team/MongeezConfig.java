package com.kk.team;

import org.mongeez.MongeezRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.mongodb.Mongo;

@Configuration
public class MongeezConfig {

	@Value("${spring.data.mongodb.database}")
	private String dbName;

	@Value("${mongeez.executeEnabled}")
	private boolean executeEnabled;

	@Autowired
	private Mongo mongo;

	@Bean
	protected MongeezRunner getMongeezRunnerBean() {
		MongeezRunner runner = new MongeezRunner();

		runner.setMongo(mongo);
		runner.setExecuteEnabled(executeEnabled);
		runner.setDbName(dbName);
		runner.setFile(new ClassPathResource("/mongo/mongeez.xml"));

		return runner;
	}
}
