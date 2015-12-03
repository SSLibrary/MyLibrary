package com.ss.academy.java.model.book;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ss.academy.java.model.user.User;

@Entity
@Table(name = "history")
public class BookHistory {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "user")
	@Column(name = "book_id")
	@JsonManagedReference(value = "user-book")
	private Book book;
	
//	@ManyToOne()
	@Column(name = "user_id")
	private User user;
	
//	@OneToOne(mappedBy = "userId")
//	@Column(name = "user_id")
//	private int userId;

//	@OneToMany(mappedBy = "userId")
//	@JsonManagedReference(value = "userId-book")
//	private List<Book> books;
//	
//	public List<Book> getBooks() {
//		return books;
//	}
//
//	public void setBooks(List<Book> books) {
//		this.books = books;
//	}

	public Date getGetDate() {
		return getDate;
	}

	public void setGetDate(Date getDate) {
		this.getDate = getDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "get_date")
	private Date getDate;
	
	@Column(name = "return_date")
	private Date returnDate;

	public int getId() {
		return id;
	}

	
}
