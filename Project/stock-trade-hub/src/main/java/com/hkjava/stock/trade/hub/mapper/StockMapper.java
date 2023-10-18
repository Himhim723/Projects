package com.hkjava.stock.trade.hub.mapper;

import static java.util.stream.Collectors.toList;
import java.time.LocalDateTime;
import com.hkjava.stock.trade.hub.dto.req.PlaceOrderDTO;
import com.hkjava.stock.trade.hub.dto.resp.OrderBookDTO;
import com.hkjava.stock.trade.hub.model.Order;
import com.hkjava.stock.trade.hub.model.OrderBook;


// DTO is designed for displaying data for the clients
public class StockMapper {

  // TBC. Sort by Price
  public static OrderBookDTO map(String symbol, OrderBook book) {
    return OrderBookDTO.builder() //
        .symbol(symbol) //
        .buyBook(book.getBuyBook().stream().collect(toList())) //
        .sellBook(book.getSellBook().stream().collect(toList())) //
        .build();
  }

  public static Order map(String uesrId, PlaceOrderDTO orderDTO) {
    return Order.of(uesrId, LocalDateTime.now(), orderDTO.getAction(),
        orderDTO.getOrderType(), orderDTO.getSymbol(),orderDTO.getPrice(), orderDTO.getShare());

  }

}
