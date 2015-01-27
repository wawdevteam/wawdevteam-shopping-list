package com.kk.team;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.session.web.http.SessionRepositoryFilter;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.user.password}")
	private String userPassword;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.csrf().disable()
			.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();

//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(springSessionRepositoryFilter(sessionRepository())
				, BasicAuthenticationFilter.class);
		
		http.httpBasic();
		// @formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		auth
			.inMemoryAuthentication()
				.withUser("user").password(this.userPassword).roles("USER").and()
				.withUser("admin").password(this.userPassword).roles("USER", "ADMIN");
		
		auth.authenticationProvider(authenticationProviderBean());
		// @formatter:on
	}

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	@SuppressWarnings("static-method")
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected AuthenticationProvider authenticationProviderBean() throws Exception {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsServiceBean());

		return authenticationProvider;
	}

	private int maxInactiveIntervalInSeconds = 3600;

	@Bean
	public SessionRepository<ExpiringSession> sessionRepository() {
		MapSessionRepository sessionRepository = new MapSessionRepository();
		sessionRepository.setDefaultMaxInactiveInterval(maxInactiveIntervalInSeconds);
		return sessionRepository;
	}

	@Bean
	public <S extends ExpiringSession> SessionRepositoryFilter<? extends ExpiringSession> springSessionRepositoryFilter(
	        SessionRepository<S> sessionRepository) {

		SessionRepositoryFilter<S> sessionRepositoryFilter = new SessionRepositoryFilter<S>(sessionRepository);
		sessionRepositoryFilter.setHttpSessionStrategy(httpSessionStrategy());

		return sessionRepositoryFilter;
	}

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}
}
