package com.priyanshu.e_commerce_v2.util.mappers;

import org.springframework.stereotype.Component;

import com.priyanshu.e_commerce_v2.dto.orderItem.OrderItemResponse;
import com.priyanshu.e_commerce_v2.entity.orderItem.OrderItem;
import com.priyanshu.e_commerce_v2.entity.product.Product;

@Component
public class OrderItemMappers {

    public OrderItem toItem(Integer quantity, Product product) {

        OrderItem orderItem = new OrderItem();

        orderItem.setPricePerUnit(product.getPrice());
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);

        return orderItem;
    }

    public OrderItemResponse toItemResponse(OrderItem item) {

        OrderItemResponse response = new OrderItemResponse();

        response.setProductId(item.getProduct().getId());
        response.setProductName(item.getProduct().getName());
        response.setQuantity(item.getQuantity());
        response.setUnitPrice(item.getPricePerUnit());
        response.setTotalPrice(item.getTotalPrice());

        return response;
    }

}
