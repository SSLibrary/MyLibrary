package com.ss.academy.java.dao.book;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
	
	@SuppressWarnings("unchecked")
	public List<BookHistory> findAllBooksHistory(Integer offset, Integer maxResults) {
		return getSession()
				.createCriteria(BookHistory.class)
//				.addOrder( Order.desc("date"))
				.setFirstResult(offset!=null?offset:0)
				.setMaxResults(maxResults!=null?maxResults:5)
				.list();		
	}
	
	public Long countAllBooksHistory(){
		return (Long)getSession()
				.createCriteria(BookHistory.class)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<BookHistory> findAllBooksHistory(Integer offset, Integer maxResults, Integer isReturned) {
		return getSession()
				.createCriteria(BookHistory.class)
				.add(Restrictions.eq("isReturned", isReturned))
//				.addOrder( Order.desc("date"))
				.setFirstResult(offset!=null?offset:0)
				.setMaxResults(maxResults!=null?maxResults:5)
				.list();		
	}
	
	public Long countAllBooksHistory(Integer isReturned){
		return (Long)getSession()
				.createCriteria(BookHistory.class)
				.add(Restrictions.eq("isReturned", isReturned))				
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
}
