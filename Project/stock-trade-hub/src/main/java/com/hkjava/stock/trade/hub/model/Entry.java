package com.hkjava.stock.trade.hub.model;

import lombok.Builder;

/**
 * the record of the data in the OrderBook
 * JUST for OrderBook
 */
@Builder
public class Entry {
    
    private double price;

    private int share;

}
