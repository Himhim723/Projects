package com.user.basicusermanagement.controller.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.basicusermanagement.controller.AdminController;
import com.user.basicusermanagement.infra.ApiResp;
import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.User;
import com.user.basicusermanagement.model.enums.Role;
import com.user.basicusermanagement.service.AdminService;

@RestController
@RequestMapping(value = "/v1/admin")
public class AdminControllerImpl extends UserControllerImpl implements AdminController {

  @Autowired
  private AdminService adminService;

  @Override
  public ApiResp<List<User>> findAllUsers() {
    return ApiResp.<List<User>> builder().ok(adminService.findAllUsers()).build();
  }

  @Override
  public ApiResp<User> authorizedUser(String username, Role role) throws UserException {
    return ApiResp.<User> builder().ok(adminService.authorizedUser(username, role)).build();
  }

  @Override
  public void deleteUser(String username) throws UserException {
    adminService.deleteUser(username);
  }


  
}
