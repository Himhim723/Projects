package com.user.basicusermanagement.service.Impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.user.basicusermanagement.config.security.JwtService;
import com.user.basicusermanagement.infra.AuthenticationResponse;
import com.user.basicusermanagement.infra.exception.Code;
import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.infra.exception.UserExistedException;
import com.user.basicusermanagement.model.User;
import com.user.basicusermanagement.model.dto.ChnagePasswordDTO;
import com.user.basicusermanagement.model.dto.UserDTO;
import com.user.basicusermanagement.model.dto.UserLoginDTO;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;
import com.user.basicusermanagement.model.mapper.UserMapper;
import com.user.basicusermanagement.repository.UserRepository;
import com.user.basicusermanagement.service.UserService;


@Service
@Primary
public class UserServiceImpl implements UserService{
  
  @Autowired
  protected UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public AuthenticationResponse signUp(UserSignUpDTO request) throws UserException{
    isUsernameValid(request.getUsername());
    isPasswordValid(request.getPassword());
    isEmailValid(request.getEmail());

    userExisted(request.getUsername());
    User userModel = userMapper.map(request);
    User saved = userRepository.save(userModel);
    String token = jwtService.generateToken(saved);
    return AuthenticationResponse.builder().token(token).build();
  }

  @Override
  public AuthenticationResponse authenticate(UserLoginDTO request) throws UserException{
    try{
    validateUser(request.getUsername(), request.getPassword());
    } catch(AuthenticationException e) {
      throw new UserException(Code.VALIDATOR_FAILURE);
    } 
    User user = findUserByUsername(request.getUsername());
    String token = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(token).build();
  }

  
  @Override
  public void changePassword(ChnagePasswordDTO request,Principal connectedUser) throws UserException{
    isPasswordValid(request.getNewPassword());
    User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    if(user != null){
      if(request.getNewPassword().equals(request.getConfirmation())){
      user.setPassword(request.getNewPassword());
      userRepository.save(user);
      } else throw new IllegalAccessError("Password and Confirmation should be the same");
    } else throw new UserException(Code.ENTITY_NOT_FOUND);
  }

  @Override
  public void deleteUser(UserLoginDTO user) throws UserException {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    userRepository.delete(findUserByUsername(user.getUsername()));
  }


  @Override
  public boolean userExisted(String username) throws UserException{
    if(userRepository.findAll().stream().filter(e->e.getUsername().equals(username)).findAny().isPresent()){
    throw new UserExistedException(Code.ALREADY_EXIST);
    }
    return false;
  }

  @Override
  public boolean isEmailValid(String email){
    String[] emailFormat = new String[]{"@gmail.com","@icloud.com","@hotmail.com","@hotmail.com.hk"};
      for(String format: emailFormat)
        if(email.endsWith(format)) return true;
      throw new IllegalArgumentException("You can only signUp with google/apple or hotmail account");
    }
  
  @Override
  public boolean isUsernameValid(String username){
    if(username.length()>=6) return true;
    else throw new IllegalArgumentException("length of username should be greater or equal to 6");
  }
  
  @Override
  public boolean isPasswordValid(String password){
    int len = password.length();
    if(len<6||len>15) 
    throw new IllegalArgumentException();
    else if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*")) {
      throw new IllegalArgumentException("Password should consisit of at least one uppercase, one lowercase character and one number");
    } else 
      return true;
  }

  @Override
  public User findUserByUsername(String username) throws UserException{
    return userRepository.findAll().stream().filter(e->e.getUsername().equals(username)).findAny().orElseThrow(()-> new UserException(Code.ENTITY_NOT_FOUND));
  }

  @Override
  public Long findUserIdByUsername(String username) throws UserException{
    return findUserByUsername(username).getUserID();
  }

  

  @Override
  public UserDTO getProfile(String username) throws UserException{
    return userMapper.map(findUserByUsername(username));
  }

  @Override
  public UserDTO adjustProfile(UserSignUpDTO user) throws UserException{
    User oldUser = findUserByUsername(user.getUsername());
    oldUser.setFullName(user.getFullName());
    oldUser.setGender(user.getGender());
    oldUser.setDob(user.getDob());
    oldUser.setEmail(user.getEmail());
    oldUser.setAddress(user.getAddress());
    oldUser.setContact(user.getContact());
    return userMapper.map(userRepository.save(oldUser));
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
  public void adjustAddress(String username,String address) throws UserException{
    User user = findUserByUsername(username);
    user.setAddress(address);
    userRepository.save(user);
  }

  @Override 
  public boolean validateUser(String username, String password) throws UserException{
    if(!findUserByUsername(username).getPassword().equals(password))
    throw new UserException(Code.VALIDATOR_FAILURE);
    else return true;
    
  }
}
