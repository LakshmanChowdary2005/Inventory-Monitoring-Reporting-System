package com.infosys.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.inventory.service.EmailService;

@RestController
public class TestMailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/testmail")
    public String sendMail() {

        emailService.sendLowStockAlert("Test Product", 2);

        return "Mail Sent";
    }
}