package com.apiuygulama.apiuygulama.service;

import com.apiuygulama.apiuygulama.model.ECategory;
import com.apiuygulama.apiuygulama.model.EFoodType;
import com.apiuygulama.apiuygulama.model.Foods;
import com.apiuygulama.apiuygulama.repository.FoodCategoryRepository;
import com.apiuygulama.apiuygulama.repository.FoodRepository;
import com.apiuygulama.apiuygulama.repository.FoodTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SSuggestService {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @Autowired
    private SuggestionService suggestionService;

    private Double sum = Double.MAX_VALUE;

    public List<Foods> sSuggest(Double pcalorie, Integer diabetes) {

        try {
            Foods atistirmalik;
            double atistirmalikrandom;
            Double oldcalorie = pcalorie;
            Integer i = 1;

            atistirmalik = suggestionService.rndFood(snack("GENEL", pcalorie, diabetes, "ATISTIRMALIK"));

            while (true) {
                atistirmalikrandom = i;
                sum = atistirmalik.getPcalorie() * atistirmalikrandom;
                i++;

                if ((sum <= pcalorie) && (sum >= ((((pcalorie * 100) / 5) * 4.75) / 100))) {
                    break;
                }
                if (sum > pcalorie || atistirmalik.getPcalorie() * 2 >= pcalorie) {
                    return sSuggest(oldcalorie, diabetes);
                }
            }

            System.out.println("Atıştırmalık Gereken kalori: " + (pcalorie) +
                    " Toplam: " + (atistirmalik.pcalorie * atistirmalikrandom) + "\n" +
                    " Atistirmalik: " + atistirmalik.getPcalorie() * atistirmalikrandom + " rndp: " + atistirmalikrandom + " Name: " + atistirmalik.name + " Gram: " + atistirmalik.pgram * atistirmalikrandom + "\n");

            atistirmalik.setPcalorie(atistirmalik.getPcalorie() * atistirmalikrandom);
            atistirmalik.setPgram(atistirmalik.getPgram() * atistirmalikrandom);

            return new ArrayList<>(Arrays.asList(atistirmalik));
        }
        catch (RuntimeException exception){
            throw new RuntimeException("Zaman Aşımı Tekrar Deneyiniz...");
        }
        catch (StackOverflowError exception){
            throw new StackOverflowError("Zaman Aşımı Tekrar Deneyiniz...");
        }
    }

    public List<Foods> snack(String foodtype, Double pcalorie, Integer diabetes, String types){
        return foodRepository.findByTypeAndPcalorieLessThanEqualAndDiabetesAndFcategories(
                foodTypeRepository.findByName(EFoodType.valueOf(foodtype)),
                pcalorie,
                diabetes,
                foodCategoryRepository.findByName(ECategory.valueOf(types))
        );
    }
}
