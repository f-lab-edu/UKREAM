package com.ukream.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

import com.ukream.es.domain.model.Product;
import com.ukream.es.domain.repository.ProductSearchRepository;
import com.ukream.es.dto.SearchFilterDTO;
import com.ukream.es.service.SearchService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
  @Mock private ProductSearchRepository productSearchRepository;
  ;

  @InjectMocks private SearchService searchService;

  @Test
  void 검색_테스트() {
    SearchFilterDTO searchFilter = SearchFilterDTO.builder().keyword("테스트").build();
    Product product = Product.builder().brandId(1L).koreanName("테스트").englishName("test").build();
    List<Product> products = Arrays.asList(product);

    given(productSearchRepository.search(eq(searchFilter), any(Pageable.class)))
        .willReturn(products);

    List<Product> result =
        assertDoesNotThrow(() -> searchService.search(searchFilter, PageRequest.of(0, 10)));

    assertEquals(result, products);
  }
}
