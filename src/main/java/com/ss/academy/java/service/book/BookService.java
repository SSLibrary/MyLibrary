package com.ss.academy.java.service.book;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.book.BookHistory;

public interface BookService {

	Book findById(Long id);

	List<Book> findAllBooks();
	
	List<Book> findBooksByTitle(String bookTitle);

	@PreAuthorize("hasAuthority('ADMIN')")
	void saveBook(Book book);

	@PreAuthorize("hasAuthority('ADMIN')")
	void updateBook(Book book);

	@PreAuthorize("hasAuthority('ADMIN')")
	void deleteBook(Book book);
	
	List<Book> list(Integer offset, Integer maxResults, Long id);
    
    Long count(Long author_id);
    
    void changeStatus(Long book_id);
    
    @PreAuthorize("hasAuthority('ADMIN')")
    void getThisBook(Long user_id, Long book_id);

    @PreAuthorize("hasAuthority('ADMIN')")
	void returnThisBook(Long user_id, Long book_id);
    
    List<Book> listMyBooks(Long user_id);
}