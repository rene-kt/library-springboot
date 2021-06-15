package com.rene.library.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rene.library.models.Book;
import com.rene.library.models.User;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	Optional<Book> findOneById(UUID id);

}
