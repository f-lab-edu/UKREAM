package com.ukream.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Builder
@AllArgsConstructor
public class LoginFormDTO {
  @NotNull(message = "이메일은 필수입니다.")
  private final String email;

  @Setter
  @NotNull(message = "비밀번호는 필수입니다.")
  private String password;
}
