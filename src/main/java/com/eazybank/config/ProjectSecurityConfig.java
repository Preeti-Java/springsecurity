package com.eazybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	//Search SpringBootWebSecurityConfiguration, copy this method
	
	static class SecurityFilterChainConfiguration {

		@Bean
		SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
			
			/* 
			 * Below is the customer security configuration
			 * 
			 */
			
			http.authorizeRequests()
				.antMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
				.antMatchers("/notices","/contact").permitAll()
				.and()
				.httpBasic()
				.and()
				.formLogin();
			
			
			/* 
			 * Configuration to deny all requests
			 * 
			 */
			
		/*	http.authorizeRequests()
			.anyRequest().denyAll()
			.and()
			.httpBasic()
			.and()
			.formLogin(); */
			
			/* 
			 * Configuration to permitAll all requests
			 * 
			 */
			
			/*http.authorizeRequests()
			.anyRequest().permitAll()
			.and()
			.httpBasic()
			.and()
			.formLogin(); */
			
			return http.build();
		}
		
		
		//InMemoryUserDetailsService -> Username and Password
		@Bean
		public InMemoryUserDetailsManager userDetailsService() {
			/**
			 * Approach 1 : User withDefaultPasswordEncoder() method
			 * while creating the userDetails
			 * 
			 * */
			
			/*UserDetails admin=User.withDefaultPasswordEncoder()
					.username("admin")
					.password("123")
					.authorities("admin")
					.build();
			
			UserDetails user=User.withDefaultPasswordEncoder()
					.username("user")
					.password("1234")
					.authorities("read")
					.build();
			
			return new InMemoryUserDetailsManager(admin,user);  */
			
			
			/**
			 * Approach 2 : Use NoOpPasswordEncoder Bean
			 * while creating the userDetails
			 * 
			 * 
			 * NoOpPasswordEncoder method separate
			 * */
			
			UserDetails admin=User.withUsername("admin")
					.password("123")
					.authorities("admin")
					.build();
			
			UserDetails user=User.withUsername("user")
					.password("1234")
					.authorities("read")
					.build();
			
			return new InMemoryUserDetailsManager(admin,user);
		}
		
		
		/*
		 * NoOpPasswordEncoder is not recommended for production usage.
		 * Use only non-prod
		 * 
		 * @return PasswordEncoder
		 * 
		 * **/
		@Bean
		public PasswordEncoder passwordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}

	}
}








