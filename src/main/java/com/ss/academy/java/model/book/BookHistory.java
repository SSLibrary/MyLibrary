package com.ss.academy.java.model.book;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.ss.academy.java.model.user.User;

@Entity
@Table(name = "books_history")
public class BookHistory {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Type(type = "timestamp")
	@Column(name = "get_date", nullable = false)
	private Date getDate = new Date();
	
	@Type(type = "timestamp")
	@Column(name = "return_date", nullable = false)
	private Date returnDate;
	
	@Column(name = "is_returned", nullable = false)
	private int isReturned = 0;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

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

	public int getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(int isReturned) {
		this.isReturned = isReturned;
	}
}
