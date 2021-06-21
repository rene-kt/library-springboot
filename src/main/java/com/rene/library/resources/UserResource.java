package com.rene.library.resources;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rene.library.models.User;
import com.rene.library.services.UserService;

@RestController
@RequestMapping
@CrossOrigin
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> findById(@PathVariable UUID id) {

		return GenericResponse.handleResponse(HttpStatus.OK, "Busca realizada com sucesso", service.findByUuid(id));

	}

	@PostMapping("/user")
	public ResponseEntity<Object> insert(@RequestBody User obj) {

		service.insert(obj);
		return GenericResponse.handleResponse(HttpStatus.CREATED, "Usuário criado com sucesso", obj);

	}
	
	@GetMapping("/user")
	public ResponseEntity<Object> findAll() {

		return GenericResponse.handleResponse(HttpStatus.OK, "Busca realizada com sucesso", service.findAll());

	}

}
