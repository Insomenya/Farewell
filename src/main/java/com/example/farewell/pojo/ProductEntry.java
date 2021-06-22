package com.example.farewell.pojo;

import com.example.farewell.domain.Product;

public class ProductEntry {
    public Product product;
    public boolean inCart;

    public ProductEntry(Product product, boolean inCart) {
        this.product = product;
        this.inCart = inCart;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }
}
