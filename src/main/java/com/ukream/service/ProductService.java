package com.ukream.service;

import com.ukream.dto.PageRequestDTO;
import com.ukream.dto.ProductDTO;
import com.ukream.error.exception.DuplicatedModelNumberException;
import com.ukream.error.exception.ProductNotFoundException;
import com.ukream.mapper.ProductMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.apache.ibatis.session.RowBounds;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductMapper productMapper;

  public void createProduct(ProductDTO product) {
    try {
      productMapper.createProduct(product);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicatedModelNumberException();
    }
  }

  public List<ProductDTO> getProducts(PageRequestDTO pageRequestDTO) {
    RowBounds rowBounds = pageRequestDTO.getRowBounds();
    return productMapper.getProducts(rowBounds);
  }

  public ProductDTO getProduct(Long productId) {
    ProductDTO product = productMapper.getProduct(productId);
    if (product == null) {
      throw new ProductNotFoundException();
    }
    return product;
  }

  public void deleteProduct(Long productId) {
    int deletedRows = productMapper.deleteProduct(productId);
    if (deletedRows == 0) {
      throw new ProductNotFoundException();
    }
  }

  public void updateProduct(ProductDTO product) {
    int updatedRows = productMapper.updateProduct(product);
    if (updatedRows == 0) {
      throw new ProductNotFoundException();
    }
  }
}
