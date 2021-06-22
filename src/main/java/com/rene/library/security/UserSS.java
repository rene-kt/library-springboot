package com.rene.library.security;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSS implements UserDetails {

	
	
	private static final long serialVersionUID = 1L;
	private UUID id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserSS() {
		
	}
	
	

	public UserSS(UUID id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	


	public UUID getId() {
		return id;
	}
	
	


	public String getEmail() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setAuthorities(List<String> profiles) {
		this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.toString())).collect(Collectors.toList());

	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
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
		return true;
	}

}