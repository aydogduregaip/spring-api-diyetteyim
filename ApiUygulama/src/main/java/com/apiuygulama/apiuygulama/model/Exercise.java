package com.apiuygulama.apiuygulama.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise")
@ApiModel(value = "Exercise API model documentation", description = "Model")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Unique id field of exercise object")
    public Integer id;

    @NotBlank
    @ApiModelProperty(value = "UUID field of exercise object")
    public String uuid = String.valueOf(UUID.randomUUID());

    @NotBlank
    @ApiModelProperty(value = "name field of exercise object")
    public String name;

    @NotBlank
    @ApiModelProperty(value = "calorie field of exercise object")
    public Double calorie;

    @NotBlank
    @ApiModelProperty(value = "image_url field of exercise object")
    public String image_url;

    public Exercise(@NotBlank String name, @NotBlank Double calorie, @NotBlank String image_url) {
        this.name = name;
        this.calorie = calorie;
        this.image_url = image_url;
    }
}
