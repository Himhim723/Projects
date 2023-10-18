package com.user.basicusermanagement.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.basicusermanagement.controller.UserController;
import com.user.basicusermanagement.infra.ApiResp;
import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.dto.UserDTO;
import com.user.basicusermanagement.model.dto.UserLoginDTO;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;
import com.user.basicusermanagement.service.UserService;

@RestController
@RequestMapping(value = "/v1")
public class UserControllerImpl implements UserController {
  
  @Autowired
  private UserService userService;

  @Override
  public ApiResp<UserDTO> signUp(UserSignUpDTO user) throws UserException {
    return ApiResp.<UserDTO>builder().ok().data(userService.signUp(user)).build();
  }

  @Override
  public ApiResp<UserDTO> getProfile(String username){
    return ApiResp.<UserDTO> builder().ok().data(userService.getProfile(username)).build();
  }

  @Override
  public ApiResp<UserDTO> login(UserLoginDTO user) throws UserException {
    return ApiResp.<UserDTO>builder().ok().data(userService.authenticationUser(user)).build();
  }

  @Override
  public ApiResp<UserDTO> adjustProfile(UserSignUpDTO user) {
    return ApiResp.<UserDTO> builder().ok().data(userService.adjustProfile(user)).build();
  }

  @Override
  public void deleteUser(UserLoginDTO user) throws UserException {
    userService.deleteUser(user);
  }


  @Override
  public void adjustPassword(String username,String password) {
    userService.adjustPassword(username,password);
  }

  @Override
  public void adjustEmail(String username,String email) throws UserException {
    userService.adjustEmail(username,email);
  }

  @Override
  public void adjustAddress(String username,String address) {
    userService.adjustAddress(username,address);
  }
}
