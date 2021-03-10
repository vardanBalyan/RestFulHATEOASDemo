package com.ttn.RestFulHATEOASDemo.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;

//@JsonFilter("userFilter")
@ApiModel(description = "user class contains all the structure of the user")
public class EnhancedUser {

    private int id;
    @Size(min = 3)
    @ApiModelProperty(notes = "name should contains atleast 3 characters")
    private String name;
    @Range(min = 10, max = 105)
    @ApiModelProperty(notes = "age should be within the range of 10 to 105")
    private int age;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String nationality;

    public EnhancedUser(int id, String name, int age, String password,String nationality) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
