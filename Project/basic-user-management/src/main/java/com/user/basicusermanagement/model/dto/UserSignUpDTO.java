package com.user.basicusermanagement.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserSignUpDTO {
  private String userName;
  private String password;
  private String fullName;
  private String gender;
  private LocalDate dob;
  private String email;
  private String address;
  private String contact;

}
