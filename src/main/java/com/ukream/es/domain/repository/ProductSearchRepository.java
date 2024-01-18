package com.ukream.es.domain.repository;

import com.ukream.es.domain.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSearchRepository
    extends ElasticsearchRepository<Product, Long>, BaseRepository<Product> {}
