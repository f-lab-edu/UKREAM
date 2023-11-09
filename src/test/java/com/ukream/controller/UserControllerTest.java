package com.ukream.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukream.dto.UserDTO;
import com.ukream.error.exception.LoginFailureException;
import com.ukream.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @MockBean private UserService userService;

  @Autowired private MockMvc mockMvc;

  @Test
  void 회원가입_테스트() throws Exception {
    UserDTO userDTO = UserDTO.builder().email("test2").password("password").build();

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDTO)))
        .andExpect(status().isCreated())
        .andDo(print());
  }

  @Test
  void 회원가입_유효성_테스트() throws Exception {
    UserDTO userDTO = UserDTO.builder().password("password").build();

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.email").value("이메일은 필수입니다.")) // JSONPath를 사용하여 내용을 검증
        .andDo(print());
  }

}
