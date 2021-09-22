package com.apiuygulama.apiuygulama.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class JMenu implements Serializable {
    public Double sumCalorie;
    public Double ksum;
    public Double basum;
    public Double osum;
    public Double asum;
    public Double aysum;

    public Double sksum;
    public Double sbasum;
    public Double sosum;
    public Double sasum;
    public Double saysum;

    public List<Foods> kahvalti;
    public List<Foods> batistirmalik;
    public List<Foods> ogleyemegi;
    public List<Foods> aatistirmalik;
    public List<Foods> aksamyemegi;

    public JMenu(@NotBlank Double sumCalorie,
                 @NotBlank Double ksum,
                 @NotBlank Double basum,
                 @NotBlank Double osum,
                 @NotBlank Double sksum,
                 @NotBlank Double sbasum,
                 @NotBlank Double sosum,

                 @NotBlank List<Foods> kahvalti,
                 @NotBlank List<Foods> atistirmalik,
                 @NotBlank List<Foods> ogleyemegi) {
        this.sumCalorie = sumCalorie;
        this.ksum = ksum;
        this.basum = basum;
        this.osum = osum;
        this.sksum = sksum;
        this.sbasum = sbasum;
        this.sosum = sosum;

        this.kahvalti = kahvalti;
        this.batistirmalik = atistirmalik;
        this.ogleyemegi = ogleyemegi;
    }

    public JMenu(@NotBlank Double sumCalorie,
                 @NotBlank Double asum,
                 @NotBlank Double aysum,
                 @NotBlank Double sasum,
                 @NotBlank Double saysum,

                 @NotBlank List<Foods> atistirmalik2,
                 @NotBlank List<Foods> aksamyemegi) {
        this.sumCalorie = sumCalorie;
        this.asum = asum;
        this.aysum = aysum;
        this.sasum = sasum;
        this.saysum = saysum;

        this.aatistirmalik = atistirmalik2;
        this.aksamyemegi = aksamyemegi;
    }

}
