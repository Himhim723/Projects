package com.openrice.demoopenrice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openrice.demoopenrice.model.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
  
}
