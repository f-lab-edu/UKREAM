package com.ukream.controller;

import com.ukream.annotation.LoginCheck;
import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.UserDTO;
import com.ukream.service.AdminService;
import com.ukream.util.SessionUtil;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {
  private final AdminService adminService;

  /**
   * 유저 목록 조회
   *
   * <p>어드민 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)와 유저 목록
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> getUsers() {

    List<UserDTO> users = adminService.getUsers();
    return ResponseEntity.ok(users);
  }

  /**
   * 유저 조회
   *
   * 어드민 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)와 유저 정보
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  @GetMapping("/users/{userId}")
  public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {

    UserDTO user = adminService.getUser(userId);
    return ResponseEntity.ok(user);
  }

  /**
   * 어드민 생성
   *
   * 어드민 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  @PostMapping
  public ResponseEntity<Void> createAdmin(@Valid @RequestBody UserDTO user) {
    adminService.createAdmin(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * 로그인
   *
   * @param input 유저 정보
   * @return 로그인이 성공한 경우 HTTP 200 OK 상태코드와 함께 응답
   */
  @PostMapping("/login")
  public ResponseEntity<Void> login(@Valid @RequestBody LoginFormDTO input, HttpSession session) {
    UserDTO user = adminService.login(input);
    SessionUtil.setLoginAdminId(session, user.getUserId());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * 어드민 로그아웃
   *
   * @param session 세션
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @GetMapping("/logout")
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  public void logout(HttpSession session) {
    SessionUtil.logoutAdmin(session);
  }
}
