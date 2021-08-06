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

import com.InHouseMarketPlace.entity.Offer;
import com.InHouseMarketPlace.repository.OfferRepository;

@SpringBootTest
class OfferServiceTest {

	@Autowired
	private OfferService offerService;
	
	@MockBean
	private OfferRepository offerRepository;
	
	@BeforeEach
	void setUp() throws Exception
	{
		Offer o = Offer.builder().offerId(101).availableUpto(LocalDate.now()).build();
		
		Mockito.when(offerRepository.findByOfferId(101)).thenReturn(o);
	}
	
	
	@Test
	@DisplayName("Testing the Offer by offerId")
	void test() {
		int offerId = 101;
		
		Offer found = offerService.getOffer(offerId);
		
		assertEquals(offerId, found.getOfferId());
	}

}
