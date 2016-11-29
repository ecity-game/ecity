package ua.org.ecity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ua.org.ecity.entities.User;
import ua.org.ecity.repository.CityRepository;
import ua.org.ecity.repository.UserRepository;

import javax.activation.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
        auth.jdbcAuthentication().usersByUsernameQuery(
                "select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select u.username, r.name as authority from user_roles ur join roles r on ur.role_id = r.id join users u on u.id = ur.user_id where u.username=?");

    }
}
