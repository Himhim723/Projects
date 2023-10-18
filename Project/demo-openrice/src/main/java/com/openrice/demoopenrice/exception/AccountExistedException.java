package com.openrice.demoopenrice.exception;

public class AccountExistedException extends Exception{
  
  public AccountExistedException(){
    super("Account has been registered");
  }
}
