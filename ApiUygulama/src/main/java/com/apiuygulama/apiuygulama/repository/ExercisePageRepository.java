package com.apiuygulama.apiuygulama.repository;

import com.apiuygulama.apiuygulama.model.Exercise;
import com.apiuygulama.apiuygulama.model.Foods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercisePageRepository extends PagingAndSortingRepository<Exercise, Long> {
    Page<Exercise> findAll(Pageable pageable);

    Page<Exercise> findByNameContaining(String exerciseName, Pageable pageable);

    Exercise findByNameIgnoreCase(String exerciseName);
}
