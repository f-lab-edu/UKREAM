package com.ukream.es.domain.filter;

import com.ukream.es.dto.SearchFilterDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface BaseFilter {
  void applyFilter(SearchFilterDTO searchFilter, BoolQueryBuilder boolQueryBuilder);

  boolean shouldSkipFilter(SearchFilterDTO searchFilter);
}
