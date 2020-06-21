package com.example.test.testregistro.api.controller;

import java.util.List;

import com.example.test.testregistro.api.business.RegistryService;
import com.example.test.testregistro.api.business.AdditionalRegistryService;
import com.example.test.testregistro.api.model.RegistryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
@EnableAutoConfiguration
public class RegistryResource {

    @Autowired
    private RegistryService services;

    @Autowired
    private AdditionalRegistryService additionalServices;

    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/v1/registries", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RegistryDTO addRegistry( @RequestBody RegistryDTO data){

        //validate data 
        if ( !additionalServices.validateData(data) ){
            throw new ResponseStatusException (HttpStatus.UNPROCESSABLE_ENTITY , "Request con datos incorrectos, no se puedo crear registro");
        }
        //call method to add registries
        data = services.addRegistry(data);

        // call method to calculate years old
        data = additionalServices.getYearsOld(data);

        // call method to get comment about birthday
        data = additionalServices.getComment(data);

        return data;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/v1/registries")
    @ResponseStatus(HttpStatus.OK)
    public List<RegistryDTO> allRegistry(){
        
        //call method to get all registries
        List<RegistryDTO> registries = services.allRegistry();
    
        registries.stream().forEach((RegistryDTO data) -> {
            // call method to calculate years old
            data = additionalServices.getYearsOld(data);
            // call method to get comment about birthday
            data = additionalServices.getComment(data);
        } );
         

        return registries;
    }

}
