package com.user.basicusermanagement.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userID;
  private String username;
  private String password;
  @Column(name = "full_name")
  private String fullName;
  private String gender;
  private LocalDate dob;
  private String email;
  private String address;
  private String contact;
  private String role;
  @Column(name = "reg_date_time")
  private LocalDateTime regDateTime;
  private LocalDateTime lastLogIn;

  public int getAge(){
    LocalDate now = LocalDate.now();
    if(now.getMonth().getValue()>dob.getMonth().getValue()
    || now.getMonth()==dob.getMonth()&&now.getDayOfMonth()>=dob.getDayOfMonth())
    return now.getYear()-dob.getYear();
    else return now.getYear()-dob.getYear()-1;
  }

}
