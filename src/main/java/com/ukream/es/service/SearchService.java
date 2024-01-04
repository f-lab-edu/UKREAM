package com.ukream.es.service;

import com.ukream.es.domain.model.Product;
// import com.ukream.es.domain.ProductRepository;
import com.ukream.es.domain.repository.ProductSearchRepository;
import com.ukream.es.dto.SearchFilterDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SearchService {

  private final ProductSearchRepository productSearchRepository;

  public List<Product> search(SearchFilterDTO searchFilter, Pageable pageable) {
    return productSearchRepository.search(searchFilter, pageable);
  }
}
