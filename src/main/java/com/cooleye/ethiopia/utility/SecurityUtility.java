package com.cooleye.ethiopia.utility;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtility {
	
	
	private static final String SALT="salt";// salt should be protected carefully
	
	
	  @Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		  
		  return new BCryptPasswordEncoder(12 ,new SecureRandom(SALT.getBytes()));
		
		
		
	}
	  @Bean
	  
	  public static String randomepassword() {
		  
		   String SALTCHRS="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		   
		   StringBuilder salt=new StringBuilder();
		   
		   Random rand=new Random();
		   
		    while(salt.length()<18) {
		    	
		    	int index=(int) (rand.nextFloat()*SALTCHRS.length());
		    	salt.append(SALTCHRS.charAt(index));
		    	
		    }
		    String saltstr=salt.toString();
		    
		    return saltstr;
		  
		  
	  }

}
