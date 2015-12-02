package com.ss.academy.java.model.book;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ss.academy.java.model.author.Author;
import com.ss.academy.java.model.comment.Comment;
import com.ss.academy.java.model.rating.Rating;

@Entity
@Table(name = "author_books")
public class Book implements Identifiable<Long> {

	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, max = 50)
	@Column(name = "title")
	@NotNull
	private String title;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@NotNull
	private BookStatus status;

	@ManyToOne()
	@JoinColumn(name = "author_id")
	@JsonBackReference(value = "author-books")
	private Author author;

	@OneToMany(mappedBy = "book")
	@JsonManagedReference(value = "book-ratings")
	private List<Rating> ratings;
	
	@OneToMany(mappedBy = "book")
	@JsonManagedReference(value = "book-comments")
	private List<Comment> comments;

	@Transient
	@JsonIgnore
	private boolean isRated;

	@Transient
	@JsonIgnore
	private Double averageRating;

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public boolean getIsRated() {
		return isRated;
	}

	public void setIsRated(boolean isRated) {
		this.isRated = isRated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Comment> getComment() {
		return comments;
	}

	public void setComment(List<Comment> comments) {
		this.comments = comments;
	}

}