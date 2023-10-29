package com.user.basicusermanagement.controller.Impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.basicusermanagement.infra.AuthenticationResponse;
import com.user.basicusermanagement.controller.UserController;
import com.user.basicusermanagement.infra.ApiResp;
import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.dto.ChnagePasswordDTO;
import com.user.basicusermanagement.model.dto.UserDTO;
import com.user.basicusermanagement.model.dto.UserLoginDTO;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;
import com.user.basicusermanagement.service.UserService;


@RestController
@RequestMapping(value = "/v1/user")
public class UserControllerImpl implements UserController {
  
  @Autowired
  private UserService authenticationService;

  @Override
  public ApiResp<AuthenticationResponse> signUp(UserSignUpDTO user) throws UserException {
    return ApiResp.<AuthenticationResponse> builder().ok(authenticationService.signUp(user)).build();
  }

  public ApiResp<AuthenticationResponse> authenticate(UserLoginDTO request) throws UserException{
    return ApiResp.<AuthenticationResponse> builder()
                  .ok(authenticationService.authenticate(request))
                  .build();
  }

  public void deleteUser(UserLoginDTO user) throws UserException{
    authenticationService.deleteUser(user);
  }

  @Override
  public ApiResp<UserDTO> getProfile(String username) throws UserException{
    return ApiResp.<UserDTO> builder().ok(authenticationService.getProfile(username)).build();
  }

  @Override
  public ApiResp<UserDTO> adjustProfile(UserSignUpDTO user) throws UserException{
    return ApiResp.<UserDTO> builder().ok(authenticationService.adjustProfile(user)).build();
  }

  @Override
  public void adjustPassword(ChnagePasswordDTO request, Principal connectedUser) throws UserException{
    authenticationService.changePassword(request,connectedUser);
  }

  @Override
  public void adjustEmail(String username,String email) throws UserException {
    authenticationService.adjustEmail(username,email);
  }

  @Override
  public void adjustAddress(String username,String address) throws UserException {
    authenticationService.adjustAddress(username,address);
  }

  

}
