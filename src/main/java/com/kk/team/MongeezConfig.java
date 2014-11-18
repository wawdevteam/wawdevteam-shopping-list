package com.kk.team;

import java.net.UnknownHostException;

import org.mongeez.MongeezRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.mongodb.Mongo;
import com.mongodb.MongoURI;

@Configuration
public class MongeezConfig {
	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;

	@Value("${spring.data.mongodb.database}")
	private String dbName;

	@Value("${mongeez.executeEnabled}")
	private boolean executeEnabled;

	@Bean
	protected MongoURI getMongoUriBean() {
		return new MongoURI(mongoUri);
	}

	@Bean
	protected Mongo getMongoBean() throws UnknownHostException {
		return new Mongo(getMongoUriBean());
	}

	@Bean
	protected MongeezRunner getMongeezRunnerBean() throws UnknownHostException {
		MongeezRunner runner = new MongeezRunner();
		runner.setMongo(getMongoBean());
		runner.setExecuteEnabled(executeEnabled);
		runner.setDbName(dbName);
		runner.setFile(new ClassPathResource("/mongo/mongeez.xml"));

		return runner;
	}
}
