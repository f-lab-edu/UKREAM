package com.ukream.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.UserDTO;
import com.ukream.error.exception.DuplicatedEmailException;
import com.ukream.error.exception.LoginFailureException;
import com.ukream.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock private UserMapper userMapper;

  @InjectMocks private UserService userService;

  @Test
  void 회원가입_테스트() {

    UserDTO user = UserDTO.builder().email("test").password("password").build();
    given(userMapper.checkDuplicatedEmail(user.getEmail())).willReturn(0);
    // 예외가 발생하지 않는지 검증
    assertDoesNotThrow(() -> userService.createUser(user));
  }

  @Test
  void 중복된_이메일_회원가입_테스트() {

    UserDTO user = UserDTO.builder().email("test").password("password").build();

    given(userMapper.checkDuplicatedEmail(user.getEmail())).willReturn(1);

    // 중복된 이메일이 발생할 경우 DuplicatedEmailException이 발생하는지 검증
    DuplicatedEmailException exception =
        assertThrows(
            DuplicatedEmailException.class,
            () -> {
              userService.createUser(user);
            });

    System.out.println(exception);
  }

  @Test
  void 로그인_성공_테스트() {
    // given
    LoginFormDTO input = LoginFormDTO.builder().email("test").password("password").build();
    UserDTO user = UserDTO.builder().email("test").password("password").build();

    given(userMapper.findByEmailAndPassword(input)).willReturn(user);
    // when
    assertDoesNotThrow(() -> userService.login(input));
  }

  @Test
  void 로그인_실패_테스트() {

    LoginFormDTO input = LoginFormDTO.builder().email("test").password("password").build();

    // 사용자가 주어진 이메일과 비밀번호로 검색되지 않을 것으로 가정
    given(userMapper.findByEmailAndPassword(input)).willReturn(null);

    // 반환값이 null일경우 LoginFailureException 발생하는지 검증
    LoginFailureException exception =
        assertThrows(
            LoginFailureException.class,
            () -> {
              userService.login(input);
            });

    System.out.println(exception);
  }
}
