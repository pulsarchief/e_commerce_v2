package com.priyanshu.e_commerce_v2.repository.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.dto.product.ProductUpdate;
import com.priyanshu.e_commerce_v2.entity.product.Product;
import com.priyanshu.e_commerce_v2.exception.ProductNotFoundException;
import com.priyanshu.e_commerce_v2.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;

    public Product findActiveProduct(Long productId) {

        return productRepository.findByIdAndActiveTrue(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product" + productId + " not found "));

    }

    public Product findProduct(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product" + productId + " not found "));

    }

    public Product addProduct(Product product) {

        return productRepository.save(product);

    }

    public Product updateProduct(ProductUpdate newDetails) {

        Product oldDetails = findProduct(newDetails.getProductId());

        if (newDetails.getActive() != null) {
            oldDetails.setActive(newDetails.getActive());
        }

        if (newDetails.getDescription() != null) {
            oldDetails.setDescription(newDetails.getDescription());
        }

        if (newDetails.getName() != null) {
            oldDetails.setName(newDetails.getName());
        }

        if (newDetails.getPrice() != null) {
            oldDetails.setPrice(newDetails.getPrice());
        }

        if (newDetails.getStock() != null) {
            oldDetails.setStock(newDetails.getStock());
        }

        return productRepository.save(oldDetails);
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();

    }

    public List<Product> getAllActiveProducts() {

        return productRepository.findAllByActiveTrue();

    }

}
