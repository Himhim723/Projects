package com.user.basicusermanagement;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.dto.UserDTO;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;
import com.user.basicusermanagement.repository.UserRepository;
import com.user.basicusermanagement.service.UserService;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @MockBean
  private UserRepository userRepository;

  @Test
  void testIsUsernameValid(){
    assertEquals(true,userService.isUsernameValid("ABCss123"));
    assertEquals(true,userService.isUsernameValid("ABC^&!!#ss123"));
    assertEquals(true,userService.isUsernameValid("ssOE23"));
  }
  
  @Test
  void testSignUp() throws UserException{
    //Normal Scenario
    UserSignUpDTO dto = UserSignUpDTO.builder().userName("him123")
                                                .password("Admin1234")
                                                .fullName("HIM")
                                                .dob(LocalDate.of(1998, 7, 23))
                                                .address("ABC Building")
                                                .contact("21111234")
                                                .email("hello@gmail.com").build();
    UserDTO expectedUser = UserDTO.builder().role("Regular").fullName("HIM")
                                            .age(25).dob(LocalDate.of(1998, 7, 23))
                                            .address("ABC Building")
                                            .contact("21111234")
                                            .email("hello@gmail.com").build();
    assertEquals(expectedUser ,userService.signUp(dto));

    //invalid Username
    UserSignUpDTO dto2 = UserSignUpDTO.builder().userName("hi3")
                                                .password("Admin1234")
                                                .fullName("HIM")
                                                .dob(LocalDate.of(1998, 7, 23))
                                                .address("ABC Building")
                                                .contact("21111234")
                                                .email("hello@gmail.com").build();
    assertThrows(IllegalArgumentException.class ,()->userService.signUp(dto2));

    //invalid Password
    UserSignUpDTO dto3 = UserSignUpDTO.builder().userName("hiasd3")
                                                .password("xdmidfdb")
                                                .fullName("HIM")
                                                .dob(LocalDate.of(1998, 7, 23))
                                                .address("ABC Building")
                                                .contact("21111234")
                                                .email("hello@gmal.com").build();
    assertThrows(IllegalArgumentException.class ,()->userService.signUp(dto3));

    //Invalid Email
    UserSignUpDTO dto4 = UserSignUpDTO.builder().userName("him123")
                                                .password("Admin1234")
                                                .fullName("HIM")
                                                .dob(LocalDate.of(1998, 7, 23))
                                                .address("ABC Building")
                                                .contact("21111234")
                                                .email("hello@gmal.com").build();
    assertThrows(IllegalArgumentException.class ,()->userService.signUp(dto4));
  }




}
