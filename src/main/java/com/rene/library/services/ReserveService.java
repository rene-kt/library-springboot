package com.rene.library.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rene.library.models.Book;
import com.rene.library.models.User;

@Service
public class ReserveService {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	public Book reserveBook(UUID userId, UUID bookId) {
		
		Book book = bookService.findByUuid(bookId);
		User user = userService.findByUuid(bookId);
		
		book.setReservedBy(user);
		user.setReservedBook(book);
		
		return book;
	}
}
