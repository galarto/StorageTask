package com.dandellion.services;

import com.dandellion.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> productList = new ArrayList<>();

    public Product saveProduct(Product product) {
        boolean savedProduct = productList.add(product);
        if(savedProduct) {
            return productList.get(productList.indexOf(product));
        }

        return null;
    }

    public Product getProduct(int index) {
        if(!productList.isEmpty() && index < productList.size() && productList.contains(productList.get(index))) {
            return productList.get(index);
        }
        return null;
    }

    public List<Product> getProductList() {
        if(!productList.isEmpty()) {
            return productList;
        }
        return null;
    }

    public Product updateProduct(int index, Product product) {
        if(productList.size() > index && productList.contains(productList.get(index))) {
            productList.add(index, product);
            return product;
        }
        return saveProduct(product);
    }

    public boolean deleteProduct(int index) {
        try {
            if (productList.contains(productList.get(index))) {
                productList.remove(index);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
