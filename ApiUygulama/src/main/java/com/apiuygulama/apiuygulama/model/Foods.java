package com.apiuygulama.apiuygulama.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "foods")
@ApiModel(value = "Foods API model documentation", description = "Model")
public class Foods implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(value = "Unique id field of foods object")
    public Integer id;

    @NotBlank
    @ApiModelProperty(value = "UUID field of foods object")
    @JsonIgnore
    public String uuid = String.valueOf(UUID.randomUUID());

    @NotBlank
    @ApiModelProperty(value = "name field of foods object")
    public String name;

    @NotBlank
    @ApiModelProperty(value = "calorie field of foods object")
    public Double pcalorie;

    @NotBlank
    @ApiModelProperty(value = "portion field of foods object")
    public Double pgram;

    @NotBlank
    @ApiModelProperty(value = "diabetes field of foods object")
    public Integer diabetes;

    @NotBlank
    @ApiModelProperty(value = "image_url field of foods object")
    public String image_url;

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
    @Column(columnDefinition = "varchar(5000)")
    @ApiModelProperty(value = "image_url field of foods object")
    public String description;

    @ApiModelProperty(value = "image_url field of foods object")
    public String f_measure;

    @ApiModelProperty(value = "image_url field of foods object")
    public String h_measure;

    @ApiModelProperty(value = "image_url field of foods object")
    public String g_measure;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "category_foods",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @ApiModelProperty(value = "fcategory field of foods object")
    private Set<FoodCategory> fcategories = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "type_food",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    @ApiModelProperty(value = "foodtype field of foods object")
    private Set<FoodType> type = new HashSet<>();

    public Foods(@NotBlank String name, @NotBlank Double pcalorie, @NotBlank Double pgram, @NotBlank Integer diabetes, @NotBlank String image_url, Integer carbohydrate, Integer protein, Integer fat, String description, String f_measure, String h_measure, String g_measure) {
        super();
        this.name = name;
        this.pcalorie = pcalorie;
        this.pgram = pgram;
        this.diabetes = diabetes;
        this.image_url = image_url;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.description= description;
        this.f_measure = f_measure;
        this.h_measure = h_measure;
        this.g_measure = g_measure;
    }
}