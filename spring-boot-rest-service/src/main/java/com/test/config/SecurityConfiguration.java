package com.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
	// use this encoder to not allow plain text passwords
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// configuration to allow basic authentication and disable CSRF	
http
.csrf().disable()
.authorizeRequests()
.anyRequest()
.authenticated()
.and()
.httpBasic();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
	// configuration to allow these urls accessed regardless of spring security configurations
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/app/**/*.{js,html}")
            .antMatchers("/content/**")
            .antMatchers("/swagger-ui/index.html");
    }
 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// in memory authentication database with credentials name admin/password
auth.inMemoryAuthentication()
.withUser("admin")
.password("$2a$10$o4YaL1C5Q.Je32FfccrFD.nQotX1p/hDK1TUIrtDW9pLiP0o19AIK")
.roles("USER");
	}
}
