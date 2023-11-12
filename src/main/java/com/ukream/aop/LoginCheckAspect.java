package com.ukream.aop;

import com.ukream.annotation.LoginCheck;
import com.ukream.error.exception.LoginRequiredException;
import com.ukream.util.SessionUtil;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 로그인 여부를 체크하는 Aspect 클래스. LoginCheck 어노테이션이 부여된 메서드 실행 전에 로그인 여부를 확인하고, 로그인이 되어 있지 않으면
 * LoginRequiredException 발생시켜 로그인이 필요한 상태임을 알린다.
 */
@Aspect
@Component
public class LoginCheckAspect {

  /**
   * LoginCheck 어노테이션이 부여된 메서드 실행 전에 로그인 여부를 확인하는 어드바이스 메서드.
   *
   * @param loginCheck LoginCheck 어노테이션 객체
   * @throws Throwable 예외 처리
   */
  @Before("@annotation(com.ukream.annotation.LoginCheck) && @annotation(loginCheck)")
  public void loginCheck(LoginCheck loginCheck) throws Throwable {
    // 현재 요청의 HttpSession을 획득
    HttpSession session =
        ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes()))
            .getRequest()
            .getSession();

    Long id = 0L;
    String userType = loginCheck.type().toString();

    // LoginCheck 어노테이션에서 지정한 유저 타입에 따라 세션에서 사용자 ID 추출
    switch (userType) {
      case "ADMIN":
        {
          id = SessionUtil.getLoginAdminId(session);
          break;
        }
      case "USER":
        {
          id = SessionUtil.getLoginUserId(session);
          break;
        }
    }

    // ID가 0이면 로그인이 되어 있지 않은 상태이므로 예외 발생
    if (id == 0L) {
      throw new LoginRequiredException("로그인을 해주세요.");
    }
  }
}
