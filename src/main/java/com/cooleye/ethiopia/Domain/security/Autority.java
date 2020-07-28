package com.cooleye.ethiopia.Domain.security;

import org.springframework.security.core.GrantedAuthority;

public class Autority implements GrantedAuthority{
	
	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private final String autority;
	
	
	

	public Autority(String autority) {
	
		this.autority = autority;
	}




	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return autority;
	}

}
