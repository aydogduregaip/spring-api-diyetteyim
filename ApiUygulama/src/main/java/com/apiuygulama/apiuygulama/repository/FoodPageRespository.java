package com.apiuygulama.apiuygulama.repository;

import com.apiuygulama.apiuygulama.model.Foods;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface FoodPageRespository extends PagingAndSortingRepository<Foods, Long> {

    Page<Foods> findAll(Pageable pageable);

    Page<Foods> findByNameContaining(String foodsName, Pageable pageable);

    Foods findByNameIgnoreCase(String foodsName);
}