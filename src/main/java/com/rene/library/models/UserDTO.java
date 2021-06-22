package com.rene.library.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID id;
	private String name;
	private String username;
	private Instant createdDate;
	private Integer numberOfBooksReserved = 0;
	private Integer numberOfReservedBooks = 0;
	private Book currentReservedBook;
	private Set<Book> reservedBooks;
	private Set<Book> publishedBooks;
	private Book lastDevolvedBook;

}
