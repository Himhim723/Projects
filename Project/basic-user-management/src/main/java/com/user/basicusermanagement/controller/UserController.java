package com.user.basicusermanagement.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.user.basicusermanagement.infra.AuthenticationResponse;
import com.user.basicusermanagement.infra.ApiResp;
import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.dto.ChnagePasswordDTO;
import com.user.basicusermanagement.model.dto.UserDTO;
import com.user.basicusermanagement.model.dto.UserLoginDTO;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;

public interface UserController {

  /**
   * Request for Signing Up an account
   * Receive Data for registration from client
   * @param user 
   * @limit email should be in the correct format
   * @return (onSuccess) Data of Profile
   * @return (onFail) Api Error Response
   * 
   */
  @PostMapping(value = "/signUp")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<AuthenticationResponse> signUp(@RequestBody UserSignUpDTO user) throws UserException;

  /**
   * Registered User login validating the username and password
   * @param user Group username and password in a class and do validation in service class 
   * @return  (onSuccess) Data of Profile
   * @return (onFail) Api Error Response
   */
  @PostMapping(value = "/authenticate")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<AuthenticationResponse> authenticate(@RequestBody UserLoginDTO user) throws UserException;


  /**
   * Request for the data in the UserProfile (UserDTO)
   * @param username
   * @return (onSuccess) Data of Profile without user password
   * @return (onFail) Api Error Response
   */
  @GetMapping(value = "/profile")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<UserDTO> getProfile(@RequestParam String username) throws UserException;

  /**
   * Changing most of the important data
   * @param user adjust the Profile data with the use of UserSignUpDTO
   * @limit email should be in the correct format
   * @return (onSuccess) Data of Profile
   * @return (onFail) Api Error Response
   */
  @PutMapping(value = "/profile")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<UserDTO> adjustProfile(@RequestBody UserSignUpDTO user)throws UserException;

  /**
   * For Users to delete their Account
   * @param user Users are required to input their username and password for authentication 
   * if they would like to confirm deleting their account
   * @throws UserException
   */
  @DeleteMapping(value = "/delete")
  @ResponseStatus(value = HttpStatus.OK)
  void deleteUser(@RequestBody UserLoginDTO user) throws UserException;

  /**
   * adjust password only
   * @param username used to locate the user
   * @param password change the password
   */
  @PatchMapping(value = "/changePassword")
  @ResponseStatus(value = HttpStatus.OK)
  void adjustPassword(@PathVariable ChnagePasswordDTO request,Principal connectedUser) throws UserException;

  /**
   * adjust Email
   * @param usernameused to locate the user
   * @param email
   * @throws UserException
   */
  @PatchMapping(value = "/adjust/user/{username}/email/{email}")
  @ResponseStatus(value = HttpStatus.OK)
  void adjustEmail(@PathVariable String username,@PathVariable String email) throws UserException;

  /**
   * adjust Address
   * @param username used to locate the user
   * @param address
   */
  @PatchMapping(value = "/adjust/user/{username}/address/{address}")
  @ResponseStatus(value = HttpStatus.OK)
  void adjustAddress(@PathVariable String username,@PathVariable String address) throws UserException;

  
}