package com.ukream.mapper;

import com.ukream.dto.ProductDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface ProductMapper {
  public void createProduct(ProductDTO product);

  public List<ProductDTO> getProducts(RowBounds rowBounds);

  public ProductDTO getProduct(Long productId);

  public int deleteProduct(Long productId);

  public int updateProduct(ProductDTO product);
}
