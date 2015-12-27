package com.ss.academy.java.dao.book;

import com.ss.academy.java.model.book.BookHistory;

public interface BookHistoryDao {
	
	BookHistory findById(Long id);

	void saveBookHistory(BookHistory bookHistory);
}
