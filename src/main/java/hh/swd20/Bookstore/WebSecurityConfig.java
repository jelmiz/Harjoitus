package hh.swd20.Bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import hh.swd20.Bookstore.webcontroller.UserDetailServiceImpl;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
@Override
protected void configure(HttpSecurity http) throws Exception {
http.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated().and().formLogin()
.loginPage("/login").permitAll().and().logout().permitAll();
}

@Autowired
private UserDetailServiceImpl userDetailsService;

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
auth.userDetailsService(userDetailsService).passwordEncoder(new
BCryptPasswordEncoder());
}
}
