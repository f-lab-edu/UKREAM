package com.ukream.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LoginCheck 어노테이션은 메소드에 적용되어 로그인 상태를 체크하는 데 사용됩니다. 어노테이션의 속성을 통해 사용자의 유형을 지정할 수
 * 있습니다. @Retention(RetentionPolicy.RUNTIME): 어노테이션이 런타임 시에도 유지되도록
 * 지정합니다. @Target(ElementType.METHOD): 어노테이션이 메소드에 적용될 수 있도록 지정합니다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginCheck {

  /**
   * 사용자의 유형을 지정하는 열거형(UserType) 속성입니다.
   *
   * @return UserType 열거형 상수 중 하나를 반환합니다.
   */
  UserType type();

  /** UserType 열거형은 사용자의 유형을 정의합니다. USER: 일반 사용자 ADMIN: 관리자 */
  public static enum UserType {
    USER,
    ADMIN
  }
}
