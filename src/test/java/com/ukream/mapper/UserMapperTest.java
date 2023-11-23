package com.ukream.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.UserDTO;
import com.ukream.util.SHA256Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

  @Autowired private UserMapper userMapper;

  @Test
  public void 중복된_이메일_체크_테스트() {
    String email = "test";
    int result = userMapper.checkDuplicatedEmail(email);
    // 중복된 이메일이면 1을 반환하는지 검증
    assertEquals(1, result);
  }

  @Test
  public void 이메일_체크_테스트() {
    String email = "test55";
    int result = userMapper.checkDuplicatedEmail(email);
    // 0을 반환하는지 검증
    assertEquals(0, result);
  }

  @Test
  public void 유저_생성_테스트() {
    UserDTO user = UserDTO.builder().email("test52").password("1234").build();
    userMapper.createUser(user);

    int result = userMapper.checkDuplicatedEmail(user.getEmail());
    // 생성되었는지 검증
    assertEquals(1, result);
  }

  @Test
  public void 로그인_테스트() {
    LoginFormDTO input =
        LoginFormDTO.builder().email("test3").password(SHA256Util.generateSha256("1234")).build();
    UserDTO user = userMapper.findByEmailAndPassword(input);
    // null인지 검증
    assertNotNull(user);
  }
}
