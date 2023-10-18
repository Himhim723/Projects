package com.openrice.demoopenrice.controller.imp;

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
import com.openrice.demoopenrice.entity.Shop;
import com.openrice.demoopenrice.model.Booking;
import com.openrice.demoopenrice.model.Comment;
import com.openrice.demoopenrice.model.CustomerSignUp;

public interface CMController {
  @PostMapping(value = "/signUp")  //success
  CMember signUp(@RequestBody CustomerSignUp member);
  @PutMapping(value = "/home/setting/{username}")  //success
  CMember adjustInfo(@PathVariable String username, @RequestBody CustomerSignUp member);
  @DeleteMapping(value = "/home/setting/{username}") // success
  void deleteAccount(@PathVariable String username);
  @PatchMapping(value = "/home/setting/{username}") // success
  void adjustPassword(@PathVariable String username,@RequestParam String password);
  @GetMapping(value = "/shops") //success
  List<Shop> findAllShops();
  @GetMapping(value = "/shop/search/{name}") //success
  Shop findShopByName(@PathVariable String name);

  @GetMapping(value = "/home/{username}/fav") //success
  List<Shop> findMyFavShops(@PathVariable String username);

  @PostMapping(value = "/home/fav") //success
  Shop addMyFavShop (@RequestParam String username,@RequestParam String shopName);

  @DeleteMapping(value = "/home/fav") //success
  void deleteMyFavShop (@RequestParam String username,@RequestParam String shopName);

  @PostMapping(value = "/home/booking") //success
  Booking bookResturant (@RequestParam String username,@RequestParam String shopName);

  @DeleteMapping(value = "/home/booking") //success
  void deleteBooking (@RequestParam String username,@RequestParam String shopName);

  @PostMapping(value = "/home/shop/{shopName}") 
  Comment postComments(@PathVariable String shopName,@RequestBody Comment comment);


}
