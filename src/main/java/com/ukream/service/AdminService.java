package com.ukream.service;

import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.UserDTO;
import com.ukream.error.exception.DuplicatedEmailException;
import com.ukream.error.exception.LoginFailureException;
import com.ukream.error.exception.UserNotFoundException;
import com.ukream.mapper.AdminMapper;
import com.ukream.mapper.UserMapper;
import com.ukream.util.SHA256Util;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
  private final AdminMapper adminMapper;
  private final UserMapper userMapper;

  public List<UserDTO> getUsers() {
    return adminMapper.getUsers();
  }

  public UserDTO getUser(Long userId) {
    UserDTO user = adminMapper.getUser(userId);
    if (user == null) {
      throw new UserNotFoundException("해당 유저를 찾을 수 없습니다.");
    }
    return user;
  }

  public void createAdmin(UserDTO user) {
    user.setPassword(SHA256Util.generateSha256(user.getPassword()));
    try {
    adminMapper.createAdmin(user);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicatedEmailException("중복된 이메일 입니다.");
  }
  }

  public UserDTO login(LoginFormDTO input) {
    input.setPassword(SHA256Util.generateSha256(input.getPassword()));

    UserDTO user = adminMapper.findByEmailAndPassword(input);

    if (user == null) {
      throw new LoginFailureException("유효하지 않은 이메일 또는 비밀번호입니다.");
    }

    return user;
  }
}
