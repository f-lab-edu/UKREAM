package com.ukream.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukream.dto.LoginFormDTO;
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
  @Disabled
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
  @Disabled
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

  @Test
  void 로그인_성공_테스트() throws Exception {
    LoginFormDTO input = LoginFormDTO.builder().email("test").password("1234").build();

    UserDTO user = UserDTO.builder().email("test").password("password").build();

    given(userService.login(any(LoginFormDTO.class))).willReturn(user);

    mockMvc
        .perform(
            post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(input)))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void 이메일을_입력하지않은_로그인_실패_테스트() throws Exception {
    LoginFormDTO userDTO = LoginFormDTO.builder().password("password").build();

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.email").value("이메일은 필수입니다.")) // JSONPath를 사용하여 내용을 검증
        .andDo(print());
  }

  @Test
  void 비밀번호를_입력하지않은_로그인_실패_테스트() throws Exception {
    LoginFormDTO userDTO = LoginFormDTO.builder().email("email").build();

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDTO)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.password").value("비밀번호는 필수입니다.")) // JSONPath를 사용하여 내용을 검증
        .andDo(print());
  }

  @Test
  void 로그인_실패_테스트() throws Exception {
    LoginFormDTO userDTO = LoginFormDTO.builder().email("email").password("password").build();

    given(userService.login(any(LoginFormDTO.class))).willThrow(LoginFailureException.class);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDTO)))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }
}
