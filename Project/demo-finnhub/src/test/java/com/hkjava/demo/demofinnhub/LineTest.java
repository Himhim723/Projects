package com.hkjava.demo.demofinnhub;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import com.hkjava.demo.demofinnhub.model.graph.Interval;
import com.hkjava.demo.demofinnhub.model.graph.Line;
import com.hkjava.demo.demofinnhub.model.graph.Point;
import com.hkjava.demo.demofinnhub.model.graph.Price;
import com.hkjava.demo.demofinnhub.model.graph.SourcePoint;
import com.hkjava.demo.demofinnhub.model.graph.TranDayTime;

// Line object has List<Point>
// [10.0, 23/9], [11.0, 24/9], [12.0, 25/9], [13.0, 26/9], [14.0, 27/9],
// [20.0, 28/9], [20.0, 29/9], [20.0, 30/9], [20.0, 1/10], [20.0, 2/10],

// 5-day moving average
// [12.0, 27/9], [14.0, 28/9], [15.8, 29/9], [17.4, 30/9], [18.8, 1/10],
// [20.0, 2/10]
@TestInstance(Lifecycle.PER_CLASS)
public class LineTest {

  private Map<String, List<SourcePoint>> mockSrcMap;

  @BeforeAll
  void init() {
    mockSrcMap = new HashMap<>();
    List<SourcePoint> sourcePoints = new ArrayList<>();
    sourcePoints.add(new SourcePoint(10.0d, LocalDate.of(2023, 9, 23)));
    sourcePoints.add(new SourcePoint(20.0d, LocalDate.of(2023, 9, 28)));
    sourcePoints.add(new SourcePoint(20.0d, LocalDate.of(2023, 9, 29)));
    sourcePoints.add(new SourcePoint(11.0d, LocalDate.of(2023, 9, 24)));
    sourcePoints.add(new SourcePoint(20.0d, LocalDate.of(2023, 10, 1)));
    sourcePoints.add(new SourcePoint(20.0d, LocalDate.of(2023, 9, 30)));
    sourcePoints.add(new SourcePoint(12.0d, LocalDate.of(2023, 9, 25)));
    sourcePoints.add(new SourcePoint(13.0d, LocalDate.of(2023, 9, 26)));
    sourcePoints.add(new SourcePoint(20.0d, LocalDate.of(2023, 10, 2)));
    sourcePoints.add(new SourcePoint(14.0d, LocalDate.of(2023, 9, 27)));
    mockSrcMap.put("00005", sourcePoints);

    // mock static class
    mockStatic(SourcePoint.class);
  }

  @Test
  void testLineConstructor() {
    // Mock static method
    when(SourcePoint.getSourceMap()).thenReturn(mockSrcMap);

    // Test Constructor
    Line line = new Line("00005", Interval.DAY);

    // Assert the elements are in the list of closePoint
    assertThat(line.getClosePoints(),
        hasItem(new Point(13.0d, new TranDayTime("2023-09-26"))));
    assertThat(line.getClosePoints(),
        hasItem(hasProperty("closePrice", equalTo(new Price(14.0d)))));
    assertThat(line.getClosePoints(), hasItem(
        hasProperty("tranDateTime", equalTo(new TranDayTime("2023-10-01")))));
    assertThat(line.getClosePoints().size(), equalTo(10));
  }

  @Test
  void testLineMoveAverage() {
    // Mock static method
    when(SourcePoint.getSourceMap()).thenReturn(mockSrcMap);

    Line line = new Line("00005", Interval.DAY);
    List<Point> moveAvg = line.movingAverage(5);
    // System.out.println("move=" + moveAvg);
    assertThat(moveAvg,
        hasItem(new Point(12.0d, new TranDayTime("2023-09-27"))));
    assertThat(moveAvg,
        hasItem(new Point(14.0d, new TranDayTime("2023-09-28"))));
    assertThat(moveAvg,
        hasItem(new Point(15.8d, new TranDayTime("2023-09-29"))));
    assertThat(moveAvg,
        hasItem(new Point(17.4d, new TranDayTime("2023-09-30"))));
    assertThat(moveAvg,
        hasItem(new Point(18.8d, new TranDayTime("2023-10-01"))));
    assertThat(moveAvg,
        hasItem(new Point(20.0d, new TranDayTime("2023-10-02"))));
    assertThat(moveAvg.size(), equalTo(6));
  }
}

