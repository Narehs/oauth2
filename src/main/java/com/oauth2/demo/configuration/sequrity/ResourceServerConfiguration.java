package com.oauth2.demo.configuration.sequrity;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/oauth/token").permitAll()
                .antMatchers("/locale/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/properties/**").hasRole("USER")
                .anyRequest().authenticated();
    }
}
