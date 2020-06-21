package com.example.test.testregistro.api.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.test.testregistro.api.model.Registry;

public interface RegistryRepository extends JpaRepository<Registry, Long>{
  
}