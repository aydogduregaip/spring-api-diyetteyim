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
public class DSuggestService {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @Autowired
    private SuggestionService suggestionService;

    private Double sum = Double.MAX_VALUE;

    public List<Foods> dSuggest(Double pcalorie, Integer diabetes, Double multiplier){
        try {
            Foods soup, maincourse, salad, dessert, bread;
            double saladrandom, souprandom, dessertrandom,breadrandom;
            Double oldcalorie = pcalorie;
            int i = 1;

            maincourse = suggestionService.rndFood(dinner("GENEL", pcalorie, diabetes, "ANAYEMEK"));

            if (maincourse.pcalorie > ((pcalorie * 75) / 100)) {
                maincourse.setPcalorie(maincourse.getPcalorie() * 0.5 * multiplier);
                maincourse.setPgram(maincourse.getPgram() * 0.5 * multiplier);
            }

            else if((maincourse.pcalorie * 3) <= ((pcalorie * 75) / 100)){
                return dSuggest(oldcalorie, diabetes, multiplier);
            }

            else if ((maincourse.pcalorie * 2) <= ((pcalorie * 75) / 100)){ //((pcalorie - maincourse.pcalorie) + ((maincourse.pcalorie * 25) / 100))) {
                maincourse.setPcalorie(maincourse.getPcalorie() * 2);
                maincourse.setPgram(maincourse.getPgram() * 2);
            }

            pcalorie -= maincourse.getPcalorie();

            soup = suggestionService.rndFood(dinner("GENEL", pcalorie, diabetes, "CORBA"));
            salad = suggestionService.rndFood(dinner("GENEL", pcalorie, diabetes, "SALATA"));
            dessert = suggestionService.rndFood(dinner("GENEL", pcalorie, diabetes, "TATLI"));
            bread = suggestionService.rndFood(dinner("GENEL", pcalorie, diabetes, "EKMEK"));

            //System.out.println("ekmek calorisi: " + bread.getPcalorie());

            while (true) {
                saladrandom = suggestionService.getRandomNumber(1, 2);

                souprandom = suggestionService.getRandomNumber(1, 2);
                souprandom = Math.round(souprandom);

                breadrandom = suggestionService.getRandomNumber(2, 3);
                breadrandom  = Math.round(breadrandom);

                dessertrandom = 1;

                sum = salad.getPcalorie() * multiplier * saladrandom +
                        dessert.getPcalorie() * multiplier * dessertrandom +
                        soup.getPcalorie() * multiplier * souprandom +
                        bread.getPcalorie() * multiplier * breadrandom;

                i++;

                if ((sum + maincourse.getPcalorie()) <= (pcalorie + maincourse.getPcalorie()) &&
                        ((sum + maincourse.getPcalorie()) >= (((((pcalorie +maincourse.getPcalorie()) * 100) / 35) * 34) / 100))) {
                    break;
                }

                if (i > 100) {
                    return dSuggest(oldcalorie, diabetes, multiplier);
                }
            }

            salad.setPgram(salad.getPgram() * multiplier * saladrandom);
            dessert.setPgram(dessert.getPgram() * multiplier * dessertrandom);
            soup.setPgram(soup.getPgram() * multiplier * souprandom);
            bread.setPgram(bread.getPgram() * multiplier * breadrandom);

            salad.setPcalorie(salad.getPcalorie() * multiplier * saladrandom);
            dessert.setPcalorie(dessert.getPcalorie() * multiplier * dessertrandom);
            soup.setPcalorie(soup.getPcalorie() * multiplier * souprandom);
            bread.setPcalorie(bread.getPcalorie() * multiplier * breadrandom);

            System.out.println("AKSAM Gereken kalori: " + (pcalorie + maincourse.pcalorie) +
                    " Toplam: " + (sum + maincourse.pcalorie) + "\n" +
                    " AnaYemek: " + maincourse.getPcalorie() + " Name: " + maincourse.name + " Gram: " + maincourse.getPgram() + "\n" +
                    " Salata: " + salad.getPcalorie() + " rndp: " + saladrandom + " Name: " + salad.name + " Gram: " + salad.getPgram()  + "\n" +
                    " Çorba: " + soup.getPcalorie() + " rnds: " + souprandom + " Name: " + soup.name + " Gram: " + soup.getPgram()  + "\n" +
                    " Tatlı: " + dessert.getPcalorie()  + " rndd: " + dessertrandom + " Name: " + dessert.name + " Gram: " + dessert.getPgram()  + "\n" +
                    " Ekmek: " + bread.getPcalorie() + " rndb: " + breadrandom + " Name: " + bread.name + " Gram: " + bread.getPgram() );

            return new ArrayList<>(Arrays.asList(maincourse, salad, soup, dessert, bread));
        }
        catch (RuntimeException exception){
            throw new RuntimeException("Zaman Aşımı Tekrar Deneyiniz...");
        }
        catch (StackOverflowError exception){
            throw new StackOverflowError("Zaman Aşımı Tekrar Deneyiniz...");
        }
    }

    public List<Foods> dinner(String foodtype, Double pcalorie, Integer diabetes, String types){
        return foodRepository.findByTypeAndPcalorieLessThanEqualAndDiabetesAndFcategories(
                foodTypeRepository.findByName(EFoodType.valueOf(foodtype)),
                pcalorie,
                diabetes,
                foodCategoryRepository.findByName(ECategory.valueOf(types))
        );
    }
}