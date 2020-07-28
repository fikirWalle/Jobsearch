package com.cooleye.ethiopia.utility;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.cooleye.ethiopia.Domain.User;

@Component
public class Mailconstractor {
	
	@Autowired
	private Environment envi;
	
	
	
	public SimpleMailMessage ConstractResetTokenEmail(String contextpath, Locale locale,
			    String token,User user,String password) {
		
		           String url=contextpath + "/newuser?token="+token;
		           String message ="\n please click on this link to verify your email and e"
		           		+ "dit your personal information your password is : \n" +password;
		           
		           SimpleMailMessage email=new SimpleMailMessage();
		
		             email.setTo(user.getEmail());
		                email.setSubject("Shining Center new User");
		                email.setText(url+message);
		                email.setFrom(envi.getProperty("support.email"));
		                
		                return email;
	}

}
