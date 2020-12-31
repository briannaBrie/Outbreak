package com.example.outbreak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String getHome(Model model) {
        //pass the model of the person who is logged in
        //model.addAttribute("username", username)
        return "/home";
    }
}
