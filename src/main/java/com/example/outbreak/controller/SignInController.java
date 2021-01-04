package com.example.outbreak.controller;

import com.example.outbreak.model.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class SignInController {
   /* //to get login form page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getSignForm(){
        //return html page name
        return "login";
    }
    //checking for login credentials
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model){
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if("admin".equals(username)&&"admin".equals(password)){
            //if username and password is correct, we are returning home page
            return "home";
        }
        //if username or password is wrong
        model.addAttribute("invalidCredentials", true);
        return "login";
    }*/
}
