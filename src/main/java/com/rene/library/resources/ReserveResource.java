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
import com.rene.library.services.ReserveService;

@RestController
@RequestMapping
@CrossOrigin
public class ReserveResource {

	
	@Autowired 
	private ReserveService service;


	@PutMapping("/reserve")
	public ResponseEntity<Object> reserveBook(@RequestBody Reserve obj) {
		
		Book book = service.reserveBook(obj.getUserID(), obj.getBookID());
		

		return GenericResponse.handleResponse(HttpStatus.OK, "Livro reservado com sucesso", book);

	}

}
