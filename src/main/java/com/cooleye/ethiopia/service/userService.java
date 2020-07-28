package com.cooleye.ethiopia.service;



import java.util.Set;

import com.cooleye.ethiopia.Domain.User;
import com.cooleye.ethiopia.Domain.security.PasswordRestToken;
import com.cooleye.ethiopia.Domain.security.UserRole;


public interface userService {
	
	public PasswordRestToken getpasswordrestToken(final String token);
	
	
	void creatpasswordResetTokenForUser(final User user,final String token);
	
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	User createUser(User user,Set<UserRole> userroles) throws Exception;


	User Save(User user);
	
	

}
