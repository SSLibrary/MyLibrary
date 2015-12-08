package com.ss.academy.java.dao.book;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ss.academy.java.dao.AbstractDao;
import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.book.BookHistory;
import com.ss.academy.java.service.user.UserService;

@Repository("bookDao")
public class BookDaoImpl extends AbstractDao<Long, Book> implements BookDao {
	
	UserService userService;
	
	UserDetails user;

	public Book findById(Long id) {
		return getByKey(id);
	}

	public void saveBook(Book book) {
		persist(book);
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
	public List<Book> list(Integer offset, Integer maxResults, Long author_id){
		
		List<Book> list= (List<Book>) getSession()
				.createCriteria(Book.class)
				.add(Restrictions.eq("author.id", author_id))
				.setFirstResult(offset!=null?offset:0)
				.setMaxResults(maxResults!=null?maxResults:5)
				.list();		
		return list;
	}
	
	public Long count(Long author_id){
		return (Long)getSession()
				.createCriteria(Book.class)
				.add(Restrictions.eq("author.id", author_id))
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}

	public void changeStatus(Long book_id) {
		String book = this.findById(book_id).getStatus().toString();
		
		System.out.println(book);
		if (book.equals("Available")){
			Query query = getSession().createSQLQuery("update author_books set status = 'Loaned' where book_id = :id");
			query.setLong("id", book_id);
			query.executeUpdate();
		}else{
			Query query = getSession().createSQLQuery("update author_books set status = 'Available' where book_id = :id");
			query.setLong("id", book_id);
			query.executeUpdate();
		}
	}

	public void getThisBook(Long user_id, Long book_id) {
		String book = this.findById(book_id).getStatus().toString();
		
		if(book.equals("Available")){
			Query query = getSession().createSQLQuery("insert into history (user_id, book_id, return_date) values (:user_id, :book_id, NOW()+INTERVAL 14 DAY)");
			query.setLong("user_id", user_id);
			query.setLong("book_id", book_id);
			query.executeUpdate();
			changeStatus(book_id);
		}
	}
	
	public void returnThisBook(Long user_id, Long book_id){
		changeStatus(book_id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> listMyBooks(Long user_id){
		
		List<Book> list= (List<Book>) getSession()
				.createCriteria(BookHistory.class)
//				.createAlias("history.user_id", "u") // inner join by default
//				.add(Restrictions.eq("u.id", user_id))
				.add(Restrictions.eq("id", user_id))
				.list();		
		return list;
	}
	
}
