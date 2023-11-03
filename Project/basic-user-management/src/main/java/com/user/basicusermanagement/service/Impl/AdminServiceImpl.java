package com.user.basicusermanagement.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.User;
import com.user.basicusermanagement.model.enums.Role;
import com.user.basicusermanagement.service.AdminService;

@Service
public class AdminServiceImpl extends UserServiceImpl implements AdminService {

  @Override
  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User authorizedUser(String username, Role role) throws UserException{
    User user = findUserByUsername(username);
    user.setRole(role);
    return userRepository.save(user);
  }

  @Override
  public void deleteUser(String username) throws UserException {
    User user = findUserByUsername(username);
    userRepository.delete(user);
  }

  
  
}
