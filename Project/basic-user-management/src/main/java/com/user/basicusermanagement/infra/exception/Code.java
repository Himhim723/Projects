package com.user.basicusermanagement.infra.exception;

import lombok.Getter;

@Getter
public enum Code {
  OK(20000, "OK"),
  IAE_EXCEPTION(90000, "Illegal Argument Exception."),
  ALREADY_EXIST(90001,"Entity Already Existed"),
  ENTITY_NOT_FOUND(90002,"Entity Not Found"),
  VALIDATOR_FAILURE(90003,"Incorrect Username/Password")
  ;

  private int code;
  private String desc;

  private Code(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static Code fromCode(int code) {
    for (Code c : Code.values()) {
      if (c.code == code) {
        return c;
      }
    }
    throw new IllegalArgumentException(); 
    //if no the code does not match one of these error code
  }
}
