package com.rene.library.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rene.library.models.Book;
import com.rene.library.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repo;

	public Book findByUuid(UUID id) {

		Optional<Book> obj = repo.findOneById(id);

		try {
			return obj.get();
		} catch (NoSuchElementException e) {
			return null;
		}

	}

	@Transactional
	public Book insert(Book obj) {
		obj.setId(null);

		return repo.save(obj);
	}

	@Transactional
	public Book update(Book newBook) {

		return repo.save(newBook);
	}

	public List<Book> findAll() {
		return repo.findAll();
	}

}
