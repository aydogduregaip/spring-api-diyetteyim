package com.apiuygulama.apiuygulama.repository;

import com.apiuygulama.apiuygulama.model.ECategory;
import com.apiuygulama.apiuygulama.model.FoodCategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {
    FoodCategory findByName(ECategory name);
}
