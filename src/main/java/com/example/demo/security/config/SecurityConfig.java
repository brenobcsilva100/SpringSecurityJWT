package com.example.demo.security.config;

import com.example.demo.security.filter.JWTAuthenticationFilter;
import com.example.demo.security.filter.JWTAuthorizationFilter;
import com.example.demo.security.service.CustomUserDetailsServicce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsServicce customUserDetailsServicce;

    @Autowired
    public SecurityConfig(CustomUserDetailsServicce customUserDetailsServicce) {
        this.customUserDetailsServicce = customUserDetailsServicce;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/*/professor/**").hasRole("PROFESSOR")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailsServicce));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsServicce).passwordEncoder(new BCryptPasswordEncoder());
    }
}
