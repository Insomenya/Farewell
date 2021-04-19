package com.example.farewell.controller;

import com.example.farewell.domain.CustomUserDetails;
import com.example.farewell.service.CustomDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {

    @Autowired
    CustomDetailsService userService;

    @GetMapping("/register")
    public String registration() {
        return "register";
    }

    @PostMapping("/register")
    public String registerCustomer(
            @RequestParam("fullName") String fullName,
            @Valid CustomUserDetails user,
            BindingResult bindingResult,
            Model model
            ){

        if (bindingResult.hasErrors()) {
            model.mergeAttributes(userService.getErrors(bindingResult));
            return "register";
        }

        if (!userService.addCustomer(user, fullName)) {
            model.addAttribute("usernameError", "User exists.");
            return "register";
        }

        return "redirect:/login";
    }

    @GetMapping("operator/registerOperator")
    public String registerOperator(){
        return "registerOperator";
    }

    @PostMapping("operator/registerOperator")
    public String registerOperator(
            @RequestParam("fullName") String fullName,
            @Valid CustomUserDetails user,
            BindingResult bindingResult,
            Model model
    ){
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(userService.getErrors(bindingResult));
            return "register";
        }

        if (!userService.addOperator(user, fullName)) {
            model.addAttribute("usernameError", "User exists.");
            return "register";
        }

        return "redirect:/login";
    }
}
