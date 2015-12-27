package com.ss.academy.java.service.book;

import com.ss.academy.java.model.book.BookHistory;

public interface BookHistoryService {

	BookHistory findById(Long id);

	void saveBookHistory(BookHistory bookHistory);
}