package com.dbms.spark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dbms.spark.config.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.eraseCredentials(false);
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/" , "/login" , "/register/student" , "/user/**", "/css/**","/img/**" , "/js/**", "/privacy-policy", "/subjects", "/courses").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/teacher", "/teacher/**").hasRole("TEACHER")
				.antMatchers("/student" , "/student/**").hasRole("STUDENT")
                .antMatchers("/staff/**").hasRole("STAFF")
                .anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll()
				.defaultSuccessUrl("/welcome").and()
				.logout().permitAll();
	}
}
