package com.dandellion.services;

import com.dandellion.models.Product;
import com.dandellion.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Product> findAll(Specification<Product> specification) {
        return productRepository.findAll(specification);
    }

   public List<Product> findByTitleContaining(String title, Sort sort) {
        List<Product> products = productRepository.findAllByTitleContaining(title, sort);
        if(products != null) {
            return products;
        }
        return null;
    }
    public List<Product> findByPriceGreaterThanEqual(@Valid double price, Sort sort) {
        List<Product> products = productRepository.findAllByPriceGreaterThanEqual(price, sort);
        if(products != null) {
            return products;
        }
        return null;
    }
    public List<Product> findByPriceLessThanEqual(@Valid double price, Sort sort) {
        List<Product> products = productRepository.findAllByPriceLessThanEqual(price, sort);
        if(products != null) {
            return products;
        }
        return null;
    }
    public List<Product> findByIsAvailable(Boolean available) {
        List<Product> products = productRepository.findAllByIsAvailable(available);
        if(products != null) {
            return products;
        }
        return null;
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
