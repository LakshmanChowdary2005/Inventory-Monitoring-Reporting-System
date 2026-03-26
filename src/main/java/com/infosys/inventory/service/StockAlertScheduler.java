package com.infosys.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.infosys.inventory.entity.Product;
import com.infosys.inventory.repository.ProductRepository;

@Component
public class StockAlertScheduler {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private EmailService emailService;

    // Runs every 1 hour
    @Scheduled(cron = "0 0 * * * *")
    public void checkLowStock() {

        List<Product> products = productRepo.findAll();

        for(Product p : products){

            if(p.getQuantity() < 3){

                emailService.sendLowStockAlert(p.getName(), p.getQuantity());

            }
        }
    }
}