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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	
	
	@ApiOperation(value = "Reserving a book by User UUID and Book UUID")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Reserve the book sucessfully"),
	    @ApiResponse(code = 400, message = "The reserve failed, there's a conflict in this action, such as: You've a reserved book already or this book is reserved by other user right now"),
	})
	
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
	
	@ApiOperation(value = "Devolving a book by User UUID and Book UUID")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Devolve the book sucessfully"),
	    @ApiResponse(code = 400, message = "The devolve failed, there's a conflict in this action, such as: You dont have any book reserved or this book is reserved by other user right now"),
	})
	
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
