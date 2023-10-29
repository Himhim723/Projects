package com.user.basicusermanagement.service;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestBody;

import com.user.basicusermanagement.infra.AuthenticationResponse;
import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.User;
import com.user.basicusermanagement.model.dto.ChnagePasswordDTO;
import com.user.basicusermanagement.model.dto.UserDTO;
import com.user.basicusermanagement.model.dto.UserLoginDTO;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;

public interface UserService {

  AuthenticationResponse signUp(UserSignUpDTO request) throws UserException;
  AuthenticationResponse authenticate(UserLoginDTO request) throws UserException;
  void changePassword(ChnagePasswordDTO request,Principal connectedUser) throws UserException;
  void deleteUser(UserLoginDTO user) throws UserException;
  boolean userExisted(String username) throws UserException;
  UserDTO getProfile(String username) throws UserException;
  UserDTO adjustProfile(UserSignUpDTO user) throws UserException; //
  void adjustEmail(String username,String email) throws UserException; //
  void adjustAddress(String username,String address) throws UserException;
  boolean isEmailValid(String email); 
  boolean isUsernameValid(String username);
  boolean isPasswordValid(String password);
  User findUserByUsername(String username)throws UserException;
  Long findUserIdByUsername(String username)throws UserException;
  boolean validateUser(String username, String password) throws UserException;
  
}
