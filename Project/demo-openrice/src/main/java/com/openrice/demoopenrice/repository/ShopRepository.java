package com.openrice.demoopenrice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.openrice.demoopenrice.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop,String> {
  
  @Query(nativeQuery = true, value = "Select * from shop where my_shop = :user")
  List<Shop> findShopsByUser(@Param(value = "user") String user);

}
