package com.openrice.demoopenrice.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.openrice.demoopenrice.model.Booking;
import com.openrice.demoopenrice.model.Comment;
import com.openrice.demoopenrice.model.Food;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Shop")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Shop implements Serializable{
  @Id
  private String name;
  private String address;
  @OneToMany(cascade = CascadeType.ALL)
  private List<Food> menu;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Comment> comments;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Booking> bookings;

  // Can also use ManyToMany to complete the task along with JoinTable
  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "myFavShops")
  @JsonIgnoreProperties(value = {"member","email","creditCard","myFavShops","myBookings"})
  private List<CMember> liked;

}