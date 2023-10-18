package com.openrice.demoopenrice.service.Imp;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.openrice.demoopenrice.entity.CMember;
import com.openrice.demoopenrice.entity.Member;
import com.openrice.demoopenrice.entity.Shop;
import com.openrice.demoopenrice.model.Booking;
import com.openrice.demoopenrice.model.Comment;
import com.openrice.demoopenrice.model.CustomerSignUp;

public interface CustomerService {
  CMember signUp(CustomerSignUp member);
  CMember adjustInfo(String username,CustomerSignUp member);
  void deleteAccount(String username);
  void adjustPassword(String username, String password);
  List<Shop> findAllShops();
  Shop findShopByName(String name);
  List<Shop> findMyFavShops(String username);
  Shop addMyFavShop (String username, String shopName);
  void deleteMyFavShop (String username, String shopName);
  Booking bookResturant (String username, String shopName);
  void deleteBooking (String username, String shopName);
  Comment postComments(String shopName,Comment comment);
}
