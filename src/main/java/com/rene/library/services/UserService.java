package com.rene.library.services;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rene.library.models.User;
import com.rene.library.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public User findByUuid(UUID id) {

		Optional<User> obj = repo.findOneById(id);

		try {
			return obj.get();
		} catch (NoSuchElementException e) {
			return null;
		}

	}

	@Transactional
	public User insert(User obj) {
		obj.setId(null);
		
	
		obj.setCreatedDate(Instant.now().minusSeconds(10800));

		return repo.save(obj);
	}

	@Transactional
	public User update(User newUser) {

		return repo.save(newUser);
	}

	public List<User> findAll() {
		return repo.findAll();
	}

}
