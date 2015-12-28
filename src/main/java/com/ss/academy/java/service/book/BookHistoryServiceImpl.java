package com.ss.academy.java.service.book;

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
}