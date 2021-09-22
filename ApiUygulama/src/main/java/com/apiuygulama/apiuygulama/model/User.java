package com.apiuygulama.apiuygulama.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "username")
        })
@ApiModel(value = "User API model documentation", description = "Model")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of user object")
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "UUID field of user object")
    public String uuid= String.valueOf(UUID.randomUUID());

    @NotBlank
    @Size(max = 20)
    @ApiModelProperty(value = "username field of user object")
    private String username;

    @NotBlank
    @Size(max = 20)
    @ApiModelProperty(value = "first_name field of user object")
    private String first_name;

    @NotBlank
    @Size(max = 20)
    @ApiModelProperty(value = "last_name field of user object")
    private String last_name;

    @NotBlank
    @Size(max = 50)
    @Email
    @ApiModelProperty(value = "email field of user object")
    private String email;

    @NotBlank
    @Size(max = 60)
    @ApiModelProperty(value = "password field of user object")
    @JsonIgnore
    private String password;

    @NotBlank
    @ApiModelProperty(value = "createdate field of user object")
    @JsonIgnore
    private Date createdate = new Date();

    @ApiModelProperty(value = "resetPasswordToken field of user object")
    @JsonIgnore
    private String resetPasswordToken;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ApiModelProperty(value = "roles field of user object")
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password, String first_name, String last_name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
