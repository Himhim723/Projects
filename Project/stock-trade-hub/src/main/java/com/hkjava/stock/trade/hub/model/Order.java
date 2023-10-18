package com.hkjava.stock.trade.hub.model;

import java.time.LocalDateTime;
import com.hkjava.stock.trade.hub.enums.Action;
import com.hkjava.stock.trade.hub.enums.OrderType;
import lombok.Getter;

/**
 * Order represents the official record recording a user actions
 * assigning an id to the order and record the application timestamp
 */

// No other lombok Constructor/ Builder, lock the id increment
@Getter
public class Order {

  private static int idCounter = 0;

  private int id;

  private String userId;

  private String symbol;

  private LocalDateTime tranDateTime;

  private Action action;

  private OrderType orderType;

  private double price;

  private int share;

  public static Order of(String userId, LocalDateTime tranDateTime, Action action,
      OrderType orderType, String symbol,double price, int share) {
    return new Order(userId, tranDateTime, action, orderType, symbol, price, share);
  }

  private Order(String userId, LocalDateTime tranDateTime, Action action,
      OrderType orderType, String symbol,double price, int share) {
    this.id = ++idCounter; 
    this.userId = userId;
    this.tranDateTime = tranDateTime;
    this.action = action;
    this.orderType = orderType;
    this.price = price;
    this.share = share;
  }

}
