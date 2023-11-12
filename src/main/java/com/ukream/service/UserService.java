package com.ukream.service;

import com.ukream.dto.UserDTO;
import com.ukream.error.exception.DuplicatedEmailException;
import com.ukream.error.exception.LoginFailureException;
import com.ukream.mapper.UserMapper;
import com.ukream.util.SHA256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;

  /**
   * 회원 가입
   *
   * @param userDTO 회원정보
   * @throws DuplicatedEmailException 이메일이 이미 존재할 경우 발생합니다.
   */
  public void createUser(UserDTO user) {
    isDuplicatedEmail(user.getEmail());
    user.setPassword(SHA256Util.generateSha256(user.getPassword()));
    userMapper.createUser(user);
  }

  /**
   * 이메일 중복 체크
   *
   * @param email 확인할 이메일 문자열
   * @return 이메일이 이미 등록되어 있으면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
   */
  public boolean isDuplicatedEmail(String email) {
    return userMapper.checkDuplicatedEmail(email) == 1;
  }
  
}
