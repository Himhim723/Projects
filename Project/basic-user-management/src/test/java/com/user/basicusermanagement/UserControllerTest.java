package com.user.basicusermanagement;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.user.basicusermanagement.controller.UserController;
import com.user.basicusermanagement.infra.exception.UserException;
import com.user.basicusermanagement.model.dto.UserDTO;
import com.user.basicusermanagement.model.dto.UserLoginDTO;
import com.user.basicusermanagement.model.dto.UserSignUpDTO;
import com.user.basicusermanagement.service.UserService;

import jakarta.validation.ConstraintValidator;

@WebMvcTest(UserController.class)
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private UserSignUpDTO signUpDTO;
  @MockBean
  private UserLoginDTO loginDTO;

  @Test
  void testSignUp() throws Exception{
    // UserDTO test1 = UserDTO.builder().fullName("HIM").age(11).address("ABC Building").contact("21111234").email("hello@gmail.com").build();
    // when(userService.signUp(signUpDTO)).thenReturn(test1);
    
    mockMvc.perform(post("/v1/signUp", signUpDTO))
    .andExpect(status().isBadRequest())
      //.andExpect(status().isOk())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
      //.andExpect(jsonPath("$.code").value(20000));
  }

}
