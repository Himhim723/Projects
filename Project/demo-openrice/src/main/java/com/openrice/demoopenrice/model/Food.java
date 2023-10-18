package com.openrice.demoopenrice.model;

import com.openrice.demoopenrice.entity.Shop;

import jakarta.persistence.Entity;
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
@Table(name = "Food")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {
  @Id
  private int id;
  private String name;
  private double price;
  

}
