package com.user.basicusermanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.user.basicusermanagement.model.enums.Role;

public class UserAgeCalTest {

  User userDummy = User.builder().userID(1L)
                         .username("cow1234")
                         .password("acc54121")
                         .fullName("ABC DUMMY")
                         .gender("M")
                         .dob(LocalDate.of(1999, 1, 1))
                         .email("admin133@gmail.com")
                         .address("KFC Building")
                         .role(Role.USER)
                         .contact("+852 67121233")
                         .regDateTime(LocalDateTime.now())
                         .build();

  @Test
  void testAgeCal(){
    User user = new User();
    LocalDate dob1 = LocalDate.of(1998, 7, 23);
    LocalDate dob2 = LocalDate.of(1899, 12, 11);
    user.setDob(dob1);
    Assertions.assertEquals(25,user.getAge());
    user.setDob(dob2);
    Assertions.assertEquals(123,user.getAge());
  }

  @Test
  void testGetter(){
    Assertions.assertTrue(userDummy.getFullName().equals("ABC DUMMY"));
  }

  @Test
  void testSetter(){
    userDummy.setFullName("Lee Cheuk Him");
    Assertions.assertTrue(userDummy.getFullName().equals("Lee Cheuk Him"));
    userDummy.setContact("+852 61119923");
    Assertions.assertEquals(userDummy.getContact(), "+852 61119923");
  }


}
