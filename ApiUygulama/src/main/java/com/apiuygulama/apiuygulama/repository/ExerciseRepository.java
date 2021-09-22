package com.apiuygulama.apiuygulama.repository;

import com.apiuygulama.apiuygulama.model.Exercise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    List<Exercise> findAllByName(String name);
    Optional<Exercise> findByName(String name);

    List<Exercise> findByCalorieLessThanEqual(
            Double calorie
    );
}
