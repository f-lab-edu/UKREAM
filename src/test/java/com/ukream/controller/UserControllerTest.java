package com.ukream.controller;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukream.dto.AddressDTO;
import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.UserDTO;
import com.ukream.error.exception.LoginFailureException;
import com.ukream.service.AddressService;
import com.ukream.service.UserService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @MockBean private UserService userService;

  @MockBean private AddressService addressService;

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

  @Test
  void 사용자_정보_조회_테스트() throws Exception {
    UserDTO user = UserDTO.builder().userId(1L).email("test").build();
    Long userId = user.getUserId();
    MockHttpSession session = new MockHttpSession();
    session.setAttribute("LOGIN_USER_ID", userId);
    given(userService.getUserInfo(userId)).willReturn(user);

    mockMvc
        .perform(get("/users").session(session))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userId").value(userId))
        .andExpect(jsonPath("$.email").value("test"));

    verify(userService, times(1)).getUserInfo(userId);
  }

  @Test
  void 주소_생성_테스트() throws Exception {
    AddressDTO address =
        AddressDTO.builder().name("test").address("addressTest").zipcode("12345").build();
    Long userId = 1L;
    MockHttpSession session = new MockHttpSession();
    session.setAttribute("LOGIN_USER_ID", userId);

    doNothing().when(userService).checkUserExists(userId);
    doNothing().when(addressService).createAddress(any(AddressDTO.class));

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/users/address")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(address)))
        .andExpect(status().isCreated())
        .andDo(print());
        
    verify(userService, times(1)).checkUserExists(userId);
    verify(addressService, times(1)).createAddress(any(AddressDTO.class));
  }

  @Test
  void 주소_목록_조회_테스트() throws Exception {
    AddressDTO address =
        AddressDTO.builder().name("test").address("addressTest").zipcode("12345").build();

    List<AddressDTO> addressList = Arrays.asList(address);

    Long userId = 1L;
    MockHttpSession session = new MockHttpSession();

    session.setAttribute("LOGIN_USER_ID", userId);

    doNothing().when(userService).checkUserExists(userId);
    given(addressService.getAddressList(userId)).willReturn(addressList);

    mockMvc
        .perform(get("/users/address").session(session))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("test"))
        .andExpect(jsonPath("$[0].address").value("addressTest"));

    verify(userService, times(1)).checkUserExists(userId);
    verify(addressService, times(1)).getAddressList(userId);
  }

  @Test
  void 주소_조회_테스트() throws Exception {
    AddressDTO address =
        AddressDTO.builder().name("test").address("addressTest").zipcode("12345").build();

    Long addressId = 1L;
    Long userId = 1L;
    MockHttpSession session = new MockHttpSession();

    session.setAttribute("LOGIN_USER_ID", userId);

    doNothing().when(userService).checkUserExists(userId);
    given(addressService.getAddress(addressId, userId)).willReturn(address);

    mockMvc
        .perform(get("/users/address/{addressId}", addressId).session(session))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("test"))
        .andExpect(jsonPath("$.address").value("addressTest"));

    verify(userService, times(1)).checkUserExists(userId);
    verify(addressService, times(1)).getAddress(addressId, userId);
  }

  @Test
  void 주소_삭제_테스트() throws Exception {
    Long addressId = 1L;

    doNothing().when(addressService).deleteAddress(addressId);

    mockMvc.perform(delete("/users/address/{addressId}", addressId)).andExpect(status().isOk());

    verify(addressService, times(1)).deleteAddress(addressId);
  }

  @Test
  void 주소_수정_테스트() throws Exception {

    AddressDTO address =
        AddressDTO.builder().name("test").address("addressTest").zipcode("12345").build();

    Long addressId = 1L;

    address.setAddressId(addressId);

    doNothing().when(addressService).updateAddress(any(AddressDTO.class));

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/users/address/{addressId}", addressId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(address)))
        .andExpect(status().isOk())
        .andDo(print());

    verify(addressService, times(1)).updateAddress(any(AddressDTO.class));
  }
}
