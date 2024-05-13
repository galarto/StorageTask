package com.dandellion.repositories;

import com.dandellion.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByTitleContaining(String title, Sort sort);
    List<Product> findAllByPriceGreaterThanEqual(double price, Sort sort);
    List<Product> findAllByPriceLessThanEqual(double price, Sort sort);
    List<Product> findAllByIsAvailable(boolean isAvailable);

}
