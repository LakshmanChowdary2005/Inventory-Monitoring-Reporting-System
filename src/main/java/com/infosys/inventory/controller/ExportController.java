package com.infosys.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.infosys.inventory.entity.Product;
import com.infosys.inventory.entity.StockLog;
import com.infosys.inventory.repository.ProductRepository;
import com.infosys.inventory.repository.StockLogRepository;

import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ExportController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private StockLogRepository logRepo;

    // Export all products
    @GetMapping("/export/products")
    public void exportProducts(HttpServletResponse response) throws Exception {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition","attachment; filename=products.csv");

        List<Product> products = productRepo.findAll();

        PrintWriter writer = response.getWriter();

        writer.println("ID,Name,Category,Quantity,Price");

        for(Product p : products){
            writer.println(p.getId()+","+p.getName()+","+p.getCategory()+","+p.getQuantity()+","+p.getPrice());
        }

        writer.close();
    }

    // Export low stock products
    @GetMapping("/export/lowstock")
    public void exportLowStock(HttpServletResponse response) throws Exception {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition","attachment; filename=lowstock.csv");

        List<Product> lowStock = productRepo.findAll()
                .stream()
                .filter(p -> p.getQuantity() < 3)
                .collect(Collectors.toList());

        PrintWriter writer = response.getWriter();

        writer.println("ID,Name,Category,Quantity,Price");

        for(Product p : lowStock){
            writer.println(p.getId()+","+p.getName()+","+p.getCategory()+","+p.getQuantity()+","+p.getPrice());
        }

        writer.close();
    }

    // Export stock logs
    @GetMapping("/export/logs")
    public void exportLogs(HttpServletResponse response) throws Exception {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition","attachment; filename=stocklogs.csv");

        List<StockLog> logs = logRepo.findAll();

        PrintWriter writer = response.getWriter();

        writer.println("Action,Item,Quantity,Time");

        for(StockLog log : logs){
            writer.println(log.getAction()+","+log.getItemName()+","+log.getQuantity()+","+log.getTime());
        }

        writer.close();
    }
}