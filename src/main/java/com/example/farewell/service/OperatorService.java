package com.example.farewell.service;

import com.example.farewell.repository.CategoryRepo;
import com.example.farewell.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public boolean addCategory(){
        return true;
    }

    public boolean addProduct(){
        return true;
    }
}
