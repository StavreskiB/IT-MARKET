package com.example.it_market.persistence_or_repository;

import com.example.it_market.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<Subcategory, Long> {
}
