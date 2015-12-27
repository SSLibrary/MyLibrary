package com.ss.academy.java.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.academy.java.dao.book.BookDao;
import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.book.BookStatus;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao dao;

	public Book findById(Long id) {
		return dao.findById(id);
	}

	public void saveBook(Book book) {
		dao.saveBook(book);
	}

	public void updateBook(Book book) {
		Book entity = dao.findById(book.getId());

		if (entity != null) {
			entity.setTitle(book.getTitle());
		}
	}

	public List<Book> findAllBooks() {
		return dao.findAllBooks();
	};

	public void deleteBook(Book book) {
		dao.deleteBookById(book.getId());
	}

	public List<Book> findBooksByTitle(String bookTitle) {
		return dao.findBooksByTitle(bookTitle);
	}
	
	public List<Book> list(Integer offset, Integer maxResults, Long id) {
		return dao.list(offset, maxResults, id);
	}

	public Long count(Long author_id) {
		return dao.count(author_id);
	}

	public void changeBookStatus(Book book) {
		
		Book entity = dao.findById(book.getId());

		if (entity != null && entity.getStatus().equals(BookStatus.Available)) {
			entity.setStatus(BookStatus.Loaned);
		} else if (entity != null && entity.getStatus().equals(BookStatus.Loaned)){
			entity.setStatus(BookStatus.Available);
		}
	}
}