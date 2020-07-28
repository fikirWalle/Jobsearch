package com.cooleye.ethiopia.repository;

import org.springframework.data.repository.CrudRepository;

import com.cooleye.ethiopia.Domain.security.Role;

public interface RoleRepoastory extends CrudRepository<Role, Long>{

	    Role findByName(String name);
	    
	   
}
