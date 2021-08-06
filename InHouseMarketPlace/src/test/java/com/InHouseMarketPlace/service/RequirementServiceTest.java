package com.InHouseMarketPlace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.InHouseMarketPlace.entity.Requirement;
import com.InHouseMarketPlace.repository.RequirementRepository;

@SpringBootTest
class RequirementServiceTest {
	
	@Autowired
	private RequirementService requirementService;
	
	@MockBean
	private RequirementRepository requirementRepository;
	
	

	@BeforeEach
	void setUp() throws Exception {
		
		Requirement r =  Requirement.builder().reqId(0).fulfilledOn(LocalDate.now()).build();
		
		Mockito.when(requirementRepository.findByRequirementId(0)).thenReturn(r);
		
		
	}

	@Test
	@DisplayName("testing the requirement by id")
	void findByRequirementIdTest() {
		
		int reqId = 0;
		
		Requirement require = requirementService.getRequirement(reqId);
		
		assertEquals(reqId, require.getReqId());
		
		
	}


}
