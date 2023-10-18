package com.user.basicusermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.basicusermanagement.infra.exception.Code;
import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.infra.exception.UserExistedException;
import com.user.basicusermanagement.model.User;
import com.user.basicusermanagement.model.dto.UserDTO;
import com.user.basicusermanagement.model.dto.UserLoginDTO;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;
import com.user.basicusermanagement.model.mapper.UserMapper;
import com.user.basicusermanagement.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService{
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  @Override
  public UserDTO signUp(UserSignUpDTO user) throws UserException {
    isUsernameValid(user.getUserName());
    isPasswordValid(user.getPassword());
    isEmailValid(user.getUserName());
    try{
      userExisted(user.getUserName());
      User userModel = userRepository.save(userMapper.map(user));
      return userMapper.map(userModel);
    }catch(UserExistedException e){
      throw new UserExistedException(Code.ALREADY_EXIST);
    }
  }

  @Override
  public UserDTO getProfile(String username) {
    return userMapper.map(findUserByUsername(username));
  }

  @Override
  public UserDTO authenticationUser (UserLoginDTO user) throws UserException{
    try{
      validateUser(user);
    }catch(UserException e){
      throw new UserException(Code.VALIDATOR_FAILURE);
    }
    return getProfile(user.getUsername());
  }

  @Override
  public UserDTO adjustProfile(UserSignUpDTO user) {
    isEmailValid(user.getEmail());
    User oldUser = findUserByUsername(user.getUserName());
    oldUser.setFullName(user.getFullName());
    oldUser.setGender(user.getGender());
    oldUser.setDob(user.getDob());
    oldUser.setEmail(user.getEmail());
    oldUser.setAddress(user.getAddress());
    oldUser.setContact(user.getContact());
    return userMapper.map(userRepository.save(oldUser));
  }

  @Override
  public void deleteUser(UserLoginDTO user) throws UserException {
    try{
    validateUser(user);
    userRepository.deleteById(findUserIdByUsername(user.getUsername()));
    } catch (UserException e){
      throw new UserException(Code.VALIDATOR_FAILURE);
    }
  }

  @Override
  public void adjustPassword(String username,String password){
    isPasswordValid(password);
    User user = findUserByUsername(username);
    user.setPassword(password);
    userRepository.save(user);
  }

  @Override
  public void adjustEmail(String username,String email) throws UserException {
    User user = findUserByUsername(username);
    try{
    user.setEmail(email);
    userRepository.save(user);
    } catch(IllegalArgumentException e){
      throw new UserException(Code.IAE_EXCEPTION);
    }
  }

  @Override
  public void adjustAddress(String username,String address) {
    User user = findUserByUsername(username);
    user.setAddress(address);
    userRepository.save(user);
  }

  /**
   * Check if username existed already
   * Banned if the username has been existed before
   * @param username
   * @return true or false
   * @throws UserException
   */
  boolean userExisted(String username) throws UserException{
    if(userRepository.findAll().stream().filter(e->e.getUsername().equals(username)).findAny().isPresent())
    throw new UserExistedException(Code.ALREADY_EXIST);
    return true;
  }

  /**
   * Locate the User directly by using stream
   * @param username
   * @return User
   */
  User findUserByUsername(String username){
    return userRepository.findAll().stream().filter(e->e.getUsername().equals(username)).findAny().orElseThrow(()-> new EntityNotFoundException());
  }

  /**
   * Find the ID of the User Entity
   * @param username
   * @return Long ID of User
   * for adjusting information of User
   */
  Long findUserIdByUsername(String username){
    return findUserByUsername(username).getUserID();
  }

  /**
   * Check if username and password match
   * @param user
   * @return true if all of the data is correct
   * @throws UserException validator_failure Invalid username/password 
   */
  boolean validateUser(UserLoginDTO user) throws UserException{
    if (findUserByUsername(user.getUsername()).getPassword().equals(user.getPassword()))
    throw new UserException(Code.VALIDATOR_FAILURE);
    return true;
  }

  //The user can register an account using the format of emailFormat
  //Other types of email are not available for the registration
  String[] emailFormat = new String[]{"@gmail.com","@icloud.com","@hotmail.com","@hotmail.com.hk"};

  boolean isEmailValid(String email){
    for(String format: emailFormat)
    if(email.endsWith(format)) return true;
    throw new IllegalArgumentException();
  }

  boolean isUsernameValid(String username){
    return username.length()>=6;
  }

  boolean isPasswordValid(String password){
    char[] letter = password.toCharArray();
    int len = letter.length;
    if(len<6||len>15) 
    throw new IllegalArgumentException();
    else if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*")) {
      throw new IllegalArgumentException();
    } else 
      return true;
  
  }
}
