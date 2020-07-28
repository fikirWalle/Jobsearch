package com.cooleye.ethiopia.repository;

import org.springframework.data.repository.CrudRepository;


import com.cooleye.ethiopia.Domain.User;


public interface UserRepository extends CrudRepository<User, Long>{
	
	
	
	User findByUsername(String username);
	
	
	User findByEmail(String email);

}
