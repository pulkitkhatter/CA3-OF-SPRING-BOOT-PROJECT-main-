package com.example.CA.Application;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();
        users.add(User.withDefaultPasswordEncoder().username("pulkit").password("1234").roles("USER").build());
        users.add(User.withDefaultPasswordEncoder().username("rohit").password("1234").roles("USER").build());
        users.add(User.withDefaultPasswordEncoder().username("admin").password("admin123").roles("ADMIN").build());
        return new InMemoryUserDetailsManager(users);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/create").hasRole("ADMIN")
                .antMatchers("/api/put", "/api/post", "/api/get").hasRole("ADMIN")
                .antMatchers("/api/getAll").authenticated() // Assumes all authenticated users can access
                .and()
                .formLogin().permitAll() // Customize login if needed
                .and()
                .logout().permitAll(); // Customize logout if needed
    }
}
