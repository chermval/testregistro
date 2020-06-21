package com.example.test.testregistro.api.business;

import com.example.test.testregistro.api.model.RegistryDTO;


public interface AdditionalRegistryService {

    public abstract RegistryDTO getYearsOld(RegistryDTO data);
    public abstract RegistryDTO getComment(RegistryDTO data); 
    public abstract boolean validateData(RegistryDTO data);
}
