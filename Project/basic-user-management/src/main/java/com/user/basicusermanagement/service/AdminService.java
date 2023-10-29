package com.user.basicusermanagement.service;

import java.util.List;

import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.User;
import com.user.basicusermanagement.model.enums.Role;

public interface AdminService {

  /**
   * Admin user can view status of all users 
   * @return
   */
  List<User> findAllUsers();

  /**
   * If a User is going to upgrade the account, It needed access of the admin to manage the role of the user
   * @param username which user Admin is going to authorize an upgrade or downgrade
   * @param role what role the Admin would like the user to become
   * @return After updating status successfully, User Information will be shown
   * @throws UserException
   */
  User authorizedUser(String username, Role role) throws UserException;

  /**
   * Admin can delete Users by entering the username of the user
   * @param username
   * @throws UserException
   */
  void deleteUser(String username) throws UserException;

}
