package com.openrice.demoopenrice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openrice.demoopenrice.controller.imp.CMController;
import com.openrice.demoopenrice.entity.CMember;
import com.openrice.demoopenrice.entity.Shop;
import com.openrice.demoopenrice.model.Booking;
import com.openrice.demoopenrice.model.Comment;
import com.openrice.demoopenrice.model.CustomerSignUp;
import com.openrice.demoopenrice.service.Imp.CustomerService;

@RestController
@RequestMapping(value = "/v1/customer")
public class CMOperation implements CMController {

  @Autowired
  private CustomerService customerService;

  @Override
  public CMember signUp(CustomerSignUp member) {
    return customerService.signUp(member);
  }

  @Override
  public CMember adjustInfo(String username, CustomerSignUp member) {
    return customerService.adjustInfo(username, member);
  }

  @Override
  public void deleteAccount(String username) {
    customerService.deleteAccount(username);
  }

  @Override
  public void adjustPassword(String username, String password) {
    customerService.adjustPassword(username, password);
  }

  @Override
  public List<Shop> findAllShops() {
    return customerService.findAllShops();
  }

  @Override
  public Shop findShopByName(String name) {
    return customerService.findShopByName(name);
  }

  @Override
  public List<Shop> findMyFavShops(String username) {
    return customerService.findMyFavShops(username);
  }

  @Override
  public Shop addMyFavShop(String username, String shopName) {
    return customerService.addMyFavShop(username, shopName);
  }

  @Override
  public void deleteMyFavShop(String username, String shopName) {
    customerService.deleteMyFavShop(username, shopName);
  }

  @Override
  public Booking bookResturant(String username, String shopName) {
    return customerService.bookResturant(username,shopName);
  }

  @Override
  public void deleteBooking(String username, String shopName) {
    customerService.deleteBooking(username, shopName);
  }

  @Override
  public Comment postComments(String shopName,Comment comment) {
    return customerService.postComments(shopName,comment);
  }
  
}
