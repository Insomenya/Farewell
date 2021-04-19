package com.example.farewell.controller;

import com.example.farewell.domain.CustomUserDetails;
import com.example.farewell.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @PreAuthorize("hasAuthority('OPERATOR')")
    @GetMapping("/orders")
    public String pendingOrders(){
        return "orderList";
    }

    @PreAuthorize("hasAuthority('OPERATOR')")
    @GetMapping("/stats")
    public String displayStats(){
        return "statistics";
    }

    @PreAuthorize("hasAuthority('OPERATOR')")
    @GetMapping("/newProduct")
    public String newProduct(){
        return "newProduct";
    }

    @PreAuthorize("hasAuthority('OPERATOR')")
    @GetMapping("/newCategory")
    public String newCategory(){
        return "newCategory";
    }
}
