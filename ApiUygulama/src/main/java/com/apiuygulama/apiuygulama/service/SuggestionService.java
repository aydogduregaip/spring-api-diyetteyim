package com.apiuygulama.apiuygulama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiuygulama.apiuygulama.model.*;

import java.util.*;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SuggestionService {

    @Autowired
    private BSuggestService bSuggestService;

    @Autowired
    private SSuggestService sSuggestService;

    @Autowired
    private LDSuggestService ldSuggestService;

    @Autowired
    private DSuggestService dSuggestService;

    private final Random rnd = new Random();

    public JMenu createMenu(Double pcalorie, String cinsiyet, Integer diabetes) {
        List<Foods> kahvalti, araogun, ogleyemegi;

        Double sumCalorie, ksum, basum, osum;
        Double sksum = (pcalorie * 20) / 100, sbasum = (pcalorie * 5) / 100, sosum = (pcalorie * 35) / 100;

        kahvalti = bSuggestService.bSuggest((pcalorie * 20) / 100, diabetes, 1.0);
        ksum = kahvalti.stream().map(e -> e.pcalorie).reduce(0.0, Double::sum);

        araogun = sSuggestService.sSuggest((pcalorie * 5) / 100, diabetes);
        basum =  araogun.stream().map(e -> e.pcalorie).reduce(0.0, Double::sum);

        ogleyemegi = ldSuggestService.lSuggest((pcalorie * 35) / 100, diabetes, 1.0);
        osum = ogleyemegi.stream().map(e -> e.pcalorie).reduce(0.0, Double::sum);

        sumCalorie = ksum + basum + osum;

        return new JMenu(sumCalorie, ksum, basum, osum, sksum, sbasum, sosum,
                kahvalti, araogun, ogleyemegi);
    }

    public Foods rndFood(List<Foods> foods) {
        return foods.get(rnd.nextInt(foods.size()));
    }

    public double getRandomNumber(double minimum, double maximum){
        return Math.round((rnd.nextDouble() * (maximum - minimum) + minimum) * 1e1) / 1e1;
    }
}