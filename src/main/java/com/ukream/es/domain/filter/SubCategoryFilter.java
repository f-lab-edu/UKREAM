package com.ukream.es.domain.filter;

import com.ukream.es.dto.SearchFilterDTO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryFilter implements BaseFilter {

  @Override
  public void applyFilter(SearchFilterDTO searchFilter, BoolQueryBuilder boolQueryBuilder) {
    if (shouldSkipFilter(searchFilter)) {
      return;
    }

    String[] categorys = searchFilter.getSubCategory();
    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

    for (String category : categorys) {
      queryBuilder.should(QueryBuilders.termQuery("sub_category.keyword", category));
    }

    boolQueryBuilder.filter(queryBuilder);
  }

  @Override
  public boolean shouldSkipFilter(SearchFilterDTO searchFilter) {
    return searchFilter == null
        || searchFilter.getSubCategory() == null
        || searchFilter.getSubCategory().length == 0;
  }
}
