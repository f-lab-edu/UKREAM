package com.ukream.es.domain.filter;

import com.ukream.es.dto.SearchFilterDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

@Component
public class MainCategoryFilter implements BaseFilter {

  @Override
  public void applyFilter(SearchFilterDTO searchFilter, BoolQueryBuilder boolQueryBuilder) {
    if (shouldSkipFilter(searchFilter)) {
      return;
    }

    String[] categorys = searchFilter.getMainCategory();
    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

    for (String category : categorys) {
      queryBuilder.should(QueryBuilders.termQuery("main_category.keyword", category));
    }

    boolQueryBuilder.filter(queryBuilder);
  }

  @Override
  public boolean shouldSkipFilter(SearchFilterDTO searchFilter) {
    return searchFilter == null
        || searchFilter.getMainCategory() == null
        || searchFilter.getMainCategory().length == 0;
  }
}
