package com.user.basicusermanagement.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.user.basicusermanagement.model.User;
import com.user.basicusermanagement.model.enums.Role;
import com.user.basicusermanagement.repository.UserRepository;

@Configuration
public class CommandLineConfig {

  @Autowired
  UserRepository userRepository;
  
  @Bean
  public CommandLineRunner commandLineRunner(){
    return (args) ->{
      userRepository.findByUsername("admin001")
      .ifPresentOrElse((user)->{return;},
        ()->userRepository.save(User.builder().username("admin001")
                                .password("Admin1234")
                                .fullName("Administrator")
                                .gender("M")
                                .dob(LocalDate.now())
                                .email("admin001@gmail.com")
                                .address("N/A")
                                .contact("N/A")
                                .role(Role.ADMIN)
                                .regDateTime(LocalDateTime.now())
                                .build()));
    }; 
  }
}
