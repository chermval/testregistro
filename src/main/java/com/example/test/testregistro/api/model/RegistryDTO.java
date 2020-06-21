package com.example.test.testregistro.api.model;

import lombok.Data;

@Data
public class RegistryDTO {

    private long id;
    private String names; 
    private String lastNames;
    private String birthday;
    private int yearsOld;
    private String message;

    
    
}