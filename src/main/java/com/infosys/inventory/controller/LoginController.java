package com.infosys.inventory.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username,String password,String role,HttpSession session){

        if(username.equals("admin") && password.equals("admin123") && role.equals("ADMIN")){
            session.setAttribute("role","ADMIN");
            return "redirect:/success";
        }

        if(username.equals("manager") && password.equals("manager123") && role.equals("MANAGER")){
            session.setAttribute("role","MANAGER");
            return "redirect:/success";
        }

        if(username.equals("user") && password.equals("user123") && role.equals("USER")){
            session.setAttribute("role","USER");
            return "redirect:/success";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}