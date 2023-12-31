package com.user.basicusermanagement.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class UserDTO {
  private Long userID;
  private String role;
  private String fullName;
  private Integer age;
  private String gender;
  private LocalDate dob;
  private String email;
  private String address;
  private String contact;



}
