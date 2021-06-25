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
import com.rene.library.repositories.UserRepository;
import com.rene.library.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping
@CrossOrigin
public class UserResource {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repo;

	@ApiOperation(value = "Return a user by UUID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return the user of the given UUID"),
			@ApiResponse(code = 404, message = "The user was not found"), })
	@GetMapping("/user/{id}")
	public ResponseEntity<Object> findById(@PathVariable UUID id) {

		return GenericResponse.handleResponse(HttpStatus.OK, "Busca realizada com sucesso", service.findByUuid(id));

	}

	@ApiOperation(value = "Return a userDTO by UUID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return the userDTO of the given UUID"),
			@ApiResponse(code = 404, message = "The userDTO was not found"), })
	@GetMapping("/userdto/{id}")
	public ResponseEntity<Object> findDtoById(@PathVariable UUID id) {

		return GenericResponse.handleResponse(HttpStatus.OK, "Busca realizada com sucesso", service.returnUserDTO(id));

	}

	@ApiOperation(value = "Create a user")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Create user successfully"),

	})

	@PostMapping("/user")
	public ResponseEntity<Object> insert(@RequestBody User obj) {

		if (repo.findByUsername(obj.getUsername()) != null) {
			return GenericResponse.handleResponse(HttpStatus.BAD_REQUEST,
					"Já existe um usuário com esse mesmo username", null);

			// TODO: handle exception
		}

		service.insert(obj);

		return GenericResponse.handleResponse(HttpStatus.CREATED, "Usuário criado com sucesso", obj);

	}

	@ApiOperation(value = "Find all users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return all users"),

	})
	@GetMapping("/user")
	public ResponseEntity<Object> findAll() {

		return GenericResponse.handleResponse(HttpStatus.OK, "Busca realizada com sucesso", service.findAll());

	}

}
