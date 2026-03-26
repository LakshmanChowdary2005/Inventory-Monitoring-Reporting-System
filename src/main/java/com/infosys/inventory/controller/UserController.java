package com.infosys.inventory.controller;

import com.infosys.inventory.entity.Product;
import com.infosys.inventory.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/user-dashboard")
    public String userDashboard(HttpSession session, Model model){

        String role = (String) session.getAttribute("role");

        if(role == null){
            return "redirect:/login";
        }

        if(!role.equals("USER")){
            return "redirect:/dashboard";
        }

        List<Product> products = productRepo.findAll();

        model.addAttribute("products", products);

        return "user-dashboard";
    }
}