package com.ukream.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukream.dto.UserDTO;
import com.ukream.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    @Test
    void 회원가입_중복이메일_테스트() throws Exception {
        UserDTO userDTO = UserDTO.builder().email("test3").password("password").build();


        doThrow(DataIntegrityViolationException.class).when(userService).createUser(any());


        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("중복된 이메일이 이미 존재합니다."))
                .andDo(print());
    }



}
