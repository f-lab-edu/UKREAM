package com.ukream.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.ukream.dto.PageRequestDTO;
import com.ukream.dto.ProductDTO;
import com.ukream.error.exception.DuplicatedModelNumberException;
import com.ukream.error.exception.ProductNotFoundException;
import com.ukream.mapper.ProductMapper;
import java.util.Arrays;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
  @Mock private ProductMapper productMapper;

  @InjectMocks private ProductService productService;

  @Test
  void 상품_생성_테스트() {

    ProductDTO product = ProductDTO.builder().koreanName("테스트").englishName("test").build();

    doNothing().when(productMapper).createProduct(any(ProductDTO.class));

    assertDoesNotThrow(() -> productService.createProduct(product));

    verify(productMapper, times(1)).createProduct(product);
  }

  @Test
  void 상품_생성_실패_테스트() {

    ProductDTO product = ProductDTO.builder().koreanName("테스트").englishName("test").build();

    doThrow(DataIntegrityViolationException.class)
        .when(productMapper)
        .createProduct(any(ProductDTO.class));

    assertThrows(DuplicatedModelNumberException.class, () -> productService.createProduct(product));

    verify(productMapper, times(1)).createProduct(product);
  }

  @Test
  void 상품_목록_조회_테스트() {
    PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
    ProductDTO product =
        ProductDTO.builder().brandId(1L).koreanName("테스트").englishName("test").build();
    List<ProductDTO> products = Arrays.asList(product);

    given(productMapper.getProducts(any(RowBounds.class))).willReturn(products);

    List<ProductDTO> result = assertDoesNotThrow(() -> productService.getProducts(pageRequestDTO));

    assertEquals(result, products);
  }

  @Test
  void 상품_조회_성공_테스트() {
    ProductDTO product =
        ProductDTO.builder().brandId(1L).koreanName("테스트").englishName("test").build();
    Long productId = 1L;

    given(productMapper.getProduct(productId)).willReturn(product);

    ProductDTO result = assertDoesNotThrow(() -> productService.getProduct(productId));

    assertEquals(result, product);
  }

  @Test
  void 상품_조회_실패_테스트() {

    Long productId = 1L;

    given(productMapper.getProduct(productId)).willReturn(null);

    assertThrows(ProductNotFoundException.class, () -> productService.getProduct(productId));
  }

  @Test
  void 상품_삭제_성공_테스트() {
    Long productId = 1L;
    
    given(productMapper.deleteProduct(productId)).willReturn(1);

    assertDoesNotThrow(() -> productService.deleteProduct(productId));

  }

  @Test
  void 상품_삭제_실패_테스트() {
    Long productId = 1L;
    
    given(productMapper.deleteProduct(productId)).willReturn(0);

    assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(productId));

  }

  @Test
  void 상품_수정_성공_테스트() {
    ProductDTO product =
    ProductDTO.builder().productId(1L).brandId(1L).koreanName("테스트").englishName("test").build();
    
    given(productMapper.updateProduct(product)).willReturn(1);

    assertDoesNotThrow(() -> productService.updateProduct(product));
  }

  @Test
  void 상품_수정_실패_테스트() {
    ProductDTO product =
    ProductDTO.builder().productId(1L).brandId(1L).koreanName("테스트").englishName("test").build();
    
    given(productMapper.updateProduct(product)).willReturn(0);

    assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(product));
  }


}
