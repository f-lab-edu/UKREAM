package com.ukream.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.UserDTO;
import com.ukream.error.exception.LoginFailureException;
import com.ukream.error.exception.UserNotFoundException;
import com.ukream.mapper.AdminMapper;
import com.ukream.mapper.UserMapper;
import com.ukream.util.SHA256Util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
  @Mock private AdminMapper adminMapper;

  @Mock private UserMapper userMapper;

  @InjectMocks private AdminService adminService;

  @Test
  void 유저_목록_조회_성공_테스트() {

    UserDTO user = UserDTO.builder().userId(1L).email("test").password("password").build();
    List<UserDTO> users = Arrays.asList(user);
    given(adminService.getUsers()).willReturn(users);

    // 예외가 발생하지 않는지 검증
    assertDoesNotThrow(() -> adminService.getUsers());
  }

  @Test
  void 유저_조회_성공_테스트() {

    UserDTO user = UserDTO.builder().userId(1L).email("test").password("password").build();
    given(adminService.getUser(user.getUserId())).willReturn(user);

    // 예외가 발생하지 않는지 검증
    assertDoesNotThrow(() -> adminService.getUser(user.getUserId()));
  }

  @Test
  void 유저_조회_실패_테스트() {

    UserDTO user = UserDTO.builder().userId(1L).email("test").password("password").build();
    given(adminMapper.getUser(user.getUserId())).willReturn(null);

    // 예외가 발생하는지 검증
    assertThrows(
        UserNotFoundException.class,
            () -> {
                adminService.getUser(user.getUserId());
            });
  }

  @Test
  void 관리자_생성_테스트(){
    
    UserDTO user = UserDTO.builder().email("test").password("password").build();
    given(userMapper.checkDuplicatedEmail(user.getEmail())).willReturn(false);
    // 예외가 발생하지 않는지 검증
    assertDoesNotThrow(() -> adminService.createAdmin(user));

  }

  @Test
  void 로그인_테스트(){
    // 로그인폼
    LoginFormDTO input = LoginFormDTO.builder().email("test").password("password").build();
    // 비밀번호암호화
    input.setPassword(SHA256Util.generateSha256(input.getPassword()));
    // 찾은 유저 값
    UserDTO user = UserDTO.builder().email("test").password(input.getPassword()).build();
    // input으로 user를 찾는다고 가정
    given(adminMapper.findByEmailAndPassword(input)).willReturn(user);
    // 예외가 발생하지 않는지 검증
    assertDoesNotThrow(() -> adminService.login(input));

  }

  @Test
  void 로그인_실패_테스트(){
    // 로그인폼
    LoginFormDTO input = LoginFormDTO.builder().email("test").password("password").build();
    // 비밀번호암호화
    input.setPassword(SHA256Util.generateSha256(input.getPassword()));
    // input으로 user를 찾는다고 가정
    given(adminMapper.findByEmailAndPassword(input)).willReturn(null);
    // 예외가 발생하지 않는지 검증
        assertThrows(
            LoginFailureException.class,
            () -> {
              adminService.login(input);
            });
  }
}
