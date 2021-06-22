package com.example.farewell.controller;


import com.example.farewell.domain.Cart;
import com.example.farewell.domain.Category;
import com.example.farewell.domain.Product;
import com.example.farewell.pojo.ProductEntry;
import com.example.farewell.repository.CategoryRepo;
import com.example.farewell.repository.ProductRepo;
import com.example.farewell.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("session")
@RequestMapping("/")
public class MainController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private Cart cart;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String main(@RequestParam(required = false) Long category,
                       Model model,
                       @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable){

        Page<Product> page;

        if (category != null) {
            page = productRepo.findAllByOrdersIsNullAndCategoryIs(categoryRepo.findById(category).get(), pageable);

        } else {
            page = productRepo.findAllByOrdersIsNull(pageable);
        }

        Page<ProductEntry> entryPage = customerService.pageToEntryPage(page, cart);

        Iterable<Category> categories = categoryRepo.findAll();

        model.addAttribute("page", entryPage);
        model.addAttribute("url", "/");
        model.addAttribute("cartSize", cart.getProducts().size());
        model.addAttribute("categories", categories);
        return "main";
    }
}
