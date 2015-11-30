package com.ss.academy.java.model.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.user.User;

@Entity
@Table(name = "COMMENTS")
public class Comment {
	
	@Id
	@Column(name = "COMMENT_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer comment_id;
	
	@Size(max = 200)
	@Column(name = "COMMENT", nullable = false)
	private String comment;
	
	@ManyToOne
	@JoinColumn(name = "BOOK_ID")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
	
}
	