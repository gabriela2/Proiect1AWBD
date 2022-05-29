package com.awbdfirstproject.railwaystationapp.configuration;

import com.awbdfirstproject.railwaystationapp.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register", "/login", "/login-error", "/home", "/journey", "/resource-exception").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/profile/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/ticket/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/incident/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/company/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/authUser")
                .failureUrl("/login-error").permitAll()
                .defaultSuccessUrl("/journey")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");


    }
}
