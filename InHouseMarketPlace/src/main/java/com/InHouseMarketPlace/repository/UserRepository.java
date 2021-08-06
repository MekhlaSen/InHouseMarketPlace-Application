package com.InHouseMarketPlace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.InHouseMarketPlace.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

	@Query("select u from Users u where user_id=?1 and password=?2")
	public Users login(String userId, String password);

	
}
