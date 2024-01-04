package com.ukream.es.domain.filter;

import com.ukream.es.dto.SearchFilterDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

@Component
public class PriceFilter implements BaseFilter {

  @Override
  public void applyFilter(SearchFilterDTO searchFilter, BoolQueryBuilder boolQueryBuilder) {
    if (shouldSkipFilter(searchFilter)) {
      return;
    }

    int minPrice = searchFilter.getMinPrice();
    int maxPrice = searchFilter.getMaxPrice();

    boolQueryBuilder.filter(QueryBuilders.rangeQuery("release_price").gte(minPrice).lte(maxPrice));
  }

  @Override
  public boolean shouldSkipFilter(SearchFilterDTO searchFilter) {
    return searchFilter == null
        || searchFilter.getMinPrice() == null
        || searchFilter.getMaxPrice() == null;
  }
}
