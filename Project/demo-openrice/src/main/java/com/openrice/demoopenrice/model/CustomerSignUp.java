package com.openrice.demoopenrice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CustomerSignUp {
  private String username;
  private String password;
  private String name;
  private String phone;
  private String email;
  private String creditCard;
}
