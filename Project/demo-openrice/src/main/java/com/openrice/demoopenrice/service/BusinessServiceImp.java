package com.openrice.demoopenrice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openrice.demoopenrice.entity.BMember;
import com.openrice.demoopenrice.entity.Member;
import com.openrice.demoopenrice.entity.Shop;
import com.openrice.demoopenrice.model.Comment;
import com.openrice.demoopenrice.model.Food;
import com.openrice.demoopenrice.repository.BMemberRepository;
// import com.openrice.demoopenrice.repository.CMemberRepository;
import com.openrice.demoopenrice.repository.MemberRepository;
import com.openrice.demoopenrice.repository.ShopRepository;
import com.openrice.demoopenrice.service.Imp.BusinessService;

import jakarta.transaction.Transactional;

@Service
public class BusinessServiceImp implements BusinessService{

  @Autowired
  private ShopRepository shopRepository;
  @Autowired
  private BMemberRepository businessRepository;
  @Autowired
  private MemberRepository memberRepository;

  @Override //success
  public Member signUp(Member member) {
    if(memberRepository.findById(member.getUsername()).isEmpty()){
      Member mUser = Member.builder().username(member.getUsername())
                                               .password(member.getPassword())
                                               .type(1).build();
      BMember user = BMember.builder().member(mUser)
                                      .build();
      businessRepository.save(user);
      return memberRepository.save(mUser);
    } else throw new IllegalAccessError();
  }

  @Override 
  public void deleteAccount(String username) {
    BMember member = findByUserName(username);
    businessRepository.deleteById(member.getId());
  }

  @Override 
  public void adjustPassword(String username, String password) {
    Member member = memberRepository.findById(username).orElseThrow(()->new IllegalAccessError("User Not Found"));
    member.setPassword(password);
    memberRepository.save(member);
  }

  @Override
  public BMember addShop(String username, Shop shop) {
    BMember member = findByUserName(username);
    member.getMyShops().add(shop);
    return businessRepository.save(member);
  }

  @Override
  public List<Shop> findMyShops(String username) {
    Member user = memberRepository.findById(username).orElseThrow(()->new IllegalAccessError("User Not Found"));
    return businessRepository.findByMember(user).getMyShops();
  }

  @Override
  public Shop findMyShopByName(String username, String shopName) {
    return findByUserName(username).getMyShops().stream().filter(e->e.getName().equals(shopName))
            .findFirst().orElseThrow(()->new IllegalAccessError("No such shop"));
  }

  @Override
  public List<Food> addFoodToShop(String username, String shopName, List<Food> foods) {
    Shop shop = findMyShopByName(username, shopName);
    for(Food food:foods)
    shop.getMenu().add(food);
    shopRepository.save(shop);
    //businessRepository.save(user);
    return shop.getMenu();
  }

  @Override
  public void deleteFoodFromShop(String username, String shopName, int foodId) {
    Shop shop = findMyShopByName(username, shopName);
    shop.getMenu().removeIf(e->e.getId()==foodId);
    shopRepository.save(findMyShopByName(username, shopName));
  }

  @Override
  public Food adjustPrice(String username, String shopName, int foodId, double price) {
    findMyShopByName(username, shopName).getMenu().forEach(e->{
      if(e.getId()==foodId)
      e.setPrice(price);
    });
    shopRepository.save(findMyShopByName(username, shopName));
    //businessRepository.save(businessRepository.findById(shopName).get());
    return findMyShopByName(username, shopName).getMenu().stream().filter(e->e.getId()==foodId).findAny().get();
  }

  @Override
  public List<Comment> findAllComments(String username, String shopName) {
    return findMyShopByName(username, shopName).getComments();
  }
  

  BMember findByUserName(String username){
    Member user = memberRepository.findById(username).orElseThrow(()->new IllegalAccessError("User Not Found"));
    return businessRepository.findByMember(user);
  }
}
