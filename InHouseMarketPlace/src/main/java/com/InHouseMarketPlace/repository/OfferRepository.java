package com.InHouseMarketPlace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.InHouseMarketPlace.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
	
	List<Offer> findByCategoryAndType(String category, String type);
	@Query("select o from Offer o where offer_id=?1")
	public Offer findByOfferId(int offerId);
	 
	
}