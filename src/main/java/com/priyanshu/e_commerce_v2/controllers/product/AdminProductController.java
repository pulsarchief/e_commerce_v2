package com.priyanshu.e_commerce_v2.controllers.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.e_commerce_v2.dto.product.AdminProductResponse;
import com.priyanshu.e_commerce_v2.dto.product.ProductEntry;
import com.priyanshu.e_commerce_v2.dto.product.ProductUpdate;
import com.priyanshu.e_commerce_v2.entity.product.Product;
import com.priyanshu.e_commerce_v2.mappers.ProductMappers;
import com.priyanshu.e_commerce_v2.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final ProductMappers productMappers;

    @GetMapping("/{productId}")
    public ResponseEntity<AdminProductResponse> getProduct(@PathVariable Long productId) {

        AdminProductResponse productResponse = productMappers
                .toAdminProductResponse(productService.findProduct(productId));

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<AdminProductResponse> addNewProduct(@RequestBody ProductEntry request) {

        Product product = productMappers.toProduct(request);

        AdminProductResponse productResponse = productMappers
                .toAdminProductResponse(productService.addProduct(product));

        return new ResponseEntity<AdminProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<AdminProductResponse> updateProductDetails(@RequestBody ProductUpdate update) {

        AdminProductResponse productResponse = productMappers
                .toAdminProductResponse(productService.updateProduct(update));

        return new ResponseEntity<AdminProductResponse>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminProductResponse>> getAllProducts() {

        List<AdminProductResponse> products = productService.getAllProducts().stream()
                .map(x -> productMappers.toAdminProductResponse(x)).toList();

        return new ResponseEntity<List<AdminProductResponse>>(products, HttpStatus.OK);
    }

}
