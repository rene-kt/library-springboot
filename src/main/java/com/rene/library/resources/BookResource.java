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

@RestController
@RequestMapping
@CrossOrigin
public class BookResource {

	@Autowired
	private BookService service;

	@GetMapping("/book/{id}")
	public ResponseEntity<Object> findById(@PathVariable UUID id) {

		return GenericResponse.handleResponse(HttpStatus.OK, "Busca realizada com sucesso", service.findByUuid(id));

	}

	@PostMapping("/book/{userId}")
	public ResponseEntity<Object> insert(@RequestBody Book obj, @PathVariable UUID userId) {

		service.insert(obj, userId);
		return GenericResponse.handleResponse(HttpStatus.CREATED, "Livro publicado com sucesso", obj);

	}

	@GetMapping("/book")
	public ResponseEntity<Object> findAll() {

		return GenericResponse.handleResponse(HttpStatus.OK, "Busca realizada com sucesso", service.findAll());

	}

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
