package com.apiuygulama.apiuygulama.service;

import com.apiuygulama.apiuygulama.model.Foods;
import com.apiuygulama.apiuygulama.model.JMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

import java.util.List;

@Service
@NoArgsConstructor
public class SuggestionService2 {

    @Autowired
    private SSuggestService sSuggestService;

    @Autowired
    private LDSuggestService ldSuggestService;

    public JMenu createMenu2(Double pcalorie, String cinsiyet, Integer diabetes) {
        List<Foods> atistirmalik, aksamyemegi;

        Double sumCalorie, asum, aysum;
        Double sasum  = (pcalorie * 5) / 100, saysum = (pcalorie * 35) / 100;

        atistirmalik = sSuggestService.sSuggest((pcalorie * 5) / 100, diabetes);
        asum = atistirmalik.stream().map(e -> e.pcalorie).reduce(0.0, Double::sum);

        aksamyemegi = ldSuggestService.lSuggest((pcalorie * 35) / 100, diabetes, 1.0);
        aysum = aksamyemegi.stream().map(e -> e.pcalorie).reduce(0.0, Double::sum);

        sumCalorie = asum + aysum;

        return new JMenu(sumCalorie, asum, aysum, sasum, saysum, atistirmalik, aksamyemegi);
    }
}
