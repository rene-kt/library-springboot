package com.rene.library.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rene.library.models.Book;
import com.rene.library.models.Reserve;
import com.rene.library.models.User;
import com.rene.library.services.BookService;
import com.rene.library.services.ReserveService;
import com.rene.library.services.UserService;

@RestController
@RequestMapping
@CrossOrigin
public class ReserveResource {

	@Autowired
	private ReserveService service;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;
	
	
	@PutMapping("/reserve")
	public ResponseEntity<Object> reserveBook(@RequestBody Reserve obj) {
		User user = userService.findByUuid(obj.getUserID());
		Book oldBook = bookService.findByUuid(obj.getBookID());

		if (user.getCurrentReservedBook() != null) {
			return GenericResponse.handleResponse(HttpStatus.BAD_REQUEST,
					"Você já está com um livro reservado, não é possível reservar dois livros ao mesmo tempo",
					user.getCurrentReservedBook());

		} 
		else if(oldBook.getReservedBy() != null) {
			return GenericResponse.handleResponse(HttpStatus.BAD_REQUEST,
					"Você está tentando reservar um livro que já está reservado",
					oldBook.getReservedBy());
		}


		Book book = service.reserveBook(obj.getUserID(), obj.getBookID());
		return GenericResponse.handleResponse(HttpStatus.OK, "Livro reservado com sucesso", book);

	}
	
	@PutMapping("/devolve")
	public ResponseEntity<Object> devolveBook(@RequestBody Reserve obj) {
		User user = userService.findByUuid(obj.getUserID());
		Book oldBook = bookService.findByUuid(obj.getBookID());

		if (user.getCurrentReservedBook() == null) {
			return GenericResponse.handleResponse(HttpStatus.BAD_REQUEST,
					"Você não está com nenhum livro reservado no momento",
					user.getCurrentReservedBook());

		} else if( !user.getCurrentReservedBook().equals(oldBook)) {
			return GenericResponse.handleResponse(HttpStatus.BAD_REQUEST,
					"Você está tentando devolver um livro que não está reservado em seu nome",
					user.getCurrentReservedBook());

		} 
		Book book = service.devolveBook(obj.getUserID(), obj.getBookID());
		return GenericResponse.handleResponse(HttpStatus.OK, "Livro devolvido com sucesso", book);

	}
}
