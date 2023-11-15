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
public class ProductDTO {
  @Setter private Long productId;
  @Setter private Long brandId;
  private LocalDateTime releaseDate;
  private String modelNumber;
  private String mainCategory;
  private String subCategory;
  private Integer releasePrice;
  private String size;
  private LocalDateTime createDate;
  private LocalDateTime modifyDate;

  @NotNull(message = "상품 영문명은 필수입니다.")
  private String englishName;

  @NotNull(message = "상품 한글명은 필수입니다.")
  private String koreanName;

  private String imagePath;
  private String gender;
  @Setter private boolean isDeleted;
}
