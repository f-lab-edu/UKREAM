package com.ukream.service;

import com.ukream.dto.BrandDTO;
import com.ukream.error.exception.BrandNotFoundException;
import com.ukream.error.exception.DuplicatedBrandNameException;
import com.ukream.mapper.BrandMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

@Service
@RequiredArgsConstructor
public class BrandService {
  private final BrandMapper brandMapper;

  public void createBrand(BrandDTO brand) {
    try {
      brandMapper.createBrand(brand);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicatedBrandNameException();
    }
  }

  public List<BrandDTO> getBrands() {
    return brandMapper.getBrands();
  }

  public BrandDTO getBrand(Long brandId) {
    BrandDTO brand = brandMapper.getBrand(brandId);
    if (brand == null) {
      throw new BrandNotFoundException();
    }
    return brand;
  }

  public void deleteBrand(Long brandId) {
    int deletedRows = brandMapper.deleteBrand(brandId);
    if (deletedRows == 0) {
      throw new BrandNotFoundException();
    }
  }

  public void updateBrand(BrandDTO brand) {
    int updatedRows = brandMapper.updateBrand(brand);
    if (updatedRows == 0) {
      throw new BrandNotFoundException();
    }
  }

  public void checkBrandExists(Long brandId) {
    BrandDTO brand = brandMapper.getBrand(brandId);
    if (brand == null) {
      throw new BrandNotFoundException();
    }
  }
}
