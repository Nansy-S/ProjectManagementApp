package com.prokopovich.projectmanagement.webapp.config;

import com.prokopovich.projectmanagement.webapp.util.jwt.CustomUserDetailsService;
import com.prokopovich.projectmanagement.webapp.util.jwt.JwtAuthenticationEntryPoint;
import com.prokopovich.projectmanagement.webapp.util.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private CustomUserDetailsService userDetailsService;
    private JwtFilter filter;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationEntryPoint authenticationEntryPoint,
                             CustomUserDetailsService userDetailsService, JwtFilter filter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.filter = filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws
            Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/users/", "/api/users/{id}", "/api/users/add",
                        "/api/users/edit",  "/api/users/edit/**").hasRole("Administrator")
                .antMatchers("/api/users/role", "/api/users/{id}", "/api/projects/**",
                        "/api/reporter/**", "/api/assignee/**", "/api/tasks/**").hasRole("Project manager")
                .antMatchers("/api/users/{id}", "/api/reporter/**", "/api/assignee/**",
                        "/api/tasks/{id}/**", "/api/projects/**").hasRole("Developer")
                .antMatchers("/api/users/{id}", "/api/reporter/{id}", "/api/assignee/**",
                        "/api/tasks/{id}/**", "/api/projects/{id}").hasRole("Tester")
                .antMatchers("/api/login").permitAll()
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.headers().httpStrictTransportSecurity().disable();
    }
}
