package com.example.test.testregistro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.test.testregistro.api.business.RegistryService;
import com.example.test.testregistro.api.business.RegistryServiceImpl;
import com.example.test.testregistro.api.model.Registry;
import com.example.test.testregistro.api.model.RegistryDTO;
import com.example.test.testregistro.api.persistence.RegistryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class RegistryApplicationTests {

	@Mock
	private RegistryRepository registryRepository;

	@InjectMocks // auto inject registryRepository
    private RegistryService registryService = new RegistryServiceImpl();

	@BeforeEach
    void setMockOutput() {
		Registry registry = new Registry();
		registry.setId(1);
		registry.setName("PABLO");
		registry.setLastName("PEREZ");
		registry.setBirthday("1990-01-01");

		List<Registry> registries = new ArrayList<>();
		registries.add(registry);

		when(registryRepository.save(new Registry("PABLO","PEREZ","1990-01-01") )).thenReturn( registry );
		when(registryRepository.findAll()).thenReturn(registries);
    }

	@Test
	void testAddRegistry(){
		RegistryDTO newData = new RegistryDTO();
		newData.setNames("PABLO");
		newData.setLastNames("PEREZ");
		newData.setBirthday("1990-01-01");

		RegistryDTO newDataExpected = new RegistryDTO();
		newDataExpected.setId(1);
		newDataExpected.setNames("PABLO");
		newDataExpected.setLastNames("PEREZ");
		newDataExpected.setBirthday("1990-01-01");

		assertEquals(newDataExpected, registryService.addRegistry(newData));
	}

	@Test
	void  testAllRegistry(){
		RegistryDTO newDataExpected = new RegistryDTO();
		newDataExpected.setId(1);
		newDataExpected.setNames("PABLO");
		newDataExpected.setLastNames("PEREZ");
		newDataExpected.setBirthday("1990-01-01");

		List<RegistryDTO> newDataExpectedList = new ArrayList<>();
		newDataExpectedList.add(newDataExpected);

		assertEquals(newDataExpectedList, registryService.allRegistry());
	}

	

}
