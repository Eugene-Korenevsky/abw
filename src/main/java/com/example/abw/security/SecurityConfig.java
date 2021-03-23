package com.example.abw.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("administration")
                .antMatchers("/user/**").hasAuthority("user")
                .antMatchers("/", "/**").access("permitAll")
                .and()
                .formLogin().loginProcessingUrl("/authorize")
                .defaultSuccessUrl("/profile").failureUrl("/loginFail")
                .and()
                .logout().logoutSuccessUrl("/")
                .and().csrf().disable().authorizeRequests().antMatchers(PUT, "/**").permitAll()
                .and().csrf().disable().authorizeRequests().antMatchers(POST, "/**").permitAll()
                .and().csrf().disable().authorizeRequests().antMatchers(DELETE, "/**").permitAll()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource).
                usersByUsernameQuery(
                        "select email, password, true from user_info where email=?"
                ).authoritiesByUsernameQuery("select u.email, r.role from user_info as u," +
                "user_role as r where u.role_id = r.id and u.email=?")
                .passwordEncoder(passwordEncoder());
    }
}
