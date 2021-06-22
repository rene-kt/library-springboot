package com.rene.library.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rene.library.models.User;
import com.rene.library.repositories.UserRepository;
import com.rene.library.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repo.findByUsername(username);

			UserSS userSS = new UserSS();
			
			userSS.setId(user.getId());
			userSS.setUsername(user.getUsername());
			userSS.setPassword(user.getPassword());
			userSS.setAuthorities(Arrays.asList("User"));

			
			return userSS;
		
	}

}
