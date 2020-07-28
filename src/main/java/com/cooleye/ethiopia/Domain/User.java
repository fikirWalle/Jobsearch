package com.cooleye.ethiopia.Domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cooleye.ethiopia.Domain.security.Autority;
import com.cooleye.ethiopia.Domain.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user", schema = "shining")
public class User implements UserDetails {

	



	/**
	 * 
	 */
	private static final long serialVersionUID = -2872760648949664813L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid" , nullable = false , updatable = false)
	
	 private long id;
	 private String firstName;
	 private String lastName;
	private String username;
	 private String password;
	 
		public void setPassword(String password) {
		this.password = password;
	}


		@Column(name="email" , nullable = false , updatable = false)
	 private String email;
	 private String phone;
	 private boolean enabled=true;
	 
	 @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	 @JsonIgnore
	 private Set<UserRole>userroles=new HashSet<UserRole>();
	 
	
	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<UserRole> getUserroles() {
		return userroles;
	}

	public void setUserroles(Set<UserRole> userroles) {
		this.userroles = userroles;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority>autorities= new HashSet<GrantedAuthority>();
		userroles.forEach(ur->autorities.add(new Autority(ur.getRole().getName())));
		return autorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	






	 
	 
}
