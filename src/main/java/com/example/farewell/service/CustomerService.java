package com.example.farewell.service;

import com.example.farewell.domain.*;
import com.example.farewell.pojo.ProductEntry;
import com.example.farewell.repository.CustomerRepo;
import com.example.farewell.repository.OrderRepo;
import com.example.farewell.repository.ProductRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean addOrder(Order order, Long customer_id){
        return true;
    }

    public boolean addToCart(Product product){
        return true;
    }

    public void changeOptions(CustomUserDetails user, String newPass, String fullName){
        Customer currentCustomer = customerRepo.findByPhoneNum(user.getUsername());
        if (currentCustomer != null){
            if (fullName != null){
                currentCustomer.setFullName(fullName);
            }
            String encodedPass;
            if (newPass != null){
                encodedPass = passwordEncoder.encode(newPass);
                currentCustomer.setPassword(encodedPass);
            } else {
                encodedPass = user.getPassword();
            }
            customerRepo.save(currentCustomer);

            Authentication authentication = new PreAuthenticatedAuthenticationToken(user, encodedPass, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    public ProductEntry productToProductEntry(Product product, Cart cart){
        ProductEntry entry = new ProductEntry(product, cart.isProductInCart(product.getId()));
        return entry;
    }

    public Page<ProductEntry> pageToEntryPage(Page<Product> page, Cart cart){
        Page<ProductEntry> entryPage = page.map(product -> productToProductEntry(product, cart));
        return entryPage;
    }

    public void toggleCart(Cart cart, Long productId){
        if (productRepo.findById(productId).isPresent()){
            Product productFromDb = productRepo.findById(productId).get();
            if (cart.getProducts() == null){
                List<Product> temp = new ArrayList<>();
                cart.setProducts(temp);
            }
            if (cart.isProductInCart(productId)){
                cart.removeProduct(cart.findProductById(productId));
            } else {
                cart.addProduct(productFromDb);
            }
        }
    }

    public double getTotalSumIncludingDiscount(CustomUserDetails user, Cart cart){
        Customer customer = user.getCustomer();
        if (customer != null){
            double personalDiscount = customer.getPersonalDiscount();
            return cart.getTotalSum() * (1 - (personalDiscount / 100));
        }
        return cart.getTotalSum();
    }

    public boolean placeOrder(Cart cart){
        if (cart.getProducts().size() > 0){
            Customer customer = ((CustomUserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getCustomer();
            if (customer != null){
                Order order = new Order((short) 1, LocalDate.now(), customer);
                List<Product> productList = new ArrayList<>();
                for (Product product : cart.getProducts()){
                    productList.add(product);
                }
                order.setProducts(productList);
                order.setTotal(cart.getTotalSum());
                cart.clearCart();
                orderRepo.save(order);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void removeFromCart(Cart cart, Long productId){
        if (cart.getProducts() != null){
            if (cart.isProductInCart(productId)){
                cart.removeProduct(cart.findProductById(productId));
            }
        }
    }

    public void updateDiscount(String phone, double discount){
        Customer customer = customerRepo.findByPhoneNum(phone);
        if (customer != null){
            customer.setPersonalDiscount(discount);
            customerRepo.save(customer);
        }
    }

    public void confirmOrder(Long orderId, Operator operator){
        if (orderRepo.findById(orderId).isPresent()){
            Order order = orderRepo.findById(orderId).get();
            order.setStatus((short) 2);
            order.setProcessedBy(operator);
            orderRepo.save(order);
        }
    }
}
