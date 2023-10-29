package com.user.basicusermanagement;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.User;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;
import com.user.basicusermanagement.model.enums.Role;
import com.user.basicusermanagement.service.Impl.UserServiceImpl;


//@SpringBootTest
public class UserServiceTest {

  // @Autowired
  UserServiceImpl userService= new UserServiceImpl();
  // @MockBean
  // UserRepository userRepository;
  // @SpyBean
  // PasswordEncoder passwordEncoder;

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
  void testIsUsernameValid(){
    assertEquals(true,userService.isUsernameValid("asdugobalsdj234234jsd!#$!"));
    assertEquals(true,userService.isUsernameValid("ABC^&!!#ss123"));
    assertEquals(true,userService.isUsernameValid("ssOE23"));
    assertEquals(true,userService.isUsernameValid("aaaaaa"));
    assertEquals(true,userService.isUsernameValid("333333"));
    assertThrows(IllegalArgumentException.class, ()->userService.isUsernameValid("asf"));
  }

  @Test
  void testIsPasswordValid(){
    assertEquals(true,userService.isPasswordValid("ABCss123"));
    assertEquals(true,userService.isPasswordValid("^&!!#sS123"));
    assertEquals(true,userService.isPasswordValid("ssOE23"));
    assertThrows(IllegalArgumentException.class, ()->userService.isPasswordValid("^&!!#ss123"));
    assertThrows(IllegalArgumentException.class, ()->userService.isPasswordValid("cdk134"));
    assertThrows(IllegalArgumentException.class, ()->userService.isPasswordValid("de34_ dufg jdfiwu"));
    assertThrows(IllegalArgumentException.class, ()->userService.isPasswordValid("asf____"));
  }
  
  @Test
  void testIsEmailValid(){
    assertEquals(true,userService.isEmailValid("abcc@gmail.com"));
    assertEquals(true,userService.isEmailValid("abcc@icloud.com"));
    assertEquals(true,userService.isEmailValid("abcc@hotmail.com"));
    assertEquals(true,userService.isEmailValid("abcc@hotmail.com.hk"));
    assertThrows(IllegalArgumentException.class,()->userService.isEmailValid("abcc@hotmail.com.hke"));
    assertThrows(IllegalArgumentException.class,()->userService.isEmailValid("shdfhi@jbdf.cbv"));
    assertThrows(IllegalArgumentException.class,()->userService.isEmailValid("dhf@87342.151"));

  }

  // @Test
  // void testFindUserByUserName() throws UserException{
  //   when(userRepository.findAll()).thenReturn(List.of(userDummy));
  //   assertEquals(userDummy,userService.findUserByUsername("cow1234"));
  //   assertThrows(UserException.class,()->userService.findUserByUsername("abcddef"));
  //   assertThrows(UserException.class,()->userService.findUserByUsername("ahgih123"));
  // }
  
  // @Test
  // void testFindUserIdByUsername()throws UserException{
  //   when(userRepository.findAll()).thenReturn(List.of(userDummy));
  //   assertEquals(1L,userService.findUserIdByUsername("cow1234"));
  //   assertThrows(UserException.class,()->userService.findUserIdByUsername("abcddef"));
  // }

  @Test
  void testSignUpError() throws UserException{
    //invalid Username
    UserSignUpDTO dto2 = UserSignUpDTO.builder().username("hi3")
                                                .password("Admin1234")
                                                .fullName("HIM")
                                                .dob(LocalDate.of(1998, 7, 23))
                                                .address("ABC Building")
                                                .contact("21111234")
                                                .email("hello@gmail.com").build();
    assertThrows(IllegalArgumentException.class ,()->userService.signUp(dto2));

    //invalid Password
    UserSignUpDTO dto3 = UserSignUpDTO.builder().username("hiasd3")
                                                .password("xdmidfdb")
                                                .fullName("HIM")
                                                .dob(LocalDate.of(1998, 7, 23))
                                                .address("ABC Building")
                                                .contact("21111234")
                                                .email("hello@gmal.com").build();
    assertThrows(IllegalArgumentException.class ,()->userService.signUp(dto3));

    //Invalid Email
    UserSignUpDTO dto4 = UserSignUpDTO.builder().username("him123")
                                                .password("Admin1234")
                                                .fullName("HIM")
                                                .dob(LocalDate.of(1998, 7, 23))
                                                .address("ABC Building")
                                                .contact("21111234")
                                                .email("hello@gmal.com").build();
    assertThrows(IllegalArgumentException.class ,()->userService.signUp(dto4));
  }

  // @Test
  // void testValidateUser() throws UserException{
  //   assertThrows(UserException.class, ()->userService.validateUser(userDummy.getUsername(), "abDc1234"));
  // }

  // @Test
  // void testUserExisted() throws UserException{
  //   when(userRepository.findAll()).thenReturn(List.of(userDummy));
  //   assertEquals(false,userService.userExisted("SomeOtherUser"));
  //   assertThrows(UserException.class,()->userService.userExisted(userDummy.getUsername()));
  // }

  // @Test
  // void testVoidMethod() throws UserException{
  //   //to prevent changing state of userdummy
  //   User user = User.builder().userID(2L)
  //   .username("cow1234").password("acc54121")
  //   .fullName("ABC DUMMY").gender("M")
  //   .dob(LocalDate.of(1999, 1, 1))
  //   .email("admin133@gmail.com")
  //   .address("KFC Building")
  //   .contact("+852 67121233")
  //   .role(Role.USER)
  //   .regDateTime(LocalDateTime.now()).build();

  //   when(userRepository.findAll()).thenReturn(List.of(user));
  //   assertEquals(2L,user.getUserID());
  //   assertEquals("admin133@gmail.com",user.getEmail());
  //   assertEquals("acc54121",user.getPassword());
  //   assertEquals("KFC Building",user.getAddress());
  //   userService.adjustEmail(user.getUsername(), "adjusted@gmail.com");
  //   userService.adjustAddress(user.getUsername(), "new Address");
  //   assertEquals("adjusted@gmail.com",user.getEmail());
  //   assertEquals("new Address",user.getAddress());

  // }

  // @Test
  // void testAuthenticate() throws UserException{
  //   UserLoginDTO wrongKey = new UserLoginDTO("cow1234", "Aa5411");
  //   when(userRepository.findAll()).thenReturn(List.of(userDummy));
  //   assertThrows(UserException.class,()->userService.authenticate(wrongKey));
  // }

}
