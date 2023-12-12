package com.ukream.es.domain.repository;

import com.ukream.es.dto.SearchFilterDTO;
import java.util.List;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Pageable;

public interface BaseRepository<T> {
  List<T> search(SearchFilterDTO searchFilter, Pageable pageable);

  void buildQuery(SearchFilterDTO searchFilter, BoolQueryBuilder boolQueryBuilder);

  void buildFilter();
}
