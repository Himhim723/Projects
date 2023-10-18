package com.hkjava.demo.demofinnhub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.service.CompanyService;

@Component
@EnableScheduling
// @Profile("enableScheduling")
public class SchedulerTaskConfig {

  public static boolean start = false;

  @Autowired
  private CompanyService companyService;

  @Scheduled(fixedRate = 60000) // run the method every minute after configuration ends
  public void fixedRateTask() throws InterruptedException, FinnhubException {
    if (start) {
      companyService.refresh();
    }
  }

  // @Scheduled(fixedDelay = 4000) // 4 sec
  public void fixedDelayTask() throws InterruptedException {
    if (start) {
      System.out.println("FixedDelay Task - " + System.currentTimeMillis());
      Thread.sleep(5000L); // doing task ... 5 seconds
    }
  }

  // @Scheduled(cron = "0/30 * 9-23 25 9 MON-FRI")
  public void fixedTimeTask() {
    if (start) {
      System.out.println("FixedTime Task- " + System.currentTimeMillis());
    }
  }
}
