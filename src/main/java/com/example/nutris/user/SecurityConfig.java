package com.example.nutris.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new CustomAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/v1/auth/register", "/api/v1/auth/login").permitAll()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout").invalidateHttpSession(true)
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/food").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/search_food").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/food").hasAuthority("NUTRIONIST")
                .and().authorizeRequests().antMatchers(HttpMethod.PATCH, "/api/v1/food").hasAuthority("NUTRIONIST")
                .and().authorizeRequests().antMatchers("/account/*").authenticated();
    }
}