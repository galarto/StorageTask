package com.dandellion.services;

import com.dandellion.models.Product;
import com.dandellion.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        if (savedProduct != null) {
            return savedProduct;
        }
        return null;
    }


    public Product getProduct(Long id) {
        if (productRepository.existsById(id)) {
            return productRepository.findById(id).get();
        }
        return null;
    }

    public List<Product> getProductList() {
        return productRepository.findAll(Pageable.unpaged()).getContent();
    }

    public boolean deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
