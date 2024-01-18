package com.ukream.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class BrandDTO {
  @Setter
  private Long brandId;

  @NotNull(message = "브랜드 영문명은 필수입니다.")
  private final String englishName;

  @NotNull(message = "브랜드 한글명은 필수입니다.")
  private final String koreanName;

  @Setter private String imagePath;

  @Setter private boolean isDeleted;
  private final LocalDateTime createDate;
  private final LocalDateTime modifyDate;
}
