package com.user.basicusermanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.user.basicusermanagement.infra.ApiResp;
import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.User;
import com.user.basicusermanagement.model.enums.Role;

/**
 * This class act as an interface stating the specific authorities that 
 * an admin can do with the role of ADMIN
 */
public interface AdminController {
  
  /**
   * Admin is allowed to view all User Details for monitor
   * @return List of User
   */
  @GetMapping(value = "/users")
  @ResponseStatus(value = HttpStatus.OK)
  //url endpoints: /v1/admin/users
  ApiResp<List<User>> findAllUsers();

  /**
   * Admin is allowed to upgrade/downgrade a member to the role of Admin
   * (if needed we can add one more layer between admin and user)
   * @param username to locate the user for upgrading his account
   * @param role admin determine which role the user is going to be
   * @return
   */
  @PostMapping(value = "/authorize")
  @ResponseStatus(value = HttpStatus.OK)
  //url endpoints: /v1/admin/authorize
  ApiResp<User> authorizedUser(@RequestParam String username, @RequestParam Role role) throws UserException;

  /**
   * Admin is allowed to delete the user account of specific person
   * @param username to delete the user account by using user's username
   */
  @DeleteMapping(value = "/del")
  @ResponseStatus(value = HttpStatus.OK)
  //url endpoints: /v1/admin/del
  void deleteUser(@RequestParam String username) throws UserException;

}
