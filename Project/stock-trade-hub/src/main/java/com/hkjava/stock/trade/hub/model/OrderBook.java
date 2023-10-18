package com.hkjava.stock.trade.hub.model;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 * the class represents the orderBook of the particular stock
 * Each stock contains one orderBook recording the buy and sell record of users
 * Designed for frontend to draw the orderBook
 * 
 */
@Getter
public class OrderBook {

  // by symbol
  private static Map<String, OrderBook> data = new HashMap<>();

  public static OrderBook getData(String symbol) {
    return data.get(symbol);
  }

  private Deque<Entry> buyBook;

  private Deque<Entry> sellBook;

}
