package com.infosys.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // ✅ Intro Page
    @GetMapping("/")
    public String introPage() {
        return "intro";
    }
}