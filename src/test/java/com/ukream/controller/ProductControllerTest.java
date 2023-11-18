package com.ukream.controller;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukream.dto.PageRequestDTO;
import com.ukream.dto.ProductDTO;
import com.ukream.service.BrandService;
import com.ukream.service.ProductService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

  @MockBean private ProductService productService;

  @MockBean private BrandService brandService;

  @Autowired private MockMvc mockMvc;

  @Test
  void 상품_생성_테스트() throws Exception {
    ProductDTO product =
        ProductDTO.builder().brandId(1L).koreanName("테스트").englishName("test").build();

    doNothing().when(brandService).checkBrandExists(anyLong());

    doNothing().when(productService).createProduct(any(ProductDTO.class));

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(product)))
        .andExpect(status().isCreated());

    verify(brandService, times(1)).checkBrandExists(anyLong());
    verify(productService, times(1)).createProduct(any(ProductDTO.class));
  }

  @Test
  void 상품_목록_조회_테스트() throws Exception {
    ProductDTO product =
        ProductDTO.builder().brandId(1L).koreanName("테스트").englishName("test").build();
    List<ProductDTO> products = Arrays.asList(product);
    given(productService.getProducts(any(PageRequestDTO.class))).willReturn(products);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/products"))
        .andExpect(jsonPath("$[0].koreanName").value("테스트"))
        .andExpect(status().isOk());

    verify(productService, times(1)).getProducts(any(PageRequestDTO.class));
  }

  @Test
  void 상품_조회_테스트() throws Exception {
    ProductDTO product =
        ProductDTO.builder().productId(1L).koreanName("테스트").englishName("test").build();

    given(productService.getProduct(product.getProductId())).willReturn(product);

    Long productId = product.getProductId();
    mockMvc
        .perform(MockMvcRequestBuilders.get("/products/{productId}", productId))
        .andExpect(MockMvcResultMatchers.jsonPath("$.koreanName").value("테스트"))
        .andDo(print())
        .andExpect(status().isOk());

    verify(productService, times(1)).getProduct(productId);
  }

  @Test
  void 상품_삭제_테스트() throws Exception {
   
    Long productId = 1L;

    doNothing().when(productService).deleteProduct(productId);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/products/{productId}", productId))
        .andDo(print())
        .andExpect(status().isOk());

    verify(productService, times(1)).deleteProduct(productId);
  }

  @Test
  void 상품_수정_테스트() throws Exception {
    ProductDTO product =
        ProductDTO.builder().koreanName("테스트").englishName("test").build();

    Long productId = 1L;

    product.setProductId(1L);

    doNothing().when(productService).updateProduct(any(ProductDTO.class));

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/products/{productId}",productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(product)))
        .andExpect(status().isOk());

    verify(productService, times(1)).updateProduct(any(ProductDTO.class));
  }
}
