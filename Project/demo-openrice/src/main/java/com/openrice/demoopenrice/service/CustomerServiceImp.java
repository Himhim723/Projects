package com.openrice.demoopenrice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openrice.demoopenrice.entity.BMember;
import com.openrice.demoopenrice.entity.CMember;
import com.openrice.demoopenrice.entity.Member;
import com.openrice.demoopenrice.entity.Shop;
import com.openrice.demoopenrice.exception.AccountExistedException;
import com.openrice.demoopenrice.model.Booking;
import com.openrice.demoopenrice.model.Comment;
import com.openrice.demoopenrice.model.CustomerSignUp;
import com.openrice.demoopenrice.repository.BookingRepository;
import com.openrice.demoopenrice.repository.CMemberRepository;
import com.openrice.demoopenrice.repository.MemberRepository;
import com.openrice.demoopenrice.repository.ShopRepository;
import com.openrice.demoopenrice.service.Imp.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImp implements CustomerService{
  
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private CMemberRepository customerRepository;
  @Autowired
  private ShopRepository shopRepository;
  @Autowired
  private BookingRepository bookingRepository;

  @Override
  public CMember signUp(CustomerSignUp member) { //success
    if(memberRepository.findById(member.getUsername()).isEmpty()){
      Member mUser = Member.builder().username(member.getUsername())
                                     .password(member.getPassword())
                                     .type(0).build();
      CMember user = CMember.builder().member(mUser)
                                     .creditCard(member.getCreditCard())
                                     .email(member.getEmail())
                                     .name(member.getName())
                                     .phone(member.getPhone())
                                     .build();
      memberRepository.save(mUser);
      return customerRepository.save(user);
    } else throw new IllegalAccessError();
  }
  
  @Override
  public CMember adjustInfo(String username, CustomerSignUp member){ //success
    CMember person = findByUserName(username);
    person.getMember().setPassword(member.getPassword());
    person.setEmail(member.getEmail());
    person.setCreditCard(member.getCreditCard());
    person.setName(member.getName());
    person.setPhone(member.getPhone());
    return customerRepository.save(person);
  }
  @Override
  public void deleteAccount(String username) { //success
    CMember member = findByUserName(username);
    customerRepository.deleteById(member.getId());
  }
  @Override
  public void adjustPassword(String username, String password) {
    CMember customer = findByUserName(username);
    customer.getMember().setPassword(password);
    customerRepository.save(customer);
  }
  @Override
  public List<Shop> findAllShops() {
    return shopRepository.findAll();
  }
  @Override
  public Shop findShopByName(String name) {
    return shopRepository.findById(name).orElseThrow(()->new IllegalAccessError("Shop does not exist"));
  }
  @Override
  public List<Shop> findMyFavShops(String username) {
    return findByUserName(username).getMyFavShops();
  }
  @Override
  public Shop addMyFavShop(String username, String shopName) {
    Shop shop = shopRepository.findById(shopName).orElseThrow(()->new IllegalAccessError("Shop not found"));
    CMember user = findByUserName(username);
    user.getMyFavShops().add(shop);
    customerRepository.save(user);
    return shop;
  }

  @Override
  @Transactional
  public void deleteMyFavShop(String username, String shopName) {
    Shop shop = shopRepository.findById(shopName).orElseThrow(()->new IllegalAccessError("Shop not found"));
    CMember user = findByUserName(username);
    user.getMyFavShops().remove(shop);
    shop.getLiked().remove(user);
    customerRepository.save(user);
  }

  /**
   * HardCoded need implementation
   * Time of Booking
   */
  @Override
  @Transactional
  public Booking bookResturant(String username, String shopName) {
    Shop shop = shopRepository.findById(shopName).get();
    CMember user = findByUserName(username);
    Booking booking = Booking.builder().userName(user).shopName(shop)
                      .bookingDate("Today").bookingTime("16:00").numOfCustomer(4).registerTime("now").build();
    shop.getBookings().add(booking);
    user.getMyBookings().add(booking);
    return bookingRepository.save(booking);
  }

  @Override
  public void deleteBooking(String username, String shopName) {
    CMember user = findByUserName(username);
    int id = user.getMyBookings().stream().filter((e)->e.getShopName().getName().equals(shopName)).findAny().get().getId();
    Booking booking = bookingRepository.findById(id).get();
    user.getMyBookings().remove(booking);
    booking.getShopName().getBookings().remove(booking);
    bookingRepository.deleteById(id);
  }

  @Override
  public Comment postComments(String shopName,Comment comment) {
    Shop shop = findShopByName(shopName);
    comment.setShopName(shopName);
    shop.getComments().add(comment);
    shopRepository.save(shop);
    return comment;
  }

  CMember findByUserName(String username){
    Member user = memberRepository.findById(username).orElseThrow(()->new IllegalAccessError("User Not Found"));
    return customerRepository.findByMember(user);
  }

}
