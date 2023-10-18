package com.hkjava.stock.trade.hub.model;

import lombok.AllArgsConstructor;

/**
 * The Class describing a person before signing in the application
 */

@AllArgsConstructor
public class Guest {

  private String userId;

  private String password;

  private String email;

  public boolean isUserIdValid() {
    return User.userPool.get(this.userId) != null;
  }

  public boolean isPasswordValid() {
    return this.password.length()>=6;
  }

  //guests can only registered with Gmail acc or Apple acc
  public boolean isEmailValid() {
    return this.email.endsWith("@gmail.com")|| email.endsWith("@icloud.com");
  }

  public boolean signUp() {
    if (this.userId == null || this.password == null || this.email == null)
      return false; // TBC. Missing Input from Front end
    if (!this.isUserIdValid() || !this.isPasswordValid()
        || !this.isEmailValid())
      return false; // TBC. Specific Exception
    // TBC. Put to user Map
    User.userPool.put(this.userId, this.password);
    return true;
  }


}
