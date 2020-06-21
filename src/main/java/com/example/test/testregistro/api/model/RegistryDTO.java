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

    public RegistryDTO(){ }

    public RegistryDTO(long id, String names, String lastNames, String birthday) {
        this.id = id;
        this.names = names;
        this.lastNames = lastNames;
        this.birthday = birthday;
    }
    
}