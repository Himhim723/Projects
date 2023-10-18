package com.user.basicusermanagement.model.mapper;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.basicusermanagement.model.User;
import com.user.basicusermanagement.model.dto.UserDTO;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;
import com.user.basicusermanagement.model.enums.Role;

@Component
public class UserMapper {

  @Autowired
  private ModelMapper modelMapper;

  public User map(UserSignUpDTO user){
    return User.builder().username(user.getUsername())
                         .password(user.getPassword())
                         .fullName(user.getFullName())
                         .gender(user.getGender())
                         .dob(user.getDob())
                         .email(user.getEmail())
                         .address(user.getAddress())
                         .contact(user.getContact())
                         .role(Role.REGULAR.getDesc())
                         .regDateTime(LocalDateTime.now())
                         .build();
  }

  public UserDTO map(User user){
    UserDTO dto = modelMapper.map(user,UserDTO.class);
    dto.setAge(user.getAge());
    return dto;
  }
}
