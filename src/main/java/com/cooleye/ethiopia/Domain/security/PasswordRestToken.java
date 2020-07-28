package com.cooleye.ethiopia.Domain.security;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.cooleye.ethiopia.Domain.User;

@Entity
public class PasswordRestToken {
	
	
	private static final int Expiration=60*24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String token;
	
	@OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
	@JoinColumn(nullable = false,name="user_id")
	private User user;
	
	private Date expiredate;
	
	public PasswordRestToken() {}
	
	public PasswordRestToken(final String token, User user) {
		
		
		  this.token=token;
		  this.user=user;
		  this.expiredate=CalculateExpiredate(Expiration);
	}
	
	





	public long getId() {
		return id;
	}







	public void setId(long id) {
		this.id = id;
	}







	public String getToken() {
		return token;
	}







	public void setToken(String token) {
		this.token = token;
	}







	public User getUser() {
		return user;
	}







	public void setUser(User user) {
		this.user = user;
	}







	public Date getExpiredate() {
		return expiredate;
	}







	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}







	public static int getExpiration() {
		return Expiration;
	}







	private Date CalculateExpiredate(final int expiretimeinminute) {
		// TODO Auto-generated method stub
		
		final Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(cal.MINUTE, expiretimeinminute);
		return new Date(cal.getTime().getTime());
	}
	
	public void updatetoken(final String token) {
		this.token=token;
		this.expiredate=CalculateExpiredate(Expiration);
		
	}

	
	@Override
	public String toString() {
		return "PasswordToken [id=" + id + ", tokken=" + token + ", user=" + user + ", expiredate=" + expiredate + "]";
	}
	
	

}
