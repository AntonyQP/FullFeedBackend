package com.upc.fullfeedbackend;

import com.upc.fullfeedbackend.security.JWTAuthorizationFilter;
import com.upc.fullfeedbackend.services.UtilService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class FullFeedBackendApplication{

    public static void main(String[] args) {
        UtilService.getDATETET();
       //SpringApplication.run(FullFeedBackendApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/user/**","/patient/**","/hospital/**", "/swagger-resources/**", "/preferences",
                            "/swagger-ui.html",
                            "/v2/api-docs", "/doctor/**",
                            "/meal/**",
                            "/webjars/**", "/meal/**", "/nutritionalPlan/**", "/personalTreatments","/personalTreatments/**", "/region/**").permitAll()
                    .anyRequest().authenticated();
        }
    }

}
