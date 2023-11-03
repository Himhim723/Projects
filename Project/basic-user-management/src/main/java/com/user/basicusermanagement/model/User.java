package com.user.basicusermanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.user.basicusermanagement.model.enums.Role;

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
@Table(name = "Registrated_User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userID;
  private String username;
  @Column(name = "U_Password") 
  //Password is a key word in Postgresql. Error will be made when altering this attribute
  private String password;
  @Column(name = "full_name")
  private String fullName;
  private String gender;
  private LocalDate dob;
  private String email;
  @Column(name = "U_Address")
  private String address;
  private String contact;
  @Column(name = "U_Role")
  private Role role;
  @Column(name = "reg_date_time")
  private LocalDateTime regDateTime;

  public int getAge(){
    LocalDate now = LocalDate.now();
    if(now.getMonth().getValue()>dob.getMonth().getValue()
    || now.getMonth()==dob.getMonth()&&now.getDayOfMonth()>=dob.getDayOfMonth())
    return now.getYear()-dob.getYear();
    else return now.getYear()-dob.getYear()-1;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }

}
