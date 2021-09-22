package com.apiuygulama.apiuygulama.service;

import com.apiuygulama.apiuygulama.model.ECategory;
import com.apiuygulama.apiuygulama.model.EFoodType;
import com.apiuygulama.apiuygulama.model.Foods;
import com.apiuygulama.apiuygulama.repository.FoodCategoryRepository;
import com.apiuygulama.apiuygulama.repository.FoodRepository;
import com.apiuygulama.apiuygulama.repository.FoodTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LDSuggestService {

    @Autowired
    @Lazy
    private FoodRepository foodRepository;

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @Autowired
    private SuggestionService suggestionService;

    private Double sum = Double.MAX_VALUE;

    public List<Foods> lSuggest(Double pcalorie, Integer diabetes, Double multiplier){
        try {
            Foods soup, maincourse, salad, dessert, bread;
            double saladrandom, souprandom, dessertrandom, breadrandom;
            Double oldcalorie = pcalorie;
            int i = 1;

            //foodRepository.clear();

            maincourse = suggestionService.rndFood(lunch("GENEL", pcalorie, diabetes, "ANAYEMEK"));

            if (maincourse.pcalorie > ((pcalorie * 75) / 100)) {
                maincourse.setPcalorie(maincourse.getPcalorie() * 0.5 * multiplier);
                maincourse.setPgram(maincourse.getPgram() * 0.5 * multiplier);
            }

            else if((maincourse.pcalorie * 3) <= ((pcalorie * 75) / 100)){
                return lSuggest(oldcalorie, diabetes, multiplier);
            }

            else if ((maincourse.pcalorie * 2) <= ((pcalorie * 75) / 100)){
                maincourse.setPcalorie(maincourse.getPcalorie() * 2);
                maincourse.setPgram(maincourse.getPgram() * 2);
            }

            pcalorie -= maincourse.getPcalorie();

            soup = suggestionService.rndFood(lunch("GENEL", pcalorie, diabetes, "CORBA"));
            salad = suggestionService.rndFood(lunch("GENEL", pcalorie, diabetes, "SALATA"));
            dessert = suggestionService.rndFood(lunch("GENEL", pcalorie, diabetes, "TATLI"));
            bread = suggestionService.rndFood(lunch("GENEL", pcalorie, diabetes, "EKMEK"));

            while (true) {
                if (pcalorie >= (salad.pcalorie + dessert.pcalorie + soup.pcalorie) * 2) {
                    saladrandom = suggestionService.getRandomNumber(1, 2);

                    souprandom = suggestionService.getRandomNumber(1, 2);
                    souprandom = Math.round(souprandom);

                    breadrandom = suggestionService.getRandomNumber(2, 3);
                    breadrandom  = Math.round(breadrandom);

                    dessertrandom = 1;
                } else if (pcalorie >= (salad.pcalorie + dessert.pcalorie + soup.pcalorie)) {
                    saladrandom = suggestionService.getRandomNumber(1, 2);

                    souprandom = suggestionService.getRandomNumber(1, 1.5);
                    souprandom = Math.round(souprandom);

                    breadrandom = suggestionService.getRandomNumber(1, 2);
                    breadrandom  = Math.round(breadrandom);

                    dessertrandom = 1;
                }
                else {
                    saladrandom = suggestionService.getRandomNumber(0.5, 1);

                    souprandom = suggestionService.getRandomNumber(0.5, 1);
                    souprandom = Math.round(souprandom);

                    breadrandom = suggestionService.getRandomNumber(0, 1);
                    breadrandom  = Math.round(breadrandom);

                    dessertrandom = 1;
                }

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
                    return lSuggest(oldcalorie, diabetes, multiplier);
                }
            }

            salad.setPgram(Math.ceil(salad.getPgram() * multiplier * saladrandom));
            dessert.setPgram(Math.ceil(dessert.getPgram() * multiplier * dessertrandom));
            bread.setPgram(Math.ceil(bread.getPgram() * multiplier * breadrandom));
            soup.setPgram(Math.ceil(soup.getPgram() * multiplier * souprandom));

            salad.setPcalorie(Math.ceil(salad.getPcalorie() * multiplier * saladrandom));
            dessert.setPcalorie(Math.ceil(dessert.getPcalorie() * multiplier * dessertrandom));
            bread.setPcalorie(Math.ceil(bread.getPcalorie() * multiplier * breadrandom));
            soup.setPcalorie(Math.ceil(soup.getPcalorie() * multiplier * souprandom));

            System.out.println("Öğle veya Akşam Gereken kalori: " + (pcalorie + maincourse.pcalorie) +
                    " Toplam: " + (sum + maincourse.pcalorie) + "\n" +
                    " AnaYemek: " + maincourse.getPcalorie() + " Name: " + maincourse.name + " Gram: " + maincourse.getPgram() + "\n" +
                    " Salata: " + salad.getPcalorie() + " rndp: " + saladrandom + " Name: " + salad.name + " Gram: " + salad.getPgram()  + "\n" +
                    " Çorba: " + soup.getPcalorie() + " rnds: " + souprandom + " Name: " + soup.name + " Gram: " + soup.getPgram()  + "\n" +
                    " Tatlı: " + dessert.getPcalorie()  + " rndd: " + dessertrandom + " Name: " + dessert.name + " Gram: " + dessert.getPgram()  + "\n" +
                    " Ekmek: " + bread.getPcalorie() + " rndb: " + breadrandom + " Name: " + bread.name + " Gram: " + bread.getPgram() + "\n");

            if (bread.getPcalorie() != 0)
                return new ArrayList<>(Arrays.asList(maincourse, salad, soup, dessert, bread));
            else
                return new ArrayList<>(Arrays.asList(maincourse, salad, soup, dessert));
        }
        catch (RuntimeException exception){
            throw new RuntimeException("Zaman Aşımı Tekrar Deneyiniz...");
        }
        catch (StackOverflowError exception){
            throw new StackOverflowError("Zaman Aşımı Tekrar Deneyiniz...");
        }
    }

    public List<Foods> lunch(String foodtype, Double pcalorie, Integer diabetes, String types){
        return foodRepository.findByTypeAndPcalorieLessThanEqualAndDiabetesAndFcategories(
                foodTypeRepository.findByName(EFoodType.valueOf(foodtype)),
                pcalorie,
                diabetes,
                foodCategoryRepository.findByName(ECategory.valueOf(types))
        );
    }
}
