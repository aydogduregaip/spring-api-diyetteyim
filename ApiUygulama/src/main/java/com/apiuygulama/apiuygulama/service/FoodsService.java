package com.apiuygulama.apiuygulama.service;

import com.apiuygulama.apiuygulama.exception.FoodAlreadyExistsException;
import com.apiuygulama.apiuygulama.exception.FoodNotFoundException;
import com.apiuygulama.apiuygulama.exception.FoodTypeNotFoundException;
import com.apiuygulama.apiuygulama.model.*;
import com.apiuygulama.apiuygulama.payload.request.FoodRequest;
import com.apiuygulama.apiuygulama.repository.FoodCategoryRepository;
import com.apiuygulama.apiuygulama.repository.FoodRepository;
import com.apiuygulama.apiuygulama.repository.FoodTypeRepository;
import com.apiuygulama.apiuygulama.repository.FoodPageRespository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FoodsService {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @Autowired
    private FoodPageRespository foodPageRespository;

    public void updateFoodById(int id, FoodRequest foodRequest) {
        Foods oldFood = getFoodById(id);

        oldFood.setName(foodRequest.getName());
        oldFood.setPcalorie(foodRequest.getPcalorie());
        oldFood.setDiabetes(foodRequest.getDiabetes());
        oldFood.setPgram(foodRequest.getPgram());
        oldFood.setImage_url(foodRequest.getImage_url());
        oldFood.setFcategories(getCategories(foodRequest));
        oldFood.setType(getFoodType(foodRequest));
        oldFood.setCarbohydrate(foodRequest.getCarbohydrate());
        oldFood.setProtein(foodRequest.getProtein());
        oldFood.setFat(foodRequest.getFat());
        oldFood.setDescription(foodRequest.getDescription());
        oldFood.setF_measure(foodRequest.getF_measure());
        oldFood.setH_measure(foodRequest.getH_measure());
        oldFood.setG_measure(foodRequest.getG_measure());

        foodRepository.save(oldFood);
    }

    public Foods getFoodById(int id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException("Error: Food is not found: " + id));
    }

    public Foods addFoodsService(FoodRequest foodRequest) {
        // Yemeğin kayıtlı olup olmadığının kontrolü
        getFoodByName(foodRequest.getName());

        Foods model_foods = new Foods(
                foodRequest.getName(),
                foodRequest.getPcalorie(),
                foodRequest.getPgram(),
                foodRequest.getDiabetes(),
                foodRequest.getImage_url(),
                foodRequest.getCarbohydrate(),
                foodRequest.getProtein(),
                foodRequest.getFat(),
                foodRequest.getDescription(),
                foodRequest.getF_measure(),
                foodRequest.getH_measure(),
                foodRequest.getG_measure());
        model_foods.setType(getFoodType(foodRequest));
        model_foods.setFcategories(getCategories(foodRequest));

        return (foodRepository.save(model_foods));
    }

    public Foods getFoodByName(String name){
        if (foodRepository.findByName(name).isPresent())
            throw new FoodAlreadyExistsException("Error: Food is already exists: " + name);
        else
            return null;
    }

    public Set<FoodCategory> getCategories(FoodRequest foodRequest){
        Set<String> strCategories = foodRequest.getFcategory();
        Set<FoodCategory> categories = new HashSet<>();

        try {
            strCategories.forEach(f_category -> {
                categories.add(foodCategoryRepository.findByName(ECategory.valueOf(f_category)));
            });
            return categories;
        }
        catch (IllegalArgumentException exception){
            throw new FoodNotFoundException("Error: Food category is not found");
        }
    }

    public Set<FoodType> getFoodType(FoodRequest foodRequest){
        Set<String> strCategories = foodRequest.getFtype();
        Set<FoodType> ftype = new HashSet<>();

        try {
            strCategories.forEach(f_type -> {
                ftype.add(foodTypeRepository.findByName(EFoodType.valueOf(f_type)));
            });
            return ftype;
        }
        catch (IllegalArgumentException exception){
            throw new FoodTypeNotFoundException("Error: Food type is not found");
        }
    }

    public Page<Foods> getAllPFoods(String foodsName, int page, int size) {
        Pageable pageable = createPageable(page, size);
        if (StringUtils.isEmpty(foodsName)) {
            return foodPageRespository.findAll(pageable);
        }
        return foodPageRespository.findByNameContaining(foodsName, pageable);
    }

    private Pageable createPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Pageable pageable1 = PageRequest.of(page, size);
        Pageable pageable2 = PageRequest.of(page, size, Direction.ASC, "id");
        return pageable;
    }

    public Foods getFoodsByName(String foodsName) {
        return foodPageRespository.findByNameIgnoreCase(foodsName);
    }
}