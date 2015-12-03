package com.ss.academy.java.dao.history;

import java.util.List;

import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.user.User;

public interface HistoryDao {

	List<Book> showMyHistory(Long userId);

	void getBook(User userId, Book bookId);
	
	void returnBook(Book bookId);
	
}
