package com.ukream.service;

import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.UserDTO;
import com.ukream.error.exception.DuplicatedEmailException;
import com.ukream.error.exception.LoginFailureException;
import com.ukream.mapper.UserMapper;
import com.ukream.util.SHA256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
    user.setPassword(SHA256Util.generateSha256(user.getPassword()));
    try {
      userMapper.createUser(user);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicatedEmailException("중복된 이메일 입니다.");
    }
  }

  public UserDTO login(LoginFormDTO input) {
    input.setPassword(SHA256Util.generateSha256(input.getPassword()));

    UserDTO user = userMapper.findByEmailAndPassword(input);

    if (user == null) {
      throw new LoginFailureException("유효하지 않은 이메일 또는 비밀번호입니다.");
    }

    return user;
  }
}
