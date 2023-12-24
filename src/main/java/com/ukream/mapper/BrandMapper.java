package com.ukream.mapper;

import com.ukream.dto.BrandDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandMapper {

  public void createBrand(BrandDTO brand);

  public List<BrandDTO> getBrands();

  public BrandDTO getBrand(Long brandId);

  public int deleteBrand(Long brandId);

  public int updateBrand(BrandDTO brand);
}