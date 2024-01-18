package com.ukream.es.domain.filter;

import com.ukream.es.dto.SearchFilterDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

@Component
public class SizeFilter implements BaseFilter {

  @Override
  public void applyFilter(SearchFilterDTO searchFilter, BoolQueryBuilder boolQueryBuilder) {
    if (shouldSkipFilter(searchFilter)) {
      return;
    }

    String[] sizes = searchFilter.getSize();
    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

    for (String size : sizes) {
      queryBuilder.should(QueryBuilders.termQuery("size.keyword", size));
    }

    boolQueryBuilder.filter(queryBuilder);
  }

  @Override
  public boolean shouldSkipFilter(SearchFilterDTO searchFilter) {
    return searchFilter == null
        || searchFilter.getSize() == null
        || searchFilter.getSize().length == 0;
  }
}
