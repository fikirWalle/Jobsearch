package com.cooleye.ethiopia.Domain.security;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooleye.ethiopia.Domain.User;

@Entity
@Table(name="userrole", schema = "shining")
public class UserRole {
	
	
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long userRoleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userfk")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rolefk")
	private Role role;

	public UserRole() {
	
	}



	public UserRole(User user,Role role) {
		this.user=user;
		this.role=role;
	}

	

	public long getUserRoleId() {
		return userRoleId;
	}



	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

}
