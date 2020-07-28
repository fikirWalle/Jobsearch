package com.cooleye.ethiopia.cofig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import com.cooleye.ethiopia.service.imp.Usersecurityservice;
import com.cooleye.ethiopia.utility.SecurityUtility;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigration extends WebSecurityConfigurerAdapter {
	
	
	
	

	private Environment envi;
	
	@Autowired
	private Usersecurityservice usersequrityservice;
	
	
	private BCryptPasswordEncoder passwordEncoder() {
		
		
		
		return SecurityUtility.passwordEncoder();
	}
	
	private static final String[] Public_Muchers= {
			
			"/resources/**",
			"/Employee",
			"./",
			"/myaccount",
			"/crateaccount"
			
			
	};
	/*http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();*/
	@Override
	
	 protected void configure(HttpSecurity http) throws Exception{
		 
		 
		 http.
		 authorizeRequests().
	
		 antMatchers("/css/**").permitAll().antMatchers("/assets/**").permitAll()
		 .antMatchers("/js/**").permitAll().
		 antMatchers("/forms/**").permitAll().antMatchers("/vendor/**").permitAll().
		 //permitAll().
	 antMatchers("/","/newuser","/","/login","/Employee","/forgetpass").
	 permitAll().
	 anyRequest().authenticated();
		 
		
		
		 //permitAll().anyRequest().
		 //authenticated();
		 
		 
		 http.csrf().
		 disable().
		 cors().
		 disable().
		 formLogin().
		 failureUrl("/login?error").
		 defaultSuccessUrl("/")
		 .loginPage("/login").permitAll().
		 and().logout().
		 logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
		 logoutSuccessUrl("/?logout").
		 deleteCookies("/remmember me").permitAll().and().rememberMe();
    

	 }
	
	public void configureGlobal(AuthenticationManagerBuilder auth )throws Exception{
		
		  auth.userDetailsService(usersequrityservice).passwordEncoder(passwordEncoder());
		
		
	}

}
