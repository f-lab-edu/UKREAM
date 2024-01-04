package com.ukream.es.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SearchFilterDTO {
  private String gender;
  private Long[] brandId;
  private String[] size;
  private Integer minPrice;
  private Integer maxPrice;
  private String[] mainCategory;
  private String[] subCategory;
  private String keyword;
}
