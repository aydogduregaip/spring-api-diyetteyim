package com.apiuygulama.apiuygulama.service;

import com.apiuygulama.apiuygulama.model.ECategory;
import com.apiuygulama.apiuygulama.model.EFoodType;
import com.apiuygulama.apiuygulama.model.Foods;
import com.apiuygulama.apiuygulama.model.JMenu;
import com.apiuygulama.apiuygulama.repository.FoodCategoryRepository;
import com.apiuygulama.apiuygulama.repository.FoodRepository;
import com.apiuygulama.apiuygulama.repository.FoodTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@Service
@NoArgsConstructor
public class OptionalMenuService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    public JMenu createMenu(Double pcalorie, String cinsiyet, Integer diabetes) {
        List<Foods> kahvalti, araogun, ogleyemegi;
        kahvalti = breakfast("KAHVALTI", diabetes);
        araogun = sbreakfast("GENEL", diabetes, "ATISTIRMALIK");
        ogleyemegi = breakfast("GENEL", diabetes);

        System.out.println(
                "Kahvalti: " + kahvalti + "\n" +
                "Atıştırmalık" + araogun + "\n" +
                "Öğle Yemeği" + ogleyemegi
        );

        return new JMenu(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, kahvalti, araogun, ogleyemegi);
    }

    public List<Foods> sbreakfast(String foodtype, Integer diabetes, String types){
        return foodRepository.findByTypeAndDiabetesAndFcategories(
                foodTypeRepository.findByName(EFoodType.valueOf(foodtype)),
                diabetes,
                foodCategoryRepository.findByName(ECategory.valueOf(types))
        );
    }

    public List<Foods> breakfast(String foodtype, Integer diabetes){
        return foodRepository.findByTypeAndDiabetes(
                foodTypeRepository.findByName(EFoodType.valueOf(foodtype)),
                diabetes
        );
    }
}
