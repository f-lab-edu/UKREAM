package com.ukream.es.domain.query;

import com.ukream.es.dto.SearchFilterDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface BaseQuery {
  void applyQuery(SearchFilterDTO searchFilter, BoolQueryBuilder boolQueryBuilder);

  boolean shouldSkipQuery(SearchFilterDTO searchFilter);
}
