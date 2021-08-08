package com.example.demo.configurations;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

import com.example.demo.services.AccountService;

@EnableWebSecurity
@Configuration
@Order(2)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccountService accountService;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.cors().and().csrf().disable();
		
		httpSecurity.antMatcher("/admin/**")
					.authorizeRequests()
					.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
					.and()
					.formLogin()
					.loginPage("/admin-panel")
					.loginProcessingUrl("/admin/process-login")
					.defaultSuccessUrl("/admin-panel/welcome")
					.failureUrl("/admin-panel/login?error")
					.usernameParameter("username").passwordParameter("password")
					.and()
					.logout()
					.logoutUrl("/admin/process-logout")
					.logoutSuccessUrl("/admin-panel/login?logout")
					.deleteCookies("JSESSIONID")
					.and()
					.exceptionHandling().accessDeniedPage("/admin-panel/accessDenied");
					
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(accountService);
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityContextHolderAwareRequestFilter awareRequestFilter() {
		return new SecurityContextHolderAwareRequestFilter();
	}
	
	@Bean
	public SecurityContextPersistenceFilter persistenceFilter() {
		return new SecurityContextPersistenceFilter();
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("hieuzxc00@gmail.com");
	    mailSender.setPassword("01688660922");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
}
