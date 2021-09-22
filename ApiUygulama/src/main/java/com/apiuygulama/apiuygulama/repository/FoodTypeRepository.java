package com.apiuygulama.apiuygulama.repository;

import com.apiuygulama.apiuygulama.model.EFoodType;
import com.apiuygulama.apiuygulama.model.FoodType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {
    FoodType findByName(EFoodType name);
}
