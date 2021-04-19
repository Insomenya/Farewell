package com.example.farewell.controller;


import com.example.farewell.domain.Product;
import com.example.farewell.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping
    public String main(Model model){
        Iterable<Product> products = productRepo.findAllByOrdersIsNull();
        model.addAttribute("products", products);
        return "main";
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping
    public String makeOrder(Model model){

        return "redirect:/customer/history";
    }
}
