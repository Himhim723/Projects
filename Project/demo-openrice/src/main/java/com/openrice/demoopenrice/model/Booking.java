package com.openrice.demoopenrice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.openrice.demoopenrice.entity.CMember;
import com.openrice.demoopenrice.entity.Shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Bookings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String registerTime; 
  @ManyToOne
  @JsonIgnoreProperties(value = {"menu","comments","bookings","liked"})
  private Shop shopName;
  private String bookingDate;
  private String bookingTime;
  @ManyToOne
  @JsonIgnoreProperties(value = {"member","creditCard","myFavShops","myBookings"})
  private CMember userName;
  private int numOfCustomer;


}