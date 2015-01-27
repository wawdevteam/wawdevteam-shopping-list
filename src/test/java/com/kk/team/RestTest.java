package com.kk.team;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class RestTest {

	private static final String X_AUTH_TOKEN = "x-auth-token";

	// @Test
	public void simpleIntegrationTest() {
		ResponseEntity<String> entity = new TestRestTemplate("admin", "pass666w0rd").getForEntity("http://localhost:8080/categories", String.class);

		assertThat(entity.getStatusCode(), equalTo(HttpStatus.OK));

		System.out.println("Token: " + entity.getHeaders().getFirst(X_AUTH_TOKEN));

		TestRestTemplate restTemplateToken = new TestRestTemplate();
		restTemplateToken.getInterceptors().add(new ClientHttpRequestInterceptor() {

			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
				String token = entity.getHeaders().getFirst(X_AUTH_TOKEN);
				request.getHeaders().add(X_AUTH_TOKEN, token);

				System.out.println("Request headers: " + request.getHeaders());

				return execution.execute(request, body);
			}
		});

		ResponseEntity<String> entityToken = restTemplateToken.getForEntity("http://localhost:8080/categories", String.class);

		assertThat(entityToken.getStatusCode(), equalTo(HttpStatus.OK));
	}
}
