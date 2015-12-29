package com.ss.academy.java.service.book;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ss.academy.java.model.book.BookHistory;

public interface BookHistoryService {

	BookHistory findById(Long id);

	void saveBookHistory(BookHistory bookHistory);
	
	void updateBookHistory(BookHistory bookHistory);
	
	@PreAuthorize("hasAuthority('ADMIN')")
	List<BookHistory> findAllBooksHistory();
	
	List<BookHistory> findAllBooksHistory(Integer offset, Integer maxResults);
	
	List<BookHistory> findAllBooksHistory(Integer offset, Integer maxResults, Integer isReturned);
	
	Long countAllBooksHistory();
	
	Long countAllBooksHistory(Integer isReturned);
}