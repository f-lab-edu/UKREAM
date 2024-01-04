package com.ukream.es.controller;

import com.ukream.es.domain.model.Product;
import com.ukream.es.dto.SearchFilterDTO;
import com.ukream.es.service.SearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/search")
@RestController
public class SearchController {

  private final SearchService searchService;

  @GetMapping
  public ResponseEntity<List<Product>> search(
      @RequestBody SearchFilterDTO searchFilter, Pageable pageable) {
    List<Product> product = searchService.search(searchFilter, pageable);
    return ResponseEntity.ok(product);
  }
}
