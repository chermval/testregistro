package com.example.test.testregistro.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Registry {

    @Id @GeneratedValue(strategy=GenerationType.AUTO) 
    private long id;
    private String name; 
    private String lastName;
    private String birthday;

    public Registry() { }

    public Registry(String name, String lastName, String birthday) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
    }

}