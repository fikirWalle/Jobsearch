package com.cooleye.ethiopia.controller;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cooleye.ethiopia.Domain.User;
import com.cooleye.ethiopia.Domain.security.PasswordRestToken;
import com.cooleye.ethiopia.Domain.security.Role;
import com.cooleye.ethiopia.Domain.security.UserRole;
import com.cooleye.ethiopia.service.userService;
import com.cooleye.ethiopia.service.imp.Usersecurityservice;
import com.cooleye.ethiopia.utility.Mailconstractor;
import com.cooleye.ethiopia.utility.SecurityUtility;

@Controller
public class HomeController {

	@Autowired
	userService userservice;

	@Autowired
	Usersecurityservice Usersecurityservice;

	@Autowired
	JavaMailSender mailsender;

	@Autowired
	Mailconstractor mailconstractor;

	@RequestMapping("/")

	public String index() {

		return "index";
	}



//	@RequestMapping("/crateaccount")
//	public String crateaccount() {
////		public String crateaccount(Local local ,@RequestParam("token") String
////		 token,Model model) {
//
////  	  model.addAttribute("classactiveaccount",true);
//
//		return "crateaccount";
//	}
 @RequestMapping("/login")
	   public String login(@Validated  Model model) {
		  
	 model.addAttribute("classactivelogin",true);
	 
		return "crateaccount";
		   
	   }
 
 @RequestMapping(value="/forgetpass" ,method = RequestMethod.POST)
 public String forgetpassword(HttpServletRequest request,@Validated
		 
			@ModelAttribute("email") String useremail, Model model)  {
	  
       model.addAttribute("classactiveforget",true);

   User user=userservice.findByEmail(useremail);
   
   
    if (user== null) {

	model.addAttribute("emailNotExists", true);

	return "crateaccount";
    }
    


	String password = SecurityUtility.randomepassword();
	String encryptedpassword = SecurityUtility.passwordEncoder().encode(password);
	user.setPassword(encryptedpassword);

	userservice.Save(user);

	String token = UUID.randomUUID().toString();
	userservice.creatpasswordResetTokenForUser(user, token);

	String appUri = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

	SimpleMailMessage newemail = mailconstractor.ConstractResetTokenEmail(appUri, request.getLocale(), token, user,
			password);
	mailsender.send(newemail);
	// mailSender..send(email);

	   
	model.addAttribute("forgetpassword", true);



	return "crateaccount";
	   
 }
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)

	public String newuserpost(HttpServletRequest request,@Validated
			@ModelAttribute("username") String username,
			@ModelAttribute("email") String useremail,
			Model model) throws Exception {

		 model.addAttribute("classactivenewuser",true);
		model.addAttribute("email", useremail);
		model.addAttribute("username", username);

		if (userservice.findByUsername(username)!= null) {

			model.addAttribute("usernameExists",true);

			return "crateaccount";
		}

		if (userservice.findByEmail(useremail) != null) {

			model.addAttribute("emailExists", true);

			return "crateaccount";
		}
		User user = new User();
		user.setUsername(username);
		user.setEmail(useremail);

		String password = SecurityUtility.randomepassword();
		String encryptedpassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedpassword);

		Role role = new Role();
		role.setRoleId(1);
		role.setName("Role_User");
		Set<UserRole> userroles = new HashSet<UserRole>();
		userroles.add(new UserRole(user, role));
		userservice.createUser(user, userroles);

		String token = UUID.randomUUID().toString();
		userservice.creatpasswordResetTokenForUser(user, token);

		String appUri = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		SimpleMailMessage email = mailconstractor.ConstractResetTokenEmail(appUri, request.getLocale(), token, user,
				password);
		mailsender.send(email);
		// mailSender..send(email);

		   
		model.addAttribute("emailsent", true);
		
		  if(true) {
			  
			  model.addAttribute("sucessfully register");
			  
		  }
		  
		  return "crateaccount";
		
	}

	@RequestMapping("/newuser")
	public String newuser(Locale locale, 
			@RequestParam("token") 
	String token, 
	Model model) {

//  	  model.addAttribute("classactiveaccount",true);
		PasswordRestToken passwordresettoken = userservice.getpasswordrestToken(token);

		if (passwordresettoken == null) {

			String message = "Invalid Token";
			model.addAttribute("message", message);
			return "redirect:/badRequest";
		}
		User user = passwordresettoken.getUser();
		String username = user.getUsername();
		//
		// begining of code to make sure the current user is still active
		UserDetails userDetails = Usersecurityservice.loadUserByUsername(username);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, 
				userDetails.getPassword(),
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		model.addAttribute("user",user);
		model.addAttribute("classactiveEdit",true);
		return "myprofile";
	}



	@RequestMapping("/already")
	public String emp(Model model) {

//  	  model.addAttribute("classactivereset",true);

		return "try";
	}

}
