package com.ukream.controller;

import com.ukream.annotation.LoginCheck;
import com.ukream.dto.BrandDTO;
import com.ukream.service.BrandService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {
  private final BrandService brandService;

  /**
   * 브랜드 생성
   *
   * 어드민 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 201 (CREATED)
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  @PostMapping
  public ResponseEntity<Void> createBrand(@Valid @RequestBody BrandDTO brand) {
    brandService.createBrand(brand);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * 브랜드 목록 조회
   *
   * @return HTTP 상태 코드 200 (OK)와 브랜드 목록
   */
  @GetMapping
  public ResponseEntity<List<BrandDTO>> getBrands() {
    List<BrandDTO> brands = brandService.getBrands();
    return ResponseEntity.ok(brands);
  }

  /**
   * 브랜드 조회
   *
   * @return HTTP 상태 코드 200 (OK)와 브랜드 정보
   */
  @GetMapping("/{brandId}")
  public ResponseEntity<BrandDTO> getBrand(@PathVariable Long brandId) {
    BrandDTO brand = brandService.getBrand(brandId);
    return ResponseEntity.ok(brand);
  }

  /**
   * 브랜드 삭제
   *
   * 어드민 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  @DeleteMapping("/{brandId}")
  public ResponseEntity<Void> deleteBrand(@PathVariable Long brandId) {
    brandService.deleteBrand(brandId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * 브랜드 수정
   *
   * 어드민 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  @PutMapping("/{brandId}")
  ResponseEntity<Void> updateBrand(@PathVariable Long brandId,@Valid @RequestBody BrandDTO brand) {
    brand.setBrandId(brandId);
    brandService.updateBrand(brand);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
