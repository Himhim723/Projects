package com.user.basicusermanagement.service;

import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.dto.UserDTO;
import com.user.basicusermanagement.model.dto.UserLoginDTO;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;

public interface UserService {

  /**
   * Accept the request if the Username does not exist
   * Do some health check while registration 
   * Email: only allows "@gmail.com","@icloud.com","@hotmail.com","@hotmail.com.hk" for signUp
   * username should be longer than 6 letters and shorter than 15 letters
   * password should contains At least one Uppercase one Lowercase and one number
   * 
   * on Success save the data into the datbase and do the convert the data into the form 
   * of data the Personal Profile included
   * @param user
   * @return (on Success) return data of the Personal Profile (UserDTO)
   * @throws UserException
   */
  UserDTO signUp(UserSignUpDTO user) throws UserException;

  /**
   * 
   * @param username
   * @return
   */
  UserDTO getProfile(String username);

  /**
   * Providing Login authentication
   * @param user username and password
   * @return Profile information if the username match the password
   * @throws UserException
   */
  UserDTO authenticationUser (UserLoginDTO user) throws UserException;
  
  /**
   * Edit the Profile Information At the same time
   * @param user Adjust information according to the attribute of UserSignUpDTO
   * other information such as UserID, Registration time are not allowed to adjust
   * @return the Adjusted information of data of UserProfile
   */
  UserDTO adjustProfile(UserSignUpDTO user);

  /**
   * do a confirmation before the user delete their account by
   * inserting the username and password again
   * (on Success) -> account deleted successfully
   * (on Failure) -> throw error
   * @param user username and password to do authentication
   * @throws UserException
   */
  void deleteUser(UserLoginDTO user) throws UserException;

  /**
   * @param username Locate the User
   * @param password Adjust the password according to the data client provided
   */
  void adjustPassword(String username,String password);
  /**
   * @param username Locate the User
   * @param email Adjust the email according to the data client provided
   */
  void adjustEmail(String username,String email) throws UserException;
 
 /**
   * @param username Locate the User
   * @param address Adjust the address according to the data client provided
   */
  void adjustAddress(String username,String address);

  boolean isEmailValid(String email);

  boolean isUsernameValid(String username);

  boolean isPasswordValid(String password);
}
