package com.apiuygulama.apiuygulama.model;

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
@Table(name = "roles")
@ApiModel(value = "Roles API model documentation", description = "Model")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of exercise object")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @ApiModelProperty(value = "name field of roles object")
    private ERole name;
}
