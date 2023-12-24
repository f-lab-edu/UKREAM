package com.ukream.controller;

import com.ukream.annotation.LoginCheck;
import com.ukream.dto.PageRequestDTO;
import com.ukream.dto.ProductDTO;
import com.ukream.service.BrandService;
import com.ukream.service.ProductService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;

  private final BrandService brandService;

  /**
   * 상품 생성
   *
   * 어드민 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 201 (CREATED)
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  @PostMapping
  public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductDTO product) {
    brandService.checkBrandExists(product.getBrandId());
    productService.createProduct(product);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * 상품 목록 조회
   *
   * @return HTTP 상태 코드 200 (OK)와 상품 목록
   */
  @GetMapping
  public ResponseEntity<List<ProductDTO>> getProducts(@ModelAttribute PageRequestDTO pageRequestDTO) {
    List<ProductDTO> products = productService.getProducts(pageRequestDTO);
    return ResponseEntity.ok(products);
  }

  /**
   * 상품 조회
   *
   * @return HTTP 상태 코드 200 (OK)와 상품 정보
   */
  @GetMapping("/{productId}")
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId) {
    ProductDTO product = productService.getProduct(productId);
    return ResponseEntity.ok(product);
  }

  /**
   * 상품 삭제
   *
   * 어드민 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  @DeleteMapping("/{productId}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * 상품 수정
   *
   * 어드민 권한을 가진 사용자에게만 접근이 허용됩니다.
   *
   * @return HTTP 상태 코드 200 (OK)
   * @throws LoginRequiredException 권한이 없는 경우 발생합니다.
   */
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  @PutMapping("/{productId}")
  ResponseEntity<Void> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductDTO product) {
    product.setProductId(productId);
    productService.updateProduct(product);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
