package com.ukream.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    @NotNull(message = "이메일은 필수입니다.")
    private String email;
    @NotNull(message = "비밀번호는 필수입니다.")
    private String password;
    private String userLevel;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
