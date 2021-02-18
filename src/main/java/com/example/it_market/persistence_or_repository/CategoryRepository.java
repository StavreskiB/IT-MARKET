package com.example.it_market.persistence_or_repository;

import com.example.it_market.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
