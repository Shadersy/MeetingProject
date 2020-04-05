package ru.gvozdilin.meet.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.gvozdilin.meet.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CommonAuthenticationEntryPoint commonAuthenticationEntryPoint;
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    SecurityWebApplicationInitializer securityWebApplicationInitializer;

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/deleteMeetup", "/createMeetup")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .userDetailsService(myUserDetailsService);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new NullPasswordEncoder();
    }
}
