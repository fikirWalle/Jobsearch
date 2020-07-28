package com.cooleye.ethiopia.service.imp;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooleye.ethiopia.Domain.User;
import com.cooleye.ethiopia.Domain.security.PasswordRestToken;

import com.cooleye.ethiopia.Domain.security.UserRole;
import com.cooleye.ethiopia.repository.RoleRepoastory;
import com.cooleye.ethiopia.repository.UserRepository;

import com.cooleye.ethiopia.repository.passwordresettokkenRepository;
import com.cooleye.ethiopia.service.userService;

@Service
public class UserServiceImp implements userService{
	 
	  private static final Logger LOG = LoggerFactory.getLogger(userService.class);
	@Autowired
	passwordresettokkenRepository passwordtokenrepository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	RoleRepoastory rolerepository;

	

	@Override
	public PasswordRestToken getpasswordrestToken(final String token) {
		// TODO Auto-generated method stub
		return passwordtokenrepository.findByToken(token);
	}

	@Override
	public void creatpasswordResetTokenForUser(User user, String token) {
		// TODO Auto-generated method stub
		
		PasswordRestToken mytoken=new PasswordRestToken(token, user);
		
		passwordtokenrepository.save(mytoken);
		
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userrepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userrepository.findByEmail(email);
	}

	@Override
	
	public User createUser(User user, Set<UserRole> userroles) throws Exception {
		// TODO Auto-generated method stub
		User localuser=userrepository.findByUsername(user.getUsername());
		
		 if(localuser!=null)  {
			
			 LOG.info("User {} Already Exist Nothing will be Done", user.getUsername());
		
		 }
		 
		 else {
			 
			 for(UserRole roles:userroles) {
				 
				 rolerepository.save(roles.getRole());
			 }
			 
			 user.getUserroles().addAll(userroles);
			 localuser=userrepository.save(user);
		 }
		return localuser;
	}

	@Override
	public User Save(User user) {
		// TODO Auto-generated method stub
		return userrepository.save(user);
	}

	
	
}
