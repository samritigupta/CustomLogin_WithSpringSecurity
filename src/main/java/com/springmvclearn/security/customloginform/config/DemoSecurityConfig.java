package com.springmvclearn.security.customloginform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity // enable spring security
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add our users for in memory authentication
		@Deprecated
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("mary").password("test123").roles("MANAGER"))
			.withUser(users.username("susan").password("test123").roles("ADMIN"));
	}

	// s1. configure for custom login form
	//@Override
	protected void configureX(HttpSecurity http) throws Exception {

		http.authorizeRequests()// Restrict request based on HttpServletRequest
				.anyRequest().authenticated() // any request needs to be authenticated
				.and()
				.formLogin()     // using login form
				.loginPage("/showMyLoginPage")    // at the request mapping
				.loginProcessingUrl("/authenticateTheUser")  // login form should post the data to this url for processing. Can give any value for this but say consistent.
				.permitAll() // everyone to see the login page
				.and()
				.logout().permitAll(); // logout support

		/*
		/authenticateTheUser: no need to make request mapping for this. spring give us by default for checking username and password.
		 */
	}

	// for public page that ask user if want to login
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/").permitAll()  // allow public access to home page
				.antMatchers("/employees").hasRole("EMPLOYEE")
				.antMatchers("/leaders/**").hasRole("MANAGER")
				.antMatchers("/systems/**").hasRole("ADMIN")
				.and()
				.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
				.and()
				.logout()
				.logoutSuccessUrl("/")  // after logout then redirect to landing page (root)
				.permitAll();

	}
}






