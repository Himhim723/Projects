package com.user.basicusermanagement.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserSignUpDTO{
  private String username;
  private String password;
  private String fullName;
  private String gender;
  private LocalDate dob;
  private String email;
  private String address;
  private String contact;

}
