package com.ukream.es.domain.repository;

import com.ukream.es.domain.filter.*;
import com.ukream.es.domain.model.Product;
import com.ukream.es.domain.query.ProductSearchQuery;
import com.ukream.es.dto.SearchFilterDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductSearchRepositoryImpl implements BaseRepository<Product> {

  private final ElasticsearchOperations elasticsearchOperations;
  private final ProductSearchQuery productSearchQuery;
  private final List<BaseFilter> filters;

  @Override
  public List<Product> search(SearchFilterDTO searchFilter, Pageable pageable) {
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    buildQuery(searchFilter, boolQueryBuilder);

    NativeSearchQuery searchQuery = new NativeSearchQuery(boolQueryBuilder).setPageable(pageable);

    SearchHits<Product> searchHits = elasticsearchOperations.search(searchQuery, Product.class);

    return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
  }

  @Override
  public void buildQuery(SearchFilterDTO searchFilter, BoolQueryBuilder boolQueryBuilder) {
    productSearchQuery.applyQuery(searchFilter, boolQueryBuilder);
    buildFilter();
    for (BaseFilter filter : filters) {
      filter.applyFilter(searchFilter, boolQueryBuilder);
    }
  }

  @Override
  public void buildFilter() {
    filters.add(new SizeFilter());
    filters.add(new PriceFilter());
    filters.add(new MainCategoryFilter());
    filters.add(new SubCategoryFilter());
  }
}
