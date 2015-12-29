package com.ss.academy.java.dao.book;

import java.util.List;

import com.ss.academy.java.model.book.BookHistory;

public interface BookHistoryDao {
	
	BookHistory findById(Long id);

	void saveBookHistory(BookHistory bookHistory);
	
	List<BookHistory> findAllBooksHistory();	

	List<BookHistory> findAllBooksHistory(Integer offset, Integer maxResults); 
	
	Long countAllBooksHistory();
} 
