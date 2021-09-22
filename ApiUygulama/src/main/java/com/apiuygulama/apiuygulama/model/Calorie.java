package com.apiuygulama.apiuygulama.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

    @Data
    @Entity
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Calorie API model documentation", description = "Model")
public class Calorie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(value = "Unique id field of calorie object")
    private Integer id;
    @ApiModelProperty(value = "cinsiyet field of calorie object")
    private String cinsiyet;

    @ApiModelProperty(value = "kilo field of calorie object")
    private Integer kilo;

    @ApiModelProperty(value = "boy field of calorie object")
    private Integer boy;

    @ApiModelProperty(value = "yas field of calorie object")
    private Integer yas;

    @ApiModelProperty(value = "aktivite field of calorie object")
    private Double aktivite;
}
