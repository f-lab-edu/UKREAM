package com.ukream.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class AddressDTO {
  private Long addressId;

  @NotNull(message = "이름은 필수입니다.")
  @Setter
  private String name;

  @NotNull(message = "주소는 필수입니다.")
  @Setter
  private String address;

  @Pattern(regexp = "^\\d{5}$", message = "올바른 우편번호 형식이 아닙니다.")
  @Setter
  private String zipcode;

  @Setter private String detailedAddress;
  @Setter private String defaultAddressYn;
  @Setter private Long userId;
  private final LocalDateTime createDate;
  private final LocalDateTime modifyDate;
  @Setter private boolean isDeleted;
}
