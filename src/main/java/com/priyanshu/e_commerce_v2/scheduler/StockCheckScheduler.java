package com.priyanshu.e_commerce_v2.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.priyanshu.e_commerce_v2.entity.product.Product;
import com.priyanshu.e_commerce_v2.repository.ProductRepository;
import com.priyanshu.e_commerce_v2.service.MailService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StockCheckScheduler {

    private final ProductRepository productRepository;
    private final MailService mailService;

    @Value("${sender.mail.address}")
    private String adminMail;

    @Scheduled(cron = "0 0 0 * * * ")
    public void checkStock() {
        System.out.println("Running stock check at midnight...");

        StringBuilder body = new StringBuilder();

        for (Product product : productRepository.findAllByActiveTrue()) {

            if (product.getStock() == 0) {
                body.append("Product Id: " + product.getId() + " Name: " + product.getName() + " is out of stock.\n");
            } else if (product.getStock() < 6) {
                body.append("Product Id: " + product.getId() + " Name: " + product.getName() + " is low in stock.\n");
            }

        }

        if (body.isEmpty()) {
            body.append("All products in stock");
        }

        mailService.sendMail(adminMail, "Stock Checks Mail", body.toString());
    }
}
