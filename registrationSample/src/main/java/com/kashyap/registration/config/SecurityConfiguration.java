package com.kashyap.registration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kashyap.registration.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private UserService service;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/registration**").permitAll()
						.requestMatchers("/","/users*","/home**").permitAll().requestMatchers("/home").permitAll().requestMatchers("/about").permitAll().requestMatchers("/users").authenticated())
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/users")
						.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/").permitAll())
				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());
		return http.build();
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(passwordEncoder());
    }
	

}
