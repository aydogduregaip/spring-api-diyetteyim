package com.apiuygulama.apiuygulama.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class FoodRequest {
    @NotBlank
    @ApiModelProperty(value = "name field of user object")
    public String name;

    @NotBlank
    @ApiModelProperty(value = "calorie field of user object")
    public Double pcalorie;

    @NotBlank
    @ApiModelProperty(value = "portion field of user object")
    public Double pgram;

    @NotBlank
    @ApiModelProperty(value = "diabetes field of user object")
    public Integer diabetes;

    @NotBlank
    @ApiModelProperty(value = "image_url field of user object")
    public String image_url;

    @NotBlank
    @ApiModelProperty(value = "category field of foods object")
    private Set<String> fcategory;

    @NotBlank
    @ApiModelProperty(value = "type field of foods object")
    private Set<String> ftype;

    @NotBlank
    @ApiModelProperty(value = "carbohydrate field of foods object")
    public Integer carbohydrate;

    @NotBlank
    @ApiModelProperty(value = "protein field of foods object")
    public Integer protein;

    @NotBlank
    @ApiModelProperty(value = "fat(yag) field of foods object")
    public Integer fat;

    @NotBlank
    @ApiModelProperty(value = "description field of foods object")
    public String description;

    @ApiModelProperty(value = "f_measure field of foods object")
    public String f_measure;

    @ApiModelProperty(value = "h_measure field of foods object")
    public String h_measure;

    @ApiModelProperty(value = "g_measure field of foods object")
    public String g_measure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPcalorie() {
        return pcalorie;
    }

    public void setPcalorie(Double pcalorie) {
        this.pcalorie = pcalorie;
    }

    public Double getPgram() {
        return pgram;
    }

    public void setPgram(Double pgram) {
        this.pgram = pgram;
    }

    public Integer getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Integer diabetes) {
        this.diabetes = diabetes;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Set<String> getFcategory() {
        return fcategory;
    }

    public void setFcategory(Set<String> fcategory) {
        this.fcategory = fcategory;
    }

    public Set<String> getFtype() {
        return ftype;
    }

    public void setFtype(Set<String> ftype) {
        this.ftype = ftype;
    }

    public Integer getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Integer carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getF_measure() {
        return f_measure;
    }

    public void setF_measure(String f_measure) {
        this.f_measure = f_measure;
    }

    public String getH_measure() {
        return h_measure;
    }

    public void setH_measure(String h_measure) {
        this.h_measure = h_measure;
    }

    public String getG_measure() {
        return g_measure;
    }

    public void setG_measure(String g_measure) {
        this.g_measure = g_measure;
    }
}
