package com.apiuygulama.apiuygulama.service;

import org.springframework.stereotype.Service;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CalorieService {

    public Double kaloriHesap(String cinsiyet, Integer kilo, Integer boy, Integer yas, Double aktivite, Double kilotype){
        if (kilotype == 0)
            kilotype = 1.0;
        else if(kilotype == 1)
            kilotype = 0.8;
        else if (kilotype == 2)
            kilotype = 1.2;

        if (cinsiyet.equals("e"))
            return ((66 + (13.7 * kilo) + (5 * boy) - (6.8 * yas)) * aktivite) * kilotype;
        else
            return ((655 + (9.6 * kilo) + (1.8 * boy) - (4.7 * yas)) * aktivite) * kilotype;
    }
}
