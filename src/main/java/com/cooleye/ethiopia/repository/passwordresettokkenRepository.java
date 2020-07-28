package com.cooleye.ethiopia.repository;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.cooleye.ethiopia.Domain.User;
import com.cooleye.ethiopia.Domain.security.PasswordRestToken;


public interface passwordresettokkenRepository extends JpaRepository<PasswordRestToken, Long> {

	
	PasswordRestToken findByToken(String token);
	
	PasswordRestToken findByuser(User user);
	
	Stream<PasswordRestToken>findByExpiredate(Date now);
	
	
	@Modifying
	
	@Query("delete from PasswordRestToken t where t.expiredate<=?1 ")
	 
	void delteAllExpiredate(Date now);
	

}
