package com.ss.academy.java.service.book;

import java.util.List;

import com.ss.academy.java.model.book.Book;

public interface BookService {

	Book findById(Long id);

	List<Book> findAllBooks();
	
	List<Book> findBooksByTitle(String bookTitle);

	//@PreAuthorize("hasAuthority('ADMIN')")
	void saveBook(Book book);

	//@PreAuthorize("hasAuthority('ADMIN')")
	void updateBook(Book book);

	//@PreAuthorize("hasAuthority('ADMIN')")
	void deleteBook(Book book);
	
	List<Book> list(Integer offset, Integer maxResults);
    
    Long count();
}