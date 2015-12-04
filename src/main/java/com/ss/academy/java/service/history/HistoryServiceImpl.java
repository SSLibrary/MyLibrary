package com.ss.academy.java.service.history;

import com.ss.academy.java.dao.history.HistoryDao;
import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.user.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value="historyService")
@Transactional
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryDao dao;
    
//    @Autowired
//    private UserDao userDao;

    public List<Book> showMyHistory(Long userId) {
        return dao.showMyHistory(userId);
    }

    public void getBook(User userId, Book bookId) {
        dao.getBook(userId, bookId);
    }

    public void returnBook(Book book) {
        dao.returnBook(book);
    }
}
