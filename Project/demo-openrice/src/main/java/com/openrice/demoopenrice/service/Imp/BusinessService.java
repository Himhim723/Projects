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

import com.openrice.demoopenrice.entity.BMember;
import com.openrice.demoopenrice.entity.Member;
import com.openrice.demoopenrice.entity.Shop;
import com.openrice.demoopenrice.model.Comment;
import com.openrice.demoopenrice.model.Food;

public interface BusinessService{
  Member signUp( Member member);
  void deleteAccount( String username);
  void adjustPassword( String username,String password);
  BMember addShop ( String username, Shop shop);
  List<Shop> findMyShops( String username);
  Shop findMyShopByName( String username, String shopName);
  List<Food> addFoodToShop( String username, String shopName, List<Food> foods);
  void deleteFoodFromShop(String username,String shopName,int foodId);
  Food adjustPrice(String username,String shopName,int foodId,double price);
  List<Comment> findAllComments( String username, String shopName);
}
