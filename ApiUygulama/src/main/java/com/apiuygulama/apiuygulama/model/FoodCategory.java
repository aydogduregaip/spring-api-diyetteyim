package com.apiuygulama.apiuygulama.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "foodcategory")
@ApiModel(value = "FoodCategory API model documentation", description = "Model")
public class FoodCategory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(value = "Unique id field of foodcategory object")
    @JsonIgnore
    public Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @ApiModelProperty(value = "name field of foodcategory object")
    private ECategory name;
}
