package com.example.farewell.controllerRest;


import com.example.farewell.domain.Cart;
import com.example.farewell.domain.CustomUserDetails;
import com.example.farewell.domain.Operator;
import com.example.farewell.pojo.*;
import com.example.farewell.repository.CustomerRepo;
import com.example.farewell.repository.OperatorRepo;
import com.example.farewell.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
@Scope("session")
public class RestController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OperatorRepo operatorRepo;
    @Autowired
    private Cart cart;

    @PostMapping(value = "toggleCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void toggleCart(@RequestBody ProductId productId){
        customerService.toggleCart(cart, (long) productId.getProductId());
    }

    @PostMapping(value = "removeCartItem", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void removeCartItem(@RequestBody ProductId productId){
        customerService.removeFromCart(cart, (long) productId.getProductId());
    }

    @GetMapping(value = "getDiscountValue", produces = MediaType.APPLICATION_JSON_VALUE)
    public DiscountValue getDiscountValue(@RequestParam String customer){
        DiscountValue discountValue = new DiscountValue(customerRepo.findByPhoneNum(customer).getPersonalDiscount());
        return discountValue;
    }

    @PostMapping(value = "updateDiscount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDiscount(@RequestBody UpdatedDiscount updatedDiscount){
        customerService.updateDiscount(updatedDiscount.getPhone(), updatedDiscount.getDiscount());
    }

    @PostMapping(value = "confirmOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void confirmOrder(@RequestBody OrderId orderId){
        Operator operator = operatorRepo.findByLogin(orderId.getOperatorName());
        if (operator != null){
            customerService.confirmOrder(orderId.getOrderId(), operator);
        }
    }
}
