package com.ss.academy.java.dao.history;

import com.ss.academy.java.dao.AbstractDao;
import com.ss.academy.java.dao.book.BookDaoImpl;
import com.ss.academy.java.dao.history.HistoryDao;
import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.book.BookHistory;
import com.ss.academy.java.model.user.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository(value="historyDao")
public class HistoryDaoImpl extends AbstractDao<Integer, BookHistory> implements HistoryDao {
    
	BookDaoImpl bookDao;

    @SuppressWarnings("unchecked")
	public List<Book> showMyHistory() {
    	Criteria criteria = createEntityCriteria().addOrder(Order.desc("book_id"));
    	List<Book> history = (List<Book>) criteria.list();
		return history;
    	
//    	SQLQuery query = this.getSession().createSQLQuery("select * from history where user_id = :userId");
//    	query.setLong("userId", userId.getId());
//    	query.executeUpdate();
//    	List<Book> historyList = query.list();
//    	return historyList;
//        Criteria criteria = this.createEntityCriteria().addOrder(Order.asc((String)"title"));
//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        List books = criteria.list();
//        return books;
    }

    public void getBook(User userId, Book bookId) {
        SQLQuery query = this.getSession().createSQLQuery("INSERT INTO history VALUES (?, ?)");
        query.setLong("user_id", userId.getId().longValue());
        query.setLong("book_id", bookId.getId().longValue());
        query.executeUpdate();
        bookDao.changeStatus(bookId.getId());
    }

    public void returnBook(Book bookId) {
        bookDao.changeStatus(bookId.getId());
    }
}