package com.rene.library.models;


import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@DynamicUpdate
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	private String title;
	private String author;
	@CreatedDate
	private Instant createdDate;
	private Date reservedAt;
	private User publishedBy;
	private User reservedBy;
	
	
	
}
