package com.infosys.inventory.controller;

import com.infosys.inventory.entity.Product;
import com.infosys.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    // ➕ ADD PRODUCT
    @PostMapping("/add-product")
    public String addProduct(Product p){
        productRepo.save(p);
        return "redirect:/dashboard";
    }

    // ➕ INCREASE STOCK (FIXED → GET)
    @GetMapping("/increase/{id}")
    public String increase(@PathVariable Long id){
        Product p = productRepo.findById(id).orElseThrow();
        p.setQuantity(p.getQuantity() + 1);
        productRepo.save(p);
        return "redirect:/dashboard";
    }

    // ➖ DECREASE STOCK (FIXED → GET)
    @GetMapping("/decrease/{id}")
    public String decrease(@PathVariable Long id){
        Product p = productRepo.findById(id).orElseThrow();
        if(p.getQuantity() > 0){
            p.setQuantity(p.getQuantity() - 1);
            productRepo.save(p);
        }
        return "redirect:/dashboard";
    }

    // ❌ DELETE PRODUCT (FIXED → GET)
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        productRepo.deleteById(id);
        return "redirect:/dashboard";
    }
}