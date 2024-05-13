package com.dandellion.controllers;

import com.dandellion.dto.RequestDto;
import com.dandellion.models.Product;
import com.dandellion.services.FiltersSpecification;
import com.dandellion.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private FiltersSpecification<Product> filtersSpecification;

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        if (savedProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
        Product updatedProduct = productService.saveProduct(product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable("id") long id) {
        Product product = productService.getProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> products = productService.getProductList();
        if (products != null) {
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("searcht/")
    public ResponseEntity<List<Product>> getFilteredProductList(@Valid @RequestParam(required = false) String title,
                                                                @Valid @RequestParam boolean sorted) {
        if(!sorted) {
        List<Product> products = productService.findByTitleContaining(title, Sort.by("title").ascending());
            if(products != null) {
            return ResponseEntity.ok(products);
            }
        }
        List<Product> products = productService.findByTitleContaining(title, Sort.by("title").descending());
        if(products != null) {
        return ResponseEntity.ok(products);
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("searchp/")
    public ResponseEntity<List<Product>> getFilteredProductList(@Valid @RequestParam(required = false) double gprice,
                                                                @Valid @RequestParam(required = false) double lprice,
                                                                @Valid @RequestParam boolean sorted) {
        if(gprice != 0 && !sorted) {
            List<Product> products = productService
                                    .findByPriceGreaterThanEqual(gprice, Sort.by("price").ascending());
            if(products != null) {
                return ResponseEntity.ok(products);
            }
        } else if(gprice != 0 && sorted) {
            List<Product> products = productService
                    .findByPriceGreaterThanEqual(gprice, Sort.by("price").descending());
            if(products != null) {
                return ResponseEntity.ok(products);
            }
        }
        if(lprice != 0 && !sorted) {
            List<Product> products = productService
                                    .findByPriceLessThanEqual(lprice, Sort.by("price").ascending());
            if (products != null) {
                return ResponseEntity.ok(products);
            }
        } else if (lprice != 0 && sorted) {
            List<Product> products = productService
                                    .findByPriceLessThanEqual(lprice, Sort.by("price").descending());
            if (products != null) {
                return ResponseEntity.ok(products);
            }
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("searchlp/")
    public ResponseEntity<List<Product>> getFilteredProductList(@Valid @RequestParam(required = false,
                                                                value = "available") boolean available) {
        List<Product> products = productService.findByIsAvailable(available);
        if(products != null) {
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public List<Product> getProducts(@RequestBody RequestDto requestDto) {
        Specification<Product> searchSpecification = filtersSpecification
                .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
        return productService.findAll(searchSpecification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
        var isRemoved = productService.deleteProduct(id);
        if (!isRemoved) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
