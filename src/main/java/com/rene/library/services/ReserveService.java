package com.rene.library.services;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rene.library.models.Book;
import com.rene.library.models.User;
import com.rene.library.repositories.BookRepository;
import com.rene.library.repositories.UserRepository;

@Service
public class ReserveService {

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	public Book reserveBook(UUID userId, UUID bookId) {

		Book book = bookService.findByUuid(bookId);
		User user = userService.findByUuid(userId);

		book.setReservedAt(Instant.now().minusSeconds(10800));

		book.setReservedBy(user);
		user.setReservedBook(book);

		userRepository.save(user);
		bookRepository.save(book);

		return book;
	}
	
	public Book devolveBook(UUID userId, UUID bookId) {
		Book book = bookService.findByUuid(bookId);
		User user = userService.findByUuid(userId);

		book.setReservedAt(null);

		book.setReservedBy(null);
		user.setReservedBook(null);

		userRepository.save(user);
		bookRepository.save(book);
		
		return book;

	}
}
