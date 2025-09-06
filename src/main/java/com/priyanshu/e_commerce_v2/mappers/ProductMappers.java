package com.priyanshu.e_commerce_v2.mappers;

import org.springframework.stereotype.Component;

import com.priyanshu.e_commerce_v2.dto.product.AdminProductResponse;
import com.priyanshu.e_commerce_v2.dto.product.ProductEntry;
import com.priyanshu.e_commerce_v2.dto.product.ProductResponse;
import com.priyanshu.e_commerce_v2.entity.product.Product;

@Component
public class ProductMappers {

    public ProductResponse toProductResponse(Product product) {

        ProductResponse response = new ProductResponse();

        response.setProductId(product.getId());
        response.setDescription(product.getDescription());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        String availability = "";
        if (product.getStock() == 0) {
            availability = "Out of stock";
        } else if (product.getStock() < 6) {

            availability = "Low stock";
        } else {
            availability = (product.getStock() / 10) * 10 + "+ Available ";
        }

        response.setAvailability(availability);

        return response;
    }

    public AdminProductResponse toAdminProductResponse(Product product) {

        AdminProductResponse response = new AdminProductResponse();

        response.setProductId(product.getId());
        response.setDescription(product.getDescription());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setActive(product.getActive());
        return response;
    }

    public Product toProduct(ProductEntry request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());

        return product;
    }

}
