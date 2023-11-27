package com.ukream.controller;

import com.ukream.annotation.LoginCheck;
import com.ukream.dto.AddressDTO;
import com.ukream.dto.LoginFormDTO;
import com.ukream.dto.PaymentInfoDTO;
import com.ukream.dto.UserDTO;
import com.ukream.service.AddressService;
import com.ukream.service.PaymentInfoService;
import com.ukream.service.UserService;
import com.ukream.util.SessionUtil;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final AddressService addressService;
  private final PaymentInfoService paymentInfoService;

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

  /**
   * 로그인
   *
   * @param input 유저 정보
   * @return 로그인이 성공한 경우 HTTP 200 OK 상태코드와 함께 응답
   */
  @PostMapping("/login")
  public ResponseEntity<Void> login(@Valid @RequestBody LoginFormDTO input, HttpSession session) {
    UserDTO user = userService.login(input);
    SessionUtil.setLoginUserId(session, user.getUserId());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * 사용자 로그아웃
   *
   * @param session 사용자 세션
   */
  @GetMapping("/logout")
  @LoginCheck(type = LoginCheck.UserType.USER)
  public void logout(HttpSession session) {
    SessionUtil.logoutUser(session);
  }

  /**
   * 사용자 정보 조회
   *
   * <p>사용자 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @param session 사용자 세션
   */
  @GetMapping
  @LoginCheck(type = LoginCheck.UserType.USER)
  public ResponseEntity<UserDTO> getUserInfo(HttpSession session) {
    Long userId = SessionUtil.getLoginUserId(session);
    UserDTO userInfo = userService.getUserInfo(userId);
    return ResponseEntity.ok(userInfo);
  }

  /**
   * 주소 생성
   *
   * <p>사용자 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @param session 사용자 세션
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @PostMapping("/addresses")
  @LoginCheck(type = LoginCheck.UserType.USER)
  public ResponseEntity<Void> createAddress(
      @Valid @RequestBody AddressDTO address, HttpSession session) {
    Long userId = SessionUtil.getLoginUserId(session);
    userService.checkUserExists(userId);
    addressService.createAddress(address);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * 주소 목록 조회
   *
   * <p>사용자 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)와 주소 목록
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @GetMapping("/addresses")
  @LoginCheck(type = LoginCheck.UserType.USER)
  public ResponseEntity<List<AddressDTO>> getAddresses(HttpSession session) {
    Long userId = SessionUtil.getLoginUserId(session);
    userService.checkUserExists(userId);
    List<AddressDTO> addresses = addressService.getAddresses(userId);
    return ResponseEntity.ok(addresses);
  }

  /**
   * 주소 조회
   *
   * <p>사용자 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)와 주소 정보
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @GetMapping("/addresses/{addressId}")
  @LoginCheck(type = LoginCheck.UserType.USER)
  public ResponseEntity<AddressDTO> getAddress(@PathVariable Long addressId, HttpSession session) {
    Long userId = SessionUtil.getLoginUserId(session);
    userService.checkUserExists(userId);
    AddressDTO address = addressService.getAddress(addressId, userId);
    return ResponseEntity.ok(address);
  }

  /**
   * 주소 삭제
   *
   * <p>사용자 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.USER)
  @DeleteMapping("/addresses/{addressId}")
  public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
    addressService.deleteAddress(addressId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * 주소 수정
   *
   * <p>사용자 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.USER)
  @PutMapping("addresses/{addressId}")
  ResponseEntity<Void> updateAddress(@PathVariable Long addressId, @Valid @RequestBody AddressDTO address) {
    addressService.updateAddress(address);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * 결제 정보 생성
   *
   * <p>사용자 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @param session 사용자 세션
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @PostMapping("/payment-infos")
  @LoginCheck(type = LoginCheck.UserType.USER)
  public ResponseEntity<Void> createPaymentInfo(
      @Valid @RequestBody PaymentInfoDTO paymentInfo, HttpSession session) {
    Long userId = SessionUtil.getLoginUserId(session);
    userService.checkUserExists(userId);
    paymentInfoService.createPaymentInfo(paymentInfo);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * 결제 정보 목록 조회
   *
   * <p>사용자 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)와 주소 목록
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @GetMapping("/payment-infos")
  @LoginCheck(type = LoginCheck.UserType.USER)
  public ResponseEntity<List<PaymentInfoDTO>> getPaymentInfos(HttpSession session) {
    Long userId = SessionUtil.getLoginUserId(session);
    userService.checkUserExists(userId);
    List<PaymentInfoDTO> paymentInfos = paymentInfoService.getPaymentInfos(userId);
    return ResponseEntity.ok(paymentInfos);
  }

  /**
   * 결제 정보 조회
   *
   * <p>사용자 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)와 주소 정보
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @GetMapping("/payment-infos/{paymentInfoId}")
  @LoginCheck(type = LoginCheck.UserType.USER)
  public ResponseEntity<PaymentInfoDTO> getPaymentInfo(
      @PathVariable Long paymentInfoId, HttpSession session) {
    Long userId = SessionUtil.getLoginUserId(session);
    userService.checkUserExists(userId);
    PaymentInfoDTO paymentInfo = paymentInfoService.getPaymentInfo(paymentInfoId, userId);
    return ResponseEntity.ok(paymentInfo);
  }

  /**
   * 결제 정보 삭제
   *
   * <p>사용자 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.USER)
  @DeleteMapping("/payment-infos/{paymentInfoId}")
  public ResponseEntity<Void> deletePaymentInfo(@PathVariable Long paymentInfoId) {
    paymentInfoService.deletePaymentInfo(paymentInfoId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * 결제 정보 수정
   *
   * <p>사용자 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.USER)
  @PutMapping("payment-infos/{paymentInfoId}")
  ResponseEntity<Void> updatePaymentInfo(
      @PathVariable Long paymentInfoId, @Valid @RequestBody PaymentInfoDTO paymentInfo) {
    paymentInfoService.updatePaymentInfo(paymentInfo);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}