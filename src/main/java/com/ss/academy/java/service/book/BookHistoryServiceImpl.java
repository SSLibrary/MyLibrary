package com.ss.academy.java.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.academy.java.dao.book.BookHistoryDao;
import com.ss.academy.java.model.book.BookHistory;
import com.ss.academy.java.model.user.User;

@Service("bookHistoryService")
@Transactional
public class BookHistoryServiceImpl implements BookHistoryService {
	private final byte LOANED_BOOK = 0;
	
	@Autowired
	private BookHistoryDao dao;

	public BookHistory findById(Long id) {
		return dao.findById(id);
	}

	public void saveBookHistory(BookHistory bookHistory) {
		dao.saveBookHistory(bookHistory);
	}
	
	public void updateBookHistory(BookHistory bookHistory) {
		BookHistory entity = dao.findById(bookHistory.getId());

		if (entity != null) {
			entity.setIsReturned(bookHistory.getIsReturned());
			entity.setReturnDate(bookHistory.getReturnDate());
		}
	}

	public List<BookHistory> findAllBooksHistory() {
		List<BookHistory> booksHistory = dao.findAllBooksHistory();
		return booksHistory;
	}
	
	public List<BookHistory> findAllBooksHistory(Integer offset, Integer maxResults) {
		List<BookHistory> booksHistory = dao.findAllBooksHistory(offset, maxResults);
		return booksHistory;
	}
	
	public List<BookHistory> findAllBooksHistory(Integer offset, Integer maxResults,byte isReturned) {
		List<BookHistory> booksHistory = dao.findAllBooksHistory(offset, maxResults, isReturned);
		return booksHistory;
	}
	
	public Long countAllBooksHistory() {
		return dao.countAllBooksHistory();
	}	
	
	public Long countAllBooksHistory(byte isReturned) {
		return dao.countAllBooksHistory(isReturned);
	}	
	
	public User getCurrentBookLoaner() {
		User currentBookLoaner = null;
		List<BookHistory> booksHistory = dao.findAllBooksHistory();
		for (BookHistory bookHistory : booksHistory) {
			if (bookHistory.getIsReturned() == LOANED_BOOK) {
			currentBookLoaner = bookHistory.getUser();
			break;
			}			
		}
		return currentBookLoaner;
	}
}
