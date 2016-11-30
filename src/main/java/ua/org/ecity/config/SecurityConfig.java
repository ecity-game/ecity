package ua.org.ecity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/index", "/hello", "/city", "/cities", "/", "/index.html").permitAll()
                .antMatchers("/login", "/user/**", "/game/**").hasAuthority("USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

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
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select u.login as username, u.password, u.enable as enabled from users u where u.login=?")
                .authoritiesByUsernameQuery(
                        "select u.name as username, r.name as role from user_roles ur join roles r on ur.role_id = r.id join users u on ur.user_id = u.id where u.login=?");

    }
}