package com.ss.academy.java.dao.book;

import java.util.List;

import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.book.BookHistory;

public interface BookDao {
	
	Book findById(Long id);

	void saveBook(Book book);

	void deleteBookById(Long id);

	List<Book> findAllBooks();
	
	List<Book> findBooksByTitle(String bookTitle);
	
	List<Book> list(Integer offset, Integer maxResults, Long id);
    
    Long count(Long author_id);
    
    void changeStatus(Long book_id);
    
    void getThisBook(Long user_id, Long book_id);

	void returnThisBook(Long user_id, Long book_id);
	
	List<Book> listMyBooks(Long user_id);
}
