package com.ss.academy.java.dao.history;

import com.ss.academy.java.dao.AbstractDao;
import com.ss.academy.java.dao.book.BookDao;
import com.ss.academy.java.dao.history.HistoryDao;
import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.book.BookHistory;
import com.ss.academy.java.model.user.User;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository(value="historyDao")
public class HistoryDaoImpl extends AbstractDao<Integer, BookHistory> implements HistoryDao {
    
	BookDao bookDao;

    @SuppressWarnings("unchecked")
	public List<Book> showMyHistory() {
    	List<Book> list = (List<Book>) getSession().createCriteria(Book.class, "book")
    			.addOrder(Order.desc("book_id")).list();
    	return list;

    }

    public void getBook(User user, Book book) {
        SQLQuery query = this.getSession().createSQLQuery("INSERT INTO history (user_id, book_id) VALUES (?, ?)");
        query.setLong("user_id", user.getId());
        query.setLong("book_id", book.getId());
        query.executeUpdate();
        bookDao.changeStatus(book.getId());;
    }

    public void returnBook(Book bookId) {
        bookDao.changeStatus(bookId.getId());
    }
}