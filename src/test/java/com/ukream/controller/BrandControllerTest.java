package com.ukream.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukream.dto.BrandDTO;
import com.ukream.service.BrandService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(BrandController.class)
public class BrandControllerTest {

  @MockBean private BrandService brandService;

  @Autowired private MockMvc mockMvc;

  @Test
  void 브랜드_생성_테스트() throws Exception {
    BrandDTO brand = BrandDTO.builder().koreanName("테스트").englishName("test").build();

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(brand)))
        .andExpect(status().isCreated())
        .andDo(print());
  }

  @Test
  void 브랜드_목록_조회_테스트() throws Exception {
    BrandDTO brand = BrandDTO.builder().koreanName("테스트").englishName("test").build();
    List<BrandDTO> brands = Arrays.asList(brand);

    given(brandService.getBrands()).willReturn(brands);

    mockMvc
        .perform(get("/brands"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].koreanName").value("테스트"))
        .andDo(print());

    verify(brandService, times(1)).getBrands();
  }

  @Test
  void 브랜드_조회_테스트() throws Exception {
    BrandDTO brand = BrandDTO.builder().brandId(1L).koreanName("테스트").englishName("test").build();

    given(brandService.getBrand(brand.getBrandId())).willReturn(brand);

    mockMvc
        .perform(get("/brands/" + brand.getBrandId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.koreanName").value("테스트"))
        .andDo(print());

    verify(brandService, times(1)).getBrand(brand.getBrandId());
  }

  @Test
  void 브랜드_삭제_테스트() throws Exception {
    Long brandId = 1L;
    doNothing().when(brandService).deleteBrand(brandId);
    mockMvc
        .perform(delete("/brands/{brandId}", brandId))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void 브랜드_수정_테스트() throws Exception {
    BrandDTO brand = BrandDTO.builder().koreanName("테스트").englishName("test").build();
    Long brandId = 1L;
    brand.setBrandId(brandId);
    doNothing().when(brandService).updateBrand(brand);
   
    mockMvc.perform(MockMvcRequestBuilders.put("/brands/{brandId}", brandId)
    .contentType(MediaType.APPLICATION_JSON)
    .content(new ObjectMapper().writeValueAsString(brand))) 
    .andExpect(status().isOk());
  }
}
