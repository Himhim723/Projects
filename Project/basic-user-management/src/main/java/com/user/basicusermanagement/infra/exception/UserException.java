package com.user.basicusermanagement.infra.exception;

public class UserException extends Exception{
  
  private int code;

  public UserException(Code code){
    super(code.getDesc());
    this.code = code.getCode();
  }
}
