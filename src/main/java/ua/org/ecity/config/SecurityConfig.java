package ua.org.ecity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/css/**",
                        "/index",
                        "/logout",
                        "/hello",
                        "/city",
                        "/cities",
                        "/",
                        "/index.html",
                        "/connect/**",
                        "/manager/**"
                ).permitAll()
                .antMatchers("/login", "/user/**", "/game/**").hasAuthority("USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
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
//                .antMatchers("/manager/**")
//                .antMatchers("/connect/**")
                .antMatchers("/resources/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "select u.login as username, u.password, u.enable as enabled from users u where u.login=?")
                .authoritiesByUsernameQuery(
                        "select u.login as username, r.name as role from user_roles ur join roles r on ur.role_id = r.id join users u on ur.user_id = u.id where u.login=?");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}