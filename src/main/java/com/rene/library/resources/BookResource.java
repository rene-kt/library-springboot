package com.rene.library.resources;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rene.library.models.Book;
import com.rene.library.services.BookService;
import com.rene.library.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping
@CrossOrigin
public class BookResource {

	@Autowired
	private BookService service;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Return a book by UUID")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Return the book of the given UUID"),
	    @ApiResponse(code = 404, message = "The book was not found"),
	})
	@GetMapping("/book/{id}")
	public ResponseEntity<Object> findById(@PathVariable UUID id) {

		return GenericResponse.handleResponse(HttpStatus.OK, "Busca realizada com sucesso", service.findByUuid(id));

	}

	@ApiOperation(value = "Create a new book")
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "The book was created"),

	})
	@PostMapping("/book")
	public ResponseEntity<Object> insert(@RequestBody Book obj) {
		
		service.insert(obj, userService.returnUserAuthenticated().getId());
		return GenericResponse.handleResponse(HttpStatus.CREATED, "Livro publicado com sucesso", obj);

	}

	@ApiOperation(value = "Return all books")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Return all books"),

	})
	@GetMapping("/book")
	public ResponseEntity<Object> findAll() {

		return GenericResponse.handleResponse(HttpStatus.OK, "Busca realizada com sucesso", service.findAll());

	}

	@ApiOperation(value = "Delete a book by UUID")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "The book was deleted"),
	    @ApiResponse(code = 400, message = "You can't delete this book because its reserved by a user right now"),
	})
	@DeleteMapping("/book/{bookId}")
	public ResponseEntity<Object> delete(@PathVariable UUID bookId) {

		Book book = service.findByUuid(bookId);

		if (book.getReservedBy() != null) {
			return GenericResponse.handleResponse(HttpStatus.BAD_REQUEST,
					"Você não pode deletar esse livro pois ele está reservado nesse momento", book);

		}

		return GenericResponse.handleResponse(HttpStatus.OK, "Deleção realizada com sucesso", service.delete(bookId));
	}

}
