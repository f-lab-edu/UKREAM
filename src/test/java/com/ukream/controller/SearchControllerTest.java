package com.ukream.controller;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukream.es.controller.SearchController;
import com.ukream.es.domain.model.Product;
import com.ukream.es.dto.SearchFilterDTO;
import com.ukream.es.service.SearchService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(SearchController.class)
public class SearchControllerTest {

  @MockBean private SearchService searchService;

  @Autowired private MockMvc mockMvc;

  @Test
  void 검색_테스트() throws Exception {
    SearchFilterDTO searchFilter = SearchFilterDTO.builder().keyword("테스트").build();
    Product product = Product.builder().brandId(1L).koreanName("테스트").englishName("test").build();
    List<Product> products = Arrays.asList(product);
    given(searchService.search(any(SearchFilterDTO.class), any(Pageable.class)))
        .willReturn(products);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(searchFilter)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].koreanName").value("테스트"))
        .andExpect(jsonPath("$[0].englishName").value("test"));

    verify(searchService, times(1)).search(any(SearchFilterDTO.class), any(Pageable.class));
  }
}
