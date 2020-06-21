package com.example.test.testregistro.api.business;

import java.util.ArrayList;
import java.util.List;
import com.example.test.testregistro.api.model.RegistryDTO;
import com.example.test.testregistro.api.model.Registry;
import com.example.test.testregistro.api.persistence.RegistryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistryServiceImpl implements RegistryService {

    @Autowired
    private RegistryRepository repository;

    @Override
    public RegistryDTO addRegistry(RegistryDTO data) {

        // create entity for save
        Registry registry = new Registry(data.getNames().toUpperCase(), data.getLastNames().toUpperCase(), data.getBirthday());

        // save data
        registry = repository.save(registry);

        // mapping data saved in DTO
        RegistryDTO registryDTO = new RegistryDTO(registry.getId(), 
                                                registry.getName().trim().split("\\s+")[0],
                                                registry.getLastName().trim().split("\\s+")[0],
                                                registry.getBirthday());

        return registryDTO;
    }

    @Override
    public List<RegistryDTO> allRegistry() {
        List<RegistryDTO> list = new ArrayList<RegistryDTO>();

        // get all data from BD 
        List<Registry> registries = repository.findAll();

         // mapping data saved in DTO
        registries.forEach((Registry registry) -> 
            list.add(new RegistryDTO(registry.getId(), registry.getName() ,
                                    registry.getLastName(), registry.getBirthday() ))
        );


        return list;
    }

}
