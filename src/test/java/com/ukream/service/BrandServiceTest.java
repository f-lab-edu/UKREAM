package com.ukream.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.ukream.dto.BrandDTO;
import com.ukream.error.exception.BrandNotFoundException;
import com.ukream.mapper.BrandMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
  @Mock private BrandMapper brandMapper;

  @InjectMocks private BrandService brandService;

  @Test
  void 브랜드_생성_테스트() {
    // given
    BrandDTO brand = BrandDTO.builder().koreanName("테스트").englishName("test").build();

    // when
    // 예외가 발생하지 않는지 검증
    assertDoesNotThrow(() -> brandService.createBrand(brand));

    // then
    // 특정 메서드 호출이 발생했는지 확인
    verify(brandMapper, times(1)).createBrand(brand);
  }

  @Test
  void 브랜드_목록_조회_테스트() {

    BrandDTO brand = BrandDTO.builder().koreanName("테스트").englishName("test").build();
    List<BrandDTO> brands = Arrays.asList(brand);
    given(brandService.getBrands()).willReturn(brands);

    // 예외가 발생하지 않는지 검증
    List<BrandDTO> result = assertDoesNotThrow(() -> brandService.getBrands());

    // 특정 메서드 호출이 발생했는지 확인
    verify(brandMapper).getBrands();

    // 반환된 결과가 예상 결과와 일치하는지 확인
    assertEquals(result, brands);
  }

  @Test
  void 브랜드_조회_성공_테스트() {
    BrandDTO brand = BrandDTO.builder().koreanName("테스트").englishName("test").build();
    Long brandId = 1L;
    given(brandMapper.getBrand(brandId)).willReturn(brand);
    // 예외가 발생하지 않는지 검증
    BrandDTO result = assertDoesNotThrow(() -> brandService.getBrand(brandId));
    // 특정 메서드 호출이 발생했는지 확인
    verify(brandMapper).getBrand(brandId);

    // 반환된 결과가 예상 결과와 일치하는지 확인
    assertEquals(result, brand);
  }

  @Test
  void 브랜드_조회_실패_테스트() {
    Long brandId = 1L;
    given(brandMapper.getBrand(brandId)).willReturn(null);

    // BrandNotFoundException이 발생하는지 검증
    assertThrows(BrandNotFoundException.class, () -> brandService.getBrand(brandId));
    // 특정 메서드 호출이 발생했는지 확인
    verify(brandMapper).getBrand(brandId);
  }

  @Test
  void 브랜드_삭제_성공_테스트() {
    Long brandId = 1L;
    given(brandMapper.deleteBrand(brandId)).willReturn(1);

    // 예외가 발생하지 않는지 검증
    assertDoesNotThrow(() -> brandService.deleteBrand(brandId));
    // 특정 메서드 호출이 발생했는지 확인
    verify(brandMapper).deleteBrand(brandId);
  }

  @Test
  void 브랜드_삭제_실패_테스트() {
    Long brandId = 1L;
    given(brandMapper.deleteBrand(brandId)).willReturn(0);

    // BrandNotFoundException이 발생하는지 검증
    assertThrows(BrandNotFoundException.class, () -> brandService.deleteBrand(brandId));
    // 특정 메서드 호출이 발생했는지 확인
    verify(brandMapper).deleteBrand(brandId);
  }

  @Test
  void 브랜드_수정_성공_테스트() {
    BrandDTO brand = BrandDTO.builder().brandId(1L).koreanName("테스트").englishName("test").build();
    given(brandMapper.updateBrand(brand)).willReturn(1);

    // 예외가 발생하지 않는지 검증
    assertDoesNotThrow(() -> brandService.updateBrand(brand));
    // 특정 메서드 호출이 발생했는지 확인
    verify(brandMapper).updateBrand(brand);
  }

  @Test
  void 브랜드_수정_실패_테스트() {
    BrandDTO brand = BrandDTO.builder().brandId(1L).koreanName("테스트").englishName("test").build();
    given(brandMapper.updateBrand(brand)).willReturn(0);

    // BrandNotFoundException이 발생하는지 검증
    assertThrows(BrandNotFoundException.class, () -> brandService.updateBrand(brand));
    // 특정 메서드 호출이 발생했는지 확인
    verify(brandMapper).updateBrand(brand);
  }
}
