package com.user.basicusermanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.user.basicusermanagement.controller.UserController;
import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.dto.UserDTO;
import com.user.basicusermanagement.model.dto.UserLoginDTO;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;
import com.user.basicusermanagement.repository.UserRepository;
import com.user.basicusermanagement.service.UserService;

import jakarta.validation.ConstraintValidator;

@WebMvcTest(UserController.class)
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  //private UserSignUpDTO signUpDTO = new UserSignUpDTO();

  @Test
  void testSignUp() throws Exception{
    // UserDTO test1 = UserDTO.builder().fullName("HIM").age(11).address("ABC Building").contact("21111234").email("hello@gmail.com").build();
    // when(userService.signUp(signUpDTO)).thenReturn(test1);
    // UserSignUpDTO signUpDTO = new UserSignUpDTO();
    // when(userService.signUp(signUpDTO)).thenReturn(new UserDTO(1L, "Admin", "asdfasdfasd", 14, "adfadfa", LocalDate.now(), "asdfa", "asdfsd", "12312321"));
    // mockMvc.perform(post("/v1/signUp", signUpDTO))
    // .andExpect(status().isBadRequest());
    UserSignUpDTO signUpDTO = UserSignUpDTO.builder().fullName("HIM").address("ABC Building").contact("21111234").email("hello@gmail.com").build();
    UserDTO expectedUser = UserDTO.builder().fullName("HIM").address("ABC Building").build();
    when(userService.signUp(signUpDTO)).thenReturn(expectedUser);
    
    mockMvc.perform(post("/v1/signUp", signUpDTO))
    .andExpect(status().isBadRequest())
    .andDo(print());
  }

}
