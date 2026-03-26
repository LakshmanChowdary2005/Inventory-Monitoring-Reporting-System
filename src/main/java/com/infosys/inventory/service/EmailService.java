package com.infosys.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendLowStockAlert(String productName, int quantity) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("lakshmamnchowdary2005@gmail.com");
        message.setSubject("Low Stock Alert - Inventory System");

        message.setText(
                "Warning!\n\nProduct: " + productName +
                        "\nCurrent Stock: " + quantity +
                        "\n\nPlease restock immediately."
        );

        mailSender.send(message);
    }

    public void sendLowStockAlert(String name) {
    }
}