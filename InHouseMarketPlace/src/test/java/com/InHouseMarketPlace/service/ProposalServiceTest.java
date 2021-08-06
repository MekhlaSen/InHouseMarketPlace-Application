package com.InHouseMarketPlace.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.InHouseMarketPlace.entity.Offer;
import com.InHouseMarketPlace.entity.Proposal;
import com.InHouseMarketPlace.repository.OfferRepository;
import com.InHouseMarketPlace.repository.ProposalRepository;

@SpringBootTest
class ProposalServiceTest {
	

	@Autowired
	private ProposalService proposalService;
	
	@MockBean
	private ProposalRepository proposalRepository;
	
	@BeforeEach
	void setUp() throws Exception
	{
		Proposal p = Proposal.builder().propId(0).proposal("abcd").proposalDate(LocalDate.now()).acceptedOn(LocalDate.now()).build();
		
		Mockito.when(proposalRepository.findByProposalId(0)).thenReturn(p);
	}

	@Test
	@DisplayName("Testing the Proposal by propId")
	void test() {
		int propId = 0;
		
		Proposal found = proposalService.getProposal(propId);
		
		assertEquals(propId, found.getPropId());
	}
}
