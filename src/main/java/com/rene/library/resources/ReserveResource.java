package com.rene.library.resources;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rene.library.models.Book;
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

	@ApiOperation(value = "Reserving a book")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Reserve the book sucessfully"),
			@ApiResponse(code = 400, message = "The reserve failed, there's a conflict in this action, such as: You've a reserved book already or this book is reserved by other user right now"), })

	@PutMapping("/reserve/{bookId}")
	public ResponseEntity<Object> reserveBook(@PathVariable UUID bookId) {
		User user = userService.returnUserAuthenticated();
		Book book = bookService.findByUuid(bookId);

		if (book.getPublishedBy().equals(user)) {
			return GenericResponse.handleResponse(HttpStatus.BAD_REQUEST,
					"Você está tentando reservar o seu próprio livro", book);

		}
		
		
		if (user.getCurrentReservedBook() != null) {
			return GenericResponse.handleResponse(HttpStatus.BAD_REQUEST,
					"Você já está com um livro reservado, não é possível reservar dois livros ao mesmo tempo",
					user.getCurrentReservedBook());

		}

		
		
		service.reserveBook(user, book);

		return GenericResponse.handleResponse(HttpStatus.OK, "Livro reservado com sucesso", book);

	}

	@ApiOperation(value = "Devolving a book")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Devolve the book sucessfully"),
			@ApiResponse(code = 400, message = "The devolve failed, there's a conflict in this action, such as: You dont have any book reserved or this book is reserved by other user right now"), })

	@PutMapping("/devolve")
	public ResponseEntity<Object> devolveBook() {
		User user = userService.returnUserAuthenticated();

		if (user.getCurrentReservedBook() == null) {
			return GenericResponse.handleResponse(HttpStatus.BAD_REQUEST,
					"Você não está com nenhum livro reservado no momento", user.getCurrentReservedBook());

		}

		Book book = service.devolveBook(user.getId(), user.getCurrentReservedBook().getId());
		return GenericResponse.handleResponse(HttpStatus.OK, "Livro devolvido com sucesso", book);

	}
}
