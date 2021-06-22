package com.rene.library.services;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rene.library.models.User;
import com.rene.library.models.UserDTO;
import com.rene.library.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encode;

	 
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
		
		obj.setPassword(encode.encode(obj.getPassword()));
		

		return repo.save(obj);
	}

	@Transactional
	public User update(User newUser) {

		return repo.save(newUser);
	}

	public List<User> findAll() {
		return repo.findAll();
	}

	public UserDTO returnUserDTO(UUID userId) {

		User user = findByUuid(userId);

		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setUsername(user.getUsername());
		dto.setCreatedDate(user.getCreatedDate());
		dto.setNumberOfBooksReserved(user.getNumberOfBooksReserved());
		dto.setNumberOfReservedBooks(user.getNumberOfReservedBooks());
		dto.setCurrentReservedBook(user.getCurrentReservedBook());
		dto.setReservedBooks(user.getReservedBooks());
		dto.setPublishedBooks(user.getPublishedBooks());
		dto.setLastDevolvedBook(user.getLastDevolvedBook());

		return dto;

	}

}
