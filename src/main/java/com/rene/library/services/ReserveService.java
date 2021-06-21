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
		
		
		// minus 3 hours
		Instant instantNow = Instant.now().minusSeconds(10800);

		
		book.setReservedAt(instantNow);

		// plus 24 hours
		// book.setExpiration_date(Instant.now().plusSeconds(86400));
		
		
		book.setExpiration_date(instantNow.plusSeconds(60));

		book.setIsExpired("N");

		book.setReservedBy(user);
		user.setReservedBook(book);
		
		
		// Adding 1 to the number of reserves
		book.setNumberOfReserves(book.getNumberOfReserves() + 1);
		
		// Adding 1 to the number of books reserved of the user who has reserved the book
		user.setNumberOfBooksReserved(user.getNumberOfBooksReserved() + 1);
		
		
		// Adding 1 to the number of published books reserved by user who has published the book
		book.getPublishedBy().setNumberOfReservedBooks(book.getPublishedBy().getNumberOfReservedBooks() + 1);
		
		
		
		
		userRepository.save(user);
		bookRepository.save(book);

		return book;
	}

	public Book devolveBook(UUID userId, UUID bookId) {
		Book book = bookService.findByUuid(bookId);
		User user = userService.findByUuid(userId);

		book.setReservedAt(null);
		book.setReservedBy(null);

		book.setDevolvedAt(Instant.now().minusSeconds(10800));

		book.setExpiration_date(null);
		book.setIsExpired("N");

		book.setDevolvedBy(user);
		user.setReservedBook(null);

		userRepository.save(user);
		bookRepository.save(book);

		return book;

	}


}
