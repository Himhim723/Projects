package com.hkjava.demo.demofinnhub.model.graph;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SourcePoint {

  public static Map<String, List<SourcePoint>> sourceMap = new HashMap<>();

  // in real life, this should be Interval.MIN_1
  private final Interval interval = Interval.DAY;

  private Price closePrice;

  private TranDayTime tranDayTime;

  public SourcePoint(Price closePrice, TranDayTime tranDayTime) {
    this.closePrice = closePrice;
    this.tranDayTime = tranDayTime;
  }

  public SourcePoint(double closePrice, LocalDateTime localDateTime) {
    this.closePrice = new Price(closePrice);
    this.tranDayTime = new TranDayTime(localDateTime);
  }

  public SourcePoint(double closePrice, LocalDate localDate) {
    this.closePrice = new Price(closePrice);
    this.tranDayTime = new TranDayTime(localDate.atStartOfDay());
  }

  public Point toPoint() {
    return Point.builder() //
        .closePrice(this.closePrice) //
        .tranDateTime(this.tranDayTime) //
        .build();
  }

  public static Map<String, List<SourcePoint>> getSourceMap() {
    return sourceMap;
  }

}
