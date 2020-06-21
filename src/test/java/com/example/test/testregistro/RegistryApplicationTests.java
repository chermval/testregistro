package com.example.test.testregistro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

		Registry registryBirthday = new Registry();
		registryBirthday.setId(2);
		registryBirthday.setName("JUAN");
		registryBirthday.setLastName("DIAZ");
		registryBirthday.setBirthday(LocalDate.now().toString());

		List<Registry> registries = new ArrayList<>();
		registries.add(registry);
		registries.add(registryBirthday);

		when(registryRepository.save(new Registry("PABLO","PEREZ","1990-01-01") )).thenReturn( registry );
		when(registryRepository.save(new Registry("JUAN","DIAZ",LocalDate.now().toString()) )).thenReturn( registryBirthday );
		when(registryRepository.findAll()).thenReturn(registries);
    }

	@Test
	void testAddRegistryNoBithday(){
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
	void testAddRegistryBithday(){
		RegistryDTO newData = new RegistryDTO();
		newData.setNames("JUAN");
		newData.setLastNames("DIAZ");
		newData.setBirthday(LocalDate.now().toString());

		RegistryDTO newDataExpected = new RegistryDTO();
		newDataExpected.setId(2);
		newDataExpected.setNames("JUAN");
		newDataExpected.setLastNames("DIAZ");
		newDataExpected.setBirthday(LocalDate.now().toString());

		assertEquals(newDataExpected, registryService.addRegistry(newData));
	}

	@Test
	void  testAllRegistry(){
		RegistryDTO newDataExpected = new RegistryDTO();
		newDataExpected.setId(1);
		newDataExpected.setNames("PABLO");
		newDataExpected.setLastNames("PEREZ");
		newDataExpected.setBirthday("1990-01-01");

		RegistryDTO newDataExpectedBirthday = new RegistryDTO();
		newDataExpectedBirthday.setId(2);
		newDataExpectedBirthday.setNames("JUAN");
		newDataExpectedBirthday.setLastNames("DIAZ");
		newDataExpectedBirthday.setBirthday(LocalDate.now().toString());

		List<RegistryDTO> newDataExpectedList = new ArrayList<>();
		newDataExpectedList.add(newDataExpected);
		newDataExpectedList.add(newDataExpectedBirthday);

		assertEquals(newDataExpectedList, registryService.allRegistry());
	}

	

}
