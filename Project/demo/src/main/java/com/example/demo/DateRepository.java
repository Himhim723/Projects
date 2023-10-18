package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<People,Long>{
  
}
