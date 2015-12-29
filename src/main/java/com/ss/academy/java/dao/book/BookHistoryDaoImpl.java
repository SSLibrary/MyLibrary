package com.ss.academy.java.dao.book;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.ss.academy.java.dao.AbstractDao;
import com.ss.academy.java.model.book.BookHistory;


@Repository("bookHistoryDao")
public class BookHistoryDaoImpl extends AbstractDao<Long, BookHistory> implements BookHistoryDao {

	/*
	 * Find Book History by id
	 */
	public BookHistory findById(Long id) {
		return getByKey(id);
	}

	/*
	 * Save Books History
	 */
	public void saveBookHistory(BookHistory bookHistory) {
		save(bookHistory);
	}
	
	/*
	 * Show all Books History
	 */
	@SuppressWarnings("unchecked")
	public List<BookHistory> findAllBooksHistory() {
		Criteria criteria = createEntityCriteria();
		List<BookHistory> booksHistory = (List<BookHistory>) criteria.list();
		return booksHistory;
	}
}
