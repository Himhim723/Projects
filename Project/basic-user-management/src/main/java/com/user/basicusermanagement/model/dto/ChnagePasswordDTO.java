package com.user.basicusermanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChnagePasswordDTO {
  private String oldPassword;
  private String newPassword;
  private String confirmation;

}
