package com.apiuygulama.apiuygulama.repository;

import com.apiuygulama.apiuygulama.model.FoodCategory;
import com.apiuygulama.apiuygulama.model.FoodType;
import com.apiuygulama.apiuygulama.model.Foods;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Cacheable;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Foods, Integer> {

    List<Foods> findAllByNameIgnoreCaseContaining(String name);

    List<Foods> findByTypeAndDiabetes(
            FoodType tname,
            Integer diabetes
    );

    List<Foods> findByTypeAndDiabetesAndFcategories(
            FoodType tname,
            Integer diabetes,
            FoodCategory name
    );

    List<Foods> findByTypeAndPcalorieLessThanEqualAndDiabetesAndFcategories(
            FoodType tname,
            Double pcalorie,
            Integer diabetes,
            FoodCategory name
    );

    List<Foods> findAllByName(String name);
    Optional<Foods> findByName(String name);
    Optional<Foods> findById(String id);
}

