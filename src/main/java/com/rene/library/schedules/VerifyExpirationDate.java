package com.rene.library.schedules;

import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rene.library.models.Book;
import com.rene.library.services.BookService;
import com.rene.library.services.ReserveService;

@Component
public class VerifyExpirationDate {

	Logger logger = Logger.getLogger(VerifyExpirationDate.class.getName());

	@Autowired
	private BookService bookService;

	@Autowired
	private ReserveService reserveService;

	// Two hours
	@Scheduled(fixedRate = 60 * 60 * 2000)
	public void scheduleIfIsItExpired() {

		isExpired();

	}

	private void isExpired() {

		List<Book> books = bookService.findAll();

		logger.log(Level.INFO, "Searching all books...");

		// To each book in books, create a Thread to each of them
		for (Book book : books) {

			Thread thread = new Thread() {
				public void run() {

					try {
						// Verifying if the book is expired
						if (book.getExpiration_date().isBefore(Instant.now().minusSeconds(10800))) {

							book.setExpiration_date(null);
							book.setIsExpired("Y");

							logger.log(Level.WARNING, "The book: " + book.getTitle() + " is expired");
							logger.log(Level.INFO, "Published by: " + book.getPublishedBy().getName().toUpperCase());
							logger.log(Level.INFO, "Reserved by: " + book.getReservedBy().getName().toUpperCase());

							
							logger.log(Level.WARNING,
									"The book: " + book.getTitle().toUpperCase() + " is being devolved right now...");

							reserveService.devolveBook(book.getReservedBy().getId(), book.getId());

							// Its not expired
						} else {

							logger.log(Level.INFO, "The book: " + book.getTitle().toUpperCase() + " is not expired");
							logger.log(Level.INFO, "Published by: " + book.getPublishedBy().getName().toUpperCase());
							logger.log(Level.INFO, "Reserved by: " + book.getReservedBy().getName().toUpperCase());

						}

						// If has no expiration date
					} catch (NullPointerException e) {

						book.setExpiration_date(null);
						book.setIsExpired("N");

						logger.log(Level.INFO, "The book: " + book.getTitle().toUpperCase() + " is not reserved");
						logger.log(Level.INFO, "Published by: " + book.getPublishedBy().getName().toUpperCase());

					}

				}
			};

			thread.start();

		}

	}

}
