package com.ukream.es.domain.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "products")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Field(name = "brand_id")
  private Long brandId;

  @Field(name = "create_date")
  private LocalDateTime createDate;

  @Field(name = "english_name")
  private String englishName;

  @Field(name = "gender")
  private String gender;

  @Field(name = "image_path")
  private String imagePath;

  @Field(name = "is_deleted")
  private boolean isDeleted;

  @Field(name = "korean_name")
  private String koreanName;

  @Field(name = "main_category")
  private String mainCategory;

  @Field(name = "main_color")
  private String mainColor;

  @Field(name = "model_number")
  private String modelNumber;

  @Field(name = "modify_date")
  private LocalDateTime modifyDate;

  @Field(name = "release_date")
  private LocalDateTime releaseDate;

  @Field(name = "release_price")
  private Integer releasePrice;

  @Field(name = "size")
  private String size;

  @Field(name = "sub_category")
  private String subCategory;

  @Transient
  @Field(name = "brand_korean_name")
  private String brandKoreanName;

  @Transient
  @Field(name = "brand_english_name")
  private String brandEnglishName;
}
