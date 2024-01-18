package com.ukream.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class UserDTO {
  private final Long userId;

  @NotNull(message = "이메일은 필수입니다.")
  private final String email;

  @Setter
  @NotNull(message = "비밀번호는 필수입니다.")
  private String password;

  @Setter
  private String userLevel;
  private final LocalDateTime createDate;
  private final LocalDateTime modifyDate;
}
