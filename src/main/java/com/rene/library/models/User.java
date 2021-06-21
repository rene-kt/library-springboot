package com.rene.library.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	private String name;
	private String email;
	private Instant createdDate;
	
	private Integer numberOfBooksReserved = 0;
	
	private Integer numberOfReservedBooks = 0;
	
	
	@JsonIgnore
    @OneToOne(mappedBy = "reservedBy")
	private Book reservedBook;
	
    @JsonIgnore
    @OneToMany(mappedBy = "publishedBy")
	private List<Book> publishedBooks;
    
    @JsonIgnore
    @OneToOne(mappedBy="devolvedBy")
    private Book lastDevolvedBook;
    
    
   
}
