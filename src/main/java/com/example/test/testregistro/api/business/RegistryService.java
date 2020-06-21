package com.example.test.testregistro.api.business;

import java.util.List;
import com.example.test.testregistro.api.model.RegistryDTO;


public interface RegistryService {

    public abstract RegistryDTO addRegistry(RegistryDTO data);
    public abstract List<RegistryDTO> allRegistry();

}
