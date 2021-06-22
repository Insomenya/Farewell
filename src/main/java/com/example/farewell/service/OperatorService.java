package com.example.farewell.service;

import com.example.farewell.domain.Category;
import com.example.farewell.domain.CustomUserDetails;
import com.example.farewell.domain.Operator;
import com.example.farewell.domain.Product;
import com.example.farewell.repository.CategoryRepo;
import com.example.farewell.repository.OperatorRepo;
import com.example.farewell.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class OperatorService {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private OperatorRepo operatorRepo;

    public boolean addCategory(Category category){
        Category categoryFromDB = categoryRepo.findByName(category.getName());
        if (categoryFromDB != null) {
            return false;
        }

        Category newCategory = new Category(category.getName());
        categoryRepo.save(newCategory);
        return true;
    }

    public boolean addProduct(Product product, MultipartFile image, Long category_id, CustomUserDetails author) throws IOException {
        if (!categoryRepo.findById(category_id).isPresent()){
            return false;
        }
        Category selectedCategory = categoryRepo.findById(category_id).get();
        Operator addedBy = operatorRepo.findByLogin(author.getUsername());
        Product newProduct = new Product(product.getName(), product.getPrice(), product.getState(), product.getDescription(), selectedCategory, addedBy);
        if (image != null && !image.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + image.getOriginalFilename();

            image.transferTo(new File(uploadPath + "/" + resultFilename));

            newProduct.setImage(resultFilename);
        }

        productRepo.save(newProduct);

        return true;
    }
}
