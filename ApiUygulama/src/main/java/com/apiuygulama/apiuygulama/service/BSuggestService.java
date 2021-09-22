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
public class BSuggestService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @Autowired
    private SuggestionService suggestionService;

    private Double sum = Double.MAX_VALUE;

    public List<Foods> bSuggest(Double pcalorie, Integer diabetes, Double multiplier) {
        try {
            Foods icecek, anayemek, peynir, zeytin, kahvaltilik_sebze;
            double peynirrandom, zeytinrandom, sebzerandom;
            Double oldcalorie = pcalorie;
            int i = 1;

            anayemek = suggestionService.rndFood(breakfast("KAHVALTI", pcalorie, diabetes, "ANAYEMEK"));

            if (anayemek.pcalorie > ((pcalorie * 85) / 100))
                anayemek.setPcalorie(anayemek.getPcalorie() * 0.5 * multiplier);

            else if ((anayemek.pcalorie * 3) < ((pcalorie - anayemek.pcalorie) + ((anayemek.pcalorie * 15) / 100)))
                anayemek.setPcalorie(anayemek.getPcalorie() * 3);

            else if ((anayemek.pcalorie * 2) < ((pcalorie - anayemek.pcalorie) + ((anayemek.pcalorie * 15) / 100)))
                anayemek.setPcalorie(anayemek.getPcalorie() * 2);

            pcalorie -= anayemek.pcalorie;

            while (true) {
                icecek = suggestionService.rndFood(breakfast("KAHVALTI", pcalorie, diabetes, "ICECEK"));
                peynir = suggestionService.rndFood(breakfast("KAHVALTI", pcalorie, diabetes, "PEYNIR"));
                zeytin = suggestionService.rndFood(breakfast("KAHVALTI", pcalorie, diabetes, "ZEYTIN"));
                kahvaltilik_sebze = suggestionService.rndFood(breakfast("KAHVALTI", pcalorie, diabetes, "KAHVALTILIK_SEBZE"));

                peynirrandom = suggestionService.getRandomNumber(0.5, 3);
                zeytinrandom = suggestionService.getRandomNumber(0.5, 3);
                sebzerandom = suggestionService.getRandomNumber(0.5, 2);

                sum = icecek.getPcalorie() +
                        peynir.getPcalorie() * multiplier * peynirrandom +
                        zeytin.getPcalorie() * multiplier * zeytinrandom +
                        kahvaltilik_sebze.getPcalorie() * multiplier * sebzerandom;
                i++;

                if ((sum <= pcalorie) && (sum >= ((((pcalorie * 100) / 20) * 19) / 100))) {
                    break;
                }

                if (i > 100) {
                    return bSuggest(oldcalorie, diabetes, multiplier);
                }
            }

            System.out.println("Gereken kalori1: " + (pcalorie + anayemek.pcalorie) +
                    " Toplam: " + (sum + anayemek.pcalorie) + "\n" +
                    " İcecek: " + icecek.getPcalorie() + " Name: " + icecek.name + " Gram: " + icecek.pgram + "\n" +
                    " AnaYemek: " + anayemek.getPcalorie() + " Name: " + anayemek.name + " Gram: " + anayemek.pgram + "\n" +
                    " Peynir: " + peynir.getPcalorie() * multiplier * peynirrandom + " rndp: " + peynirrandom + " Name: " + peynir.name + " Gram: " + peynir.pgram * multiplier * peynirrandom + "\n" +
                    " Zeytin: " + zeytin.getPcalorie() * multiplier * zeytinrandom + " rndz: " + zeytinrandom + " Name: " + zeytin.name + " Gram: " + zeytin.pgram * multiplier * zeytinrandom + "\n" +
                    " Kahvaltılık Sebze: " + kahvaltilik_sebze.getPcalorie() * multiplier * sebzerandom + " rnds: " + sebzerandom + " Name: " + kahvaltilik_sebze.name + " Gram: " + kahvaltilik_sebze.pgram * multiplier * sebzerandom + "\n");

            icecek.setPgram(icecek.getPgram());
            peynir.setPgram(Math.ceil(peynir.getPgram() * multiplier * peynirrandom));
            zeytin.setPgram(Math.ceil(zeytin.getPgram() * multiplier * zeytinrandom));
            kahvaltilik_sebze.setPgram(Math.ceil(kahvaltilik_sebze.getPgram() * multiplier * sebzerandom));

            icecek.setPcalorie(icecek.getPcalorie());
            peynir.setPcalorie(Math.ceil(peynir.getPcalorie() * multiplier * peynirrandom));
            zeytin.setPcalorie(Math.ceil(zeytin.getPcalorie() * multiplier * zeytinrandom));
            kahvaltilik_sebze.setPcalorie(Math.ceil(kahvaltilik_sebze.getPcalorie() * multiplier * sebzerandom));

            return new ArrayList<>(Arrays.asList(icecek, anayemek, peynir, zeytin, kahvaltilik_sebze));
        } catch (RuntimeException exception) {
            throw new RuntimeException("Zaman Aşımı Tekrar Deneyiniz...");
        } catch (StackOverflowError exception) {
            throw new StackOverflowError("Zaman Aşımı Tekrar Deneyiniz...");
        }
    }

    public List<Foods> breakfast(String foodtype, Double pcalorie, Integer diabetes, String types){
        return foodRepository.findByTypeAndPcalorieLessThanEqualAndDiabetesAndFcategories(
                foodTypeRepository.findByName(EFoodType.valueOf(foodtype)),
                pcalorie,
                diabetes,
                foodCategoryRepository.findByName(ECategory.valueOf(types))
        );
    }
}
