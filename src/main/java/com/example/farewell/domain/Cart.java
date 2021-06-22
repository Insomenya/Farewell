package com.example.farewell.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class Cart {
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public int getProductsCount(){
        return products.size();
    }

    public Cart() {
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public boolean isProductInCart(Long productId){
        for (Product prd : this.getProducts()){
            if (prd.getId() == productId) {
                return true;
            }
        }
        return false;
    }

    public Product findProductById(Long productId){
        for (Product prd : this.getProducts()){
            if (prd.getId() == productId) {
                return prd;
            }
        }
        return null;
    }

    public double getTotalSum(){
        double sum = 0.0D;
        for (Product product : products){
            sum += product.getPrice();
        }
        return sum;
    }

    public void clearCart(){
        products.clear();
    }
}
