package com.example.farewell.controller;

import com.example.farewell.domain.*;
import com.example.farewell.pojo.CategoryOrderCountStatistic;
import com.example.farewell.pojo.OperatorOrderCountStatistic;
import com.example.farewell.repository.*;
import com.example.farewell.service.CommonMethods;
import com.example.farewell.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ROLE_OPERATOR')")
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OperatorOrderCountRepository operatorOrderCountRepository;
    @Autowired
    private CategoryOrderCountRepository categoryOrderCountRepository;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/orders")
    public String pendingOrders(Model model){
        List<Order> orderList = orderRepo.findAllByStatusEqualsOrderByDate((short) 1);
        model.addAttribute("orderList", orderList);
        return "orderList";
    }

    @GetMapping("/changeDiscounts")
    public String changeDiscounts(Model model){
        Iterable<Customer> customerList = customerRepo.findAll();
        model.addAttribute("customerList", customerList);
        return "customerList";
    }

    @GetMapping("/history")
    public String viewHistory(@AuthenticationPrincipal CustomUserDetails user,
                              Model model,
                              @PageableDefault(sort = { "date" }, direction = Sort.Direction.DESC) Pageable pageable){
        Page<Order> page =  orderRepo.findAllByProcessedByOrderByDate(user.getOperator(), pageable);
        model.addAttribute("page", page);
        return "history";
    }

    @GetMapping("/stats")
    public String displayStats(Model model){
        List<OperatorOrderCountStatistic> orderCountStatistic = operatorOrderCountRepository.findOrderCountByOperator();
        List<Order> orders = orderRepo.findAllByStatusEqualsOrderByDate((short) 2);
        List<CategoryOrderCountStatistic> categoryOrderCountStatistic = categoryOrderCountRepository.findOrderCountByCategory();
        model.addAttribute("orders", orders);
        model.addAttribute("orderCountPieChart", orderCountStatistic);
        model.addAttribute("categoryCountPieChart", categoryOrderCountStatistic);
        return "statistics";
    }

    @GetMapping("/newProduct")
    public String newProduct(Model model){
        Iterable<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "newProduct";
    }

    @GetMapping("/newCategory")
    public String newCategory(){ return "newCategory"; }

    @PostMapping("/newProduct")
    public String addProduct(
            @Valid Product product,
            @RequestParam("image_file") MultipartFile imageFile,
            @RequestParam("category_id") Long category_id,
            @AuthenticationPrincipal CustomUserDetails author,
            BindingResult bindingResult,
            Model model
            ) throws IOException {
        if (bindingResult.hasErrors())
        {
            Map<String, String> errorsMap = CommonMethods.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("product", product);
        } else {
            if (operatorService.addProduct(product, imageFile, category_id, author)){
                model.addAttribute("success", 1);
                model.addAttribute("product", null);
            } else {
                model.addAttribute("message", "Во время добавления товара произошла ошибка.");
            }
        }
        Iterable<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "newProduct";
    }

    @PostMapping("/newCategory")
    public String addCategory(
            @Valid Category category,
            BindingResult bindingResult,
            Model model
            ){
        if (bindingResult.hasErrors())
        {
            Map<String, String> errorsMap = CommonMethods.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            if (!operatorService.addCategory(category)){
                model.addAttribute("nameError", "Категория существует");
            } else {
                model.addAttribute("newCategoryName", category.getName());
            }
        }
        return "newCategory";
    }
}
