package com.ukream.es.domain.query;

import com.ukream.es.dto.SearchFilterDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

@Component
public class ProductSearchQuery implements BaseQuery {
  @Override
  public void applyQuery(SearchFilterDTO searchFilter, BoolQueryBuilder boolQueryBuilder) {
    if (shouldSkipQuery(searchFilter)) {
      return;
    }
    String keyword = searchFilter.getKeyword() + "*";
    boolQueryBuilder.should(QueryBuilders.wildcardQuery("korean_name", keyword));
    boolQueryBuilder.should(QueryBuilders.wildcardQuery("english_name", keyword));
    boolQueryBuilder.should(QueryBuilders.termQuery("brand_korean_name", keyword));
    boolQueryBuilder.should(QueryBuilders.termQuery("brand_english_name", keyword));
    boolQueryBuilder.should(QueryBuilders.termQuery("model_number", keyword));
  }

  @Override
  public boolean shouldSkipQuery(SearchFilterDTO searchFilter) {
    return searchFilter.getKeyword() == null || searchFilter.getKeyword().isEmpty();
  }
}
