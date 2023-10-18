package com.hkjava.demo.demofinnhub.model.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Point {

  private Price closePrice;

  private TranDayTime tranDateTime;

  public Point(double closePrice, TranDayTime tranDateTime) {
    this.closePrice = new Price(closePrice);
    this.tranDateTime = tranDateTime;
  }

}
