package com.infosys.inventory.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuccessController {

    @GetMapping("/success")
    public String successPage(HttpSession session){

        if(session.getAttribute("role") == null){
            return "redirect:/login";
        }

        return "success";
    }
}