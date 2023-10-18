package com.openrice.demoopenrice.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openrice.demoopenrice.entity.BMember;
import com.openrice.demoopenrice.entity.Shop;
import com.openrice.demoopenrice.model.Food;
import com.openrice.demoopenrice.service.Imp.BusinessService;
import com.openrice.demoopenrice.service.Imp.CustomerService;

@Configuration
public class AppConfig {

  @Autowired
  private BusinessService businessService;
  @Autowired
  private CustomerService customerService;

  // @Bean
  // CommandLineRunner commandLineRunner(){
  //   return arg->{BMember business1 = BMember.builder().username("business1").password("admin1324").build();
  //   BMember business2 = BMember.builder().username("business2").password("admin1324").build();
  //   BMember business3 = BMember.builder().username("business3").password("admin1324").build();
  //   BMember business4 = BMember.builder().username("business4").password("admin1324").build();
  //   BMember business5 = BMember.builder().username("business5").password("admin1324").build();
  //   businessService.signUp(business1);
  //   businessService.signUp(business2);
  //   businessService.signUp(business3);
  //   businessService.signUp(business4);
  //   businessService.signUp(business5);
  //   businessService.addShop(business1.getUsername(), Shop.builder().name("Pacific Coffee").build());
  //   businessService.addShop(business1.getUsername(), Shop.builder().name("Starbucks").build());
  //   businessService.addShop(business1.getUsername(), Shop.builder().name("McDonald").build());
  //   businessService.addShop(business4.getUsername(), Shop.builder().name("Itamomo").build());
  //   businessService.addShop(business5.getUsername(), Shop.builder().name("Cafe de Carol").build());
  //   businessService.addFoodToShop(business1.getUsername(), "Pacific", 
  //         Arrays.asList(Food.builder().name("Long Black").price(12.5).build(),
  //                       Food.builder().name("Espresso").price(12.5).build(),
  //                       Food.builder().name("Mocha").price(12.5).build(),
  //                       Food.builder().name("Iced Chocolate Chip").price(12.5).build(),
  //                       Food.builder().name("Lattee").price(12.5).build()));
  //   businessService.addFoodToShop(business1.getUsername(), "Starbucks", 
  //         Arrays.asList(Food.builder().name("Long Black").price(12.5).build(),
  //                       Food.builder().name("Espresso").price(12.5).build(),
  //                       Food.builder().name("Mocha").price(12.5).build(),
  //                       Food.builder().name("Iced Chocolate Chip").price(12.5).build(),
  //                       Food.builder().name("Lattee").price(12.5).build()));
    

  //   };
  // }
  
}
