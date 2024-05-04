package com.dandellion.repositories;

import com.dandellion.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByTitleContaining(String title, Sort sort);
    List<Product> findAllByPriceGreaterThanEqual(Long price, Sort sort);
    List<Product> findAllByPriceLessThanEqual(Long price, Sort sort);
    List<Product> findAllByIsAvailable(Boolean isAvailable);
}
