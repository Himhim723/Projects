package com.user.basicusermanagement.model.enums;

import lombok.Getter;

@Getter
public enum Role {
  ADMIN("Admin"),
  REGULAR("Regular"),
  PREMIUM("Premium");

  private String desc;

  private Role(String desc){
    this.desc = desc;
  }

  public static Role getRole(String role){
    for(Role r: Role.values()){
      if (r.getDesc().equals(role))
      return r;
    }
    throw new IllegalArgumentException("Invalid Role");
  }

}
