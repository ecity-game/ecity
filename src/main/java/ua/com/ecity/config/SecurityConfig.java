package ua.com.ecity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/css/**", "/index", "/hello", "/", "/index.html").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                //.anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login-error").permitAll();
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
            .ignoring()
                .antMatchers("/scripts/**/*.{js}")
                .antMatchers("/node_modules/**")
                .antMatchers("/assets/**")
                .antMatchers("*.{ico}")
                .antMatchers("/views/**/*.{html}")
                .antMatchers("/app/**/*.{html}")
                .antMatchers("/app/**/*.{js}")
                .antMatchers("/resources/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authManager) throws Exception {
        authManager
            .inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER");
    }
}
