package com.ss.academy.java.model.book;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "history")
public class BookHistory {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@OneToOne(mappedBy = "userId")
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "book_id")
	private int bookId;
	
	@OneToMany(mappedBy = "userId")
	@JsonManagedReference(value = "userId-book")
	private List<Book> books;
	
	@Column(name = "get_date")
	private Date getDate;
	
	@Column(name = "return_date")
	private Date returnDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
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
}
