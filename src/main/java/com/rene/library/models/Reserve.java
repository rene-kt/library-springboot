package com.rene.library.models;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserve implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID userID;
	private UUID bookID;
}
