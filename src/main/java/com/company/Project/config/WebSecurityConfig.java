package com.company.Project.config;

import com.company.Project.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
  @Autowired
  private UsersService usersService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(usersService);
  }

  @Autowired
  public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(usersService)
        .passwordEncoder(passwordEncoder());
  }

  @Autowired
  private AuthenticationSuccessHandler authenticationSuccessHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
          .antMatchers("/", "/home", "/auth/**", "/css/**", "/static/**", "/error/804")
          .permitAll()
        .anyRequest()
          .authenticated()
          .and()
        .formLogin()
          .loginPage("/login")
          .successForwardUrl("/")
          .successHandler(authenticationSuccessHandler)
          .permitAll()
          .and()
        .logout()
          .logoutUrl("/logout")
          .logoutSuccessUrl("/")
          .permitAll();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
