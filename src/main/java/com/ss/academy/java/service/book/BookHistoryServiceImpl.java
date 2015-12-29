package com.ss.academy.java.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.academy.java.dao.book.BookHistoryDao;
import com.ss.academy.java.model.book.BookHistory;

@Service("bookHistoryService")
@Transactional
public class BookHistoryServiceImpl implements BookHistoryService {

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
			entity.setIsReturned(1);
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
	
	public Long countAllBooksHistory() {
		return dao.countAllBooksHistory();
	}	
}
