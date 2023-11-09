package com.ukream.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.ukream.dto.UserDTO;
import com.ukream.error.exception.DuplicatedEmailException;
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
    given(userMapper.emailCheck(user.getEmail())).willReturn(0);
    // 예외가 발생하지 않는지 검증
    assertDoesNotThrow(() -> userService.createUser(user));
  }

  @Test
  void 중복된_이메일_회원가입_테스트() {

    UserDTO user = UserDTO.builder().email("test").password("password").build();

    given(userMapper.emailCheck(user.getEmail())).willReturn(1);

    // 중복된 이메일이 발생할 경우 DuplicatedEmailException이 발생하는지 검증
    DuplicatedEmailException exception =
        assertThrows(
            DuplicatedEmailException.class,
            () -> {
              userService.createUser(user);
            });

    System.out.println(exception);
  }

}
