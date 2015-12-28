package com.ss.academy.java.dao.book;

import org.springframework.stereotype.Repository;

import com.ss.academy.java.dao.AbstractDao;
import com.ss.academy.java.model.book.BookHistory;


@Repository("bookHistoryDao")
public class BookHistoryDaoImpl extends AbstractDao<Long, BookHistory> implements BookHistoryDao {

	public BookHistory findById(Long id) {
		return getByKey(id);
	}

	public void saveBookHistory(BookHistory bookHistory) {
		save(bookHistory);
	}
}
