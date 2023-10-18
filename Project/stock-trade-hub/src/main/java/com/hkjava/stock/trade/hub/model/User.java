package com.hkjava.stock.trade.hub.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.hkjava.stock.trade.hub.dto.req.PlaceOrderDTO;
import com.hkjava.stock.trade.hub.enums.MemberShip;
import com.hkjava.stock.trade.hub.mapper.StockMapper;
import lombok.Getter;
import lombok.Setter;


/** 
 * This class is designed for the user of out application 
 * Before using the application, user are defined as GUEST
 * GUEST should signUp before using our application
 * after signing UP, data will be transferred to userPool temporally
 * 
 * User are able to 
 * - create order of buying/ selling stocks' shares 
 * - cancel order of buying/ selling
 * - read order users have applied
 * 
 */
@Getter
@Setter
public class User {

  // user id + password
  public static Map<String, String> userPool = new HashMap<>();

  // by uesr id, order history
  private static Map<String, List<Order>> orderMap = new HashMap<>();

  private String userId;

  private String password;

  private int score;

  public User(String userId) {
    if (userId == null)
      throw new IllegalArgumentException();
    this.userId = userId;
  }

  //check if the score reach the standard of GOLD membership
  public void addScore(int score) {
    this.score += score;
  }

  public MemberShip memberShip() {
    if (score >= 100000)
      return MemberShip.GOLD;
    else if (score >= 50000)
      return MemberShip.SIVLER;
    return MemberShip.NORMAL;
  }

  // User Login to check if username and password match the data in the userPool
  public boolean login() {
    return this.userId != null && this.password != null
        && userPool.get(this.userId).equals(password);
  }

  // Restful API 
  public void changePassword(String newPassword) {
    if (userPool.get(this.userId) != null)
      userPool.put(this.userId, newPassword);
  }

  /**
   * @param placeOrderDTO include the main information what the user would like to do
   *        e.g. BUY LIMIT when price equals 40.12
   * @return Order include other information such as timestamp OrderId
   */
  public Order placeOrder(PlaceOrderDTO placeOrderDTO) {
    Order order = StockMapper.map(this.userId, placeOrderDTO);
    return addOrder(userId, order);
  }

  public void cancelOrder(int orderId) {
    Optional<Order> order = orderMap.get(this.userId).stream() //
        .filter(e -> e.getId() == orderId) //
        .findAny(); //
    order.ifPresent(o -> {
      removeOrder(this.userId, o);
    });
  }

  public static List<Order> getOrders(String userId) {
    return orderMap.get(userId);
  }

  public static Order addOrder(String userId, Order order) {
    if (orderMap.get(userId).add(order))
      return order;
    return null;
  }

  public static boolean removeOrder(String userId, Order order) {
    return orderMap.get(userId).remove(order);
  }

}
