package com.ss.academy.java.dao.book;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ss.academy.java.dao.AbstractDao;
import com.ss.academy.java.model.book.Book;

@Repository("bookDao")
public class BookDaoImpl extends AbstractDao<Long, Book> implements BookDao {
	
	public Book findById(Long id) {
		return getByKey(id);
	}

	public void saveBook(Book book) {
		save(book);
	}

	public void deleteBookById(Long id) {
		Query query = getSession().createSQLQuery("delete from author_books where book_id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Book> findAllBooks() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("title")); // Order
																					// ascending
																					// by
																					// title
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // To
																		// avoid
																		// duplicates.
		List<Book> books = (List<Book>) criteria.list();
		return books;
	}

	@SuppressWarnings("unchecked")
	public List<Book> findBooksByTitle(String bookTitle) {
		String searchBy = "%" + bookTitle + "%";
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("title")).add(Restrictions.like("title", searchBy));

		List<Book> books = (List<Book>) criteria.list();

		return books;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Book> listAllBooks(Integer offset, Integer maxResults, Long author_id){
		
		List<Book> list= (List<Book>) getSession()
				.createCriteria(Book.class)
				.add(Restrictions.eq("author.id", author_id))
				.setFirstResult(offset!=null?offset:0)
				.setMaxResults(maxResults!=null?maxResults:5)
				.list();		
		return list;
	}
	
	public Long countAllBooks(Long author_id){
		return (Long)getSession()
				.createCriteria(Book.class)
				.add(Restrictions.eq("author.id", author_id))
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> listAllBooks(Integer offset, Integer maxResults){
		return getSession()
				.createCriteria(Book.class)
				.setFirstResult(offset!=null?offset:0)
				.setMaxResults(maxResults!=null?maxResults:5)
				.list();
	}
	
	public Long countAllBooks(){
		return (Long)getSession()
				.createCriteria(Book.class)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
}
