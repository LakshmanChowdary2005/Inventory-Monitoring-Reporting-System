package com.infosys.inventory.controller;

import com.infosys.inventory.entity.Product;
import com.infosys.inventory.entity.StockLog;
import com.infosys.inventory.repository.ProductRepository;
import com.infosys.inventory.repository.StockLogRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private StockLogRepository logRepo;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        String role = (String) session.getAttribute("role");

        if (role == null) {
            return "redirect:/login";
        }

        List<Product> products = productRepo.findAll();
        List<StockLog> logs = logRepo.findAll();

        long totalProducts = products.size();

        double totalValue = products.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();

        long lowStock = products.stream()
                .filter(p -> p.getQuantity() < 3)
                .count();

        model.addAttribute("products", products);
        model.addAttribute("logs", logs);
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("totalValue", totalValue);
        model.addAttribute("lowStock", lowStock);
        model.addAttribute("role", role); // 🔥 IMPORTANT

        return "dashboard";
    }
}