package com.cooleye.ethiopia;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.cooleye.ethiopia.Domain.User;
import com.cooleye.ethiopia.Domain.security.Role;
import com.cooleye.ethiopia.Domain.security.UserRole;
import com.cooleye.ethiopia.service.userService;
import com.cooleye.ethiopia.utility.SecurityUtility;

@SpringBootApplication
@ComponentScan(basePackages={"com.cooleye.ethiopia"})
public class CoolEyeApplication implements CommandLineRunner {

	    @Autowired
	   userService userService;
	   
	public static void main(String[] args) {
		SpringApplication.run(CoolEyeApplication.class, args);
	}

	
	 @Override public void run(String... args) throws Exception { // TODO
	 
	  
	  User user1=new User();
	  
	  user1.setFirstName("Fikir"); 
	  user1.setLastName("Walle");
	  user1.setUsername("strong");
	  user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
	  //user.setPassword(SecurityUtility.passwordEncoder()).encode("p");
	  user1.setEmail("fikirbereketu@gmail.com"); 
	  Set<UserRole>myrole=new HashSet<UserRole>();
	  Role role=new Role(); 
	  role.setRoleId(1);
	  role.setName("Role_User");
	  
  myrole.add(new UserRole(user1, role));
  
	  userService.createUser(user1, myrole);
  
	  
	  
	 }

}
