package com.user.basicusermanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.user.basicusermanagement.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Configuration
public class AppConfig {

  @Autowired
  private UserRepository userRepository;
  
  @Bean
  ModelMapper modelMapper(){
    return new ModelMapper();
  }

  @Bean
  UserDetailsService userDetailsService(){
    return (username) -> userRepository.findByUsername(username).orElseThrow(()-> new EntityNotFoundException());
        
  }
  
  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService((userDetailsService()));
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  } 

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

}
