package com.example.farewell.controller;

import com.example.farewell.domain.*;
import com.example.farewell.repository.CustomerRepo;
import com.example.farewell.repository.OrderRepo;
import com.example.farewell.service.CommonMethods;
import com.example.farewell.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("session")
@PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private Cart cart;

    @GetMapping("/cart")
    public String viewCart(@AuthenticationPrincipal CustomUserDetails user, Model model){
        model.addAttribute("products", cart.getProducts());
        model.addAttribute("totalSum", cart.getTotalSum());
        model.addAttribute("personalDiscount", (user.getCustomer().getPersonalDiscount()));
        model.addAttribute("totalDiscountSum", customerService.getTotalSumIncludingDiscount(user, cart));
        return "cart";
    }

    @GetMapping("/history")
    public String viewHistory(@AuthenticationPrincipal CustomUserDetails user,
                              Model model,
                              @PageableDefault(sort = { "date" }, direction = Sort.Direction.DESC) Pageable pageable){
        Page<Order> page =  orderRepo.findAllByCustomerOrderByDate(user.getCustomer(), pageable);
        model.addAttribute("page", page);
        return "history";
    }

    @GetMapping("/options")
    public String viewOptions(
            @AuthenticationPrincipal CustomUserDetails user,
            Model model
    ){
        model.addAttribute("customer", customerRepo.findByPhoneNum(user.getUsername()));
        return "options";
    }

    @PostMapping("/options")
    public String changeProfile(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestParam(value = "fullName", required = false) String fullName,
            @RequestParam(value = "newPass", required = false) String newPass,
            @RequestParam(value = "passVerification", required = false) String passVerification,
            Model model
    ){
        Customer currentCustomer = customerRepo.findByPhoneNum(user.getUsername());
        if (newPass != null && !newPass.equals(passVerification)) {
            model.addAttribute("newPassError", "Пароли не совпадают.");
            newPass = null;
        }

        customerService.changeOptions(user, newPass, fullName);
        model.addAttribute("message", "Настройки успешно применены.");
        model.addAttribute("customer", currentCustomer);
        return "options";
    }

    @PostMapping("/cart")
    public String placeOrder(@AuthenticationPrincipal CustomUserDetails user, Model model){
        if (customerService.placeOrder(cart)){
            return "redirect:/customer/history";
        } else {
            model.addAttribute("products", cart.getProducts());
            model.addAttribute("totalSum", cart.getTotalSum());
            model.addAttribute("personalDiscount", (user.getCustomer().getPersonalDiscount()));
            model.addAttribute("totalDiscountSum", customerService.getTotalSumIncludingDiscount(user, cart));
            model.addAttribute("error", "Произошла ошибка и операция не была вывполнена.");
            return "cart";
        }
    }
}
