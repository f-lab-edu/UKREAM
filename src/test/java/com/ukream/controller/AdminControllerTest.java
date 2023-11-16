package com.ukream.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.PageRequestDTO;
import com.ukream.dto.UserDTO;
import com.ukream.service.AdminService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

  @MockBean private AdminService adminService;

  @Autowired private MockMvc mockMvc;

  @Test
  public void 유저_목록_조회_테스트() throws Exception {
    UserDTO user = UserDTO.builder().email("email").password("password").build();
    List<UserDTO> users = Arrays.asList(user);

    given(adminService.getUsers(any(PageRequestDTO.class))).willReturn(users);

    mockMvc
        .perform(get("/admins/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].email").value("email"))
        .andDo(print());


    verify(adminService, times(1)).getUsers(any(PageRequestDTO.class));
  }

  @Test
  public void 유저_조회_테스트() throws Exception {
    UserDTO user = UserDTO.builder().userId(1L).email("email").password("password").build();

    given(adminService.getUser(user.getUserId())).willReturn(user);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/admins")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
        .andExpect(status().isCreated())
        .andDo(print());

    verify(adminService, times(1)).createAdmin(user);
  }

  @Test
  public void 관리자_생성_테스트() throws Exception {

    UserDTO user = UserDTO.builder().userId(1L).email("email").password("password").build();

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/admins")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
        .andExpect(status().isCreated())
        .andDo(print());
  }

  @Test
  void 로그인_성공_테스트() throws Exception {
    LoginFormDTO input = LoginFormDTO.builder().email("test").password("1234").build();

    UserDTO user = UserDTO.builder().email("test").password("password").build();

    given(adminService.login(any(LoginFormDTO.class))).willReturn(user);

    mockMvc
        .perform(
            post("/admins/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(input)))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void 로그아웃_테스트() throws Exception {
    mockMvc.perform(get("/admins/logout")).andExpect(status().isOk());
  }
}
