package com.openrice.demoopenrice.controller.imp;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.openrice.demoopenrice.entity.BMember;
import com.openrice.demoopenrice.entity.Member;
import com.openrice.demoopenrice.entity.Shop;
import com.openrice.demoopenrice.model.Comment;
import com.openrice.demoopenrice.model.Food;

public interface BMController {
  @PostMapping(value = "/signUp") //success
  Member signUp(@RequestBody Member member);

  @DeleteMapping(value = "/home/{username}")  //success
  //Cannot delete while Customer Liked The restaurant
  void deleteAccount(@PathVariable String username);

  @PatchMapping(value = "/home/{username}")  //success
  void adjustPassword(@PathVariable String username,@RequestParam String password);

  @PostMapping(value = "/home/{username}/shop") //success
  BMember addShop (@PathVariable String username,@RequestBody Shop shop);

  @GetMapping(value = "/home/{username}/shops")  //success
  List<Shop> findMyShops(@PathVariable String username);

  @GetMapping(value = "/home/{username}/shop/{shopName}") //success
  Shop findMyShopByName(@PathVariable String username,@PathVariable String shopName);

  @PostMapping(value = "/home/user/{username}/shop/{shopName}") 
  List<Food> addFoodToShop(@PathVariable String username,@PathVariable String shopName,@RequestBody List<Food> foods);

  @DeleteMapping(value = "/home")
  void deleteFoodFromShop(@RequestParam String username,@RequestParam String shopName,@RequestParam int foodId);

  @PatchMapping(value = "/home")
  Food adjustPrice(@RequestParam String username,@RequestParam String shopName,@RequestParam int foodId,@RequestParam double price);

  @GetMapping(value = "/home/user/{username}/shop/{shopName}/comment")
  List<Comment> findAllComments(@PathVariable String username,@PathVariable String shopName);

}
