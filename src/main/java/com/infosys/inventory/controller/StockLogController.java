package com.infosys.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.infosys.inventory.repository.StockLogRepository;

@Controller
public class StockLogController {

    @Autowired
    private StockLogRepository logRepo;

    @GetMapping("/logs")
    public String viewLogs(Model model) {

        model.addAttribute("logs", logRepo.findAll());

        return "logs";
    }
}