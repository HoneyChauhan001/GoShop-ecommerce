package com.example.goshop.repository;

import com.example.goshop.Enum.Category;
import com.example.goshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    public List<Product> findByCategoryAndPrice(Category category, int price);
}
