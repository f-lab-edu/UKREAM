package com.ukream.controller;

import com.ukream.annotation.LoginCheck;
import com.ukream.dto.UserDTO;
import com.ukream.service.UserService;
import com.ukream.util.SessionUtil;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /**
   * 회원 가입
   *
   * @param user 유저 정보
   * @return 회원 가입이 성공한 경우 HTTP 201 Created 상태코드와 함께 응답
   */
  @PostMapping
  public ResponseEntity<Void> signUp(@Valid @RequestBody UserDTO user) {
    userService.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
