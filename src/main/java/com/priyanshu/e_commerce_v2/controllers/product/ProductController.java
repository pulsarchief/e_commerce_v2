package com.priyanshu.e_commerce_v2.controllers.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.e_commerce_v2.dto.product.ProductResponse;
import com.priyanshu.e_commerce_v2.repository.service.ProductService;
import com.priyanshu.e_commerce_v2.util.mappers.ProductMappers;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMappers productMappers;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {

        ProductResponse productResponse = productMappers.toProductResponse(productService.findActiveProduct(productId));

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> products = productService.getAllActiveProducts().stream()
                .map(x -> productMappers.toProductResponse(x)).toList();

        return new ResponseEntity<List<ProductResponse>>(products, HttpStatus.OK);
    }
}
