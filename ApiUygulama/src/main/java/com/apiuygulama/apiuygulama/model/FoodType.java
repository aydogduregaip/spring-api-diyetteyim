package com.apiuygulama.apiuygulama.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import liquibase.pro.packaged.C;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "foodtype")
@ApiModel(value = "Type API model documentation", description = "Model")
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of type object")
    @JsonIgnore
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @ApiModelProperty(value = "name field of type object")
    private EFoodType name;
}
