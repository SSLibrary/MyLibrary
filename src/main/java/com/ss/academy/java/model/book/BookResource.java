package com.ss.academy.java.model.book;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.ss.academy.java.model.author.Author;
import com.ss.academy.java.model.rating.Rating;

public class BookResource extends ResourceSupport {

	private String title;
	private BookStatus status;
	private List<Rating> ratings;

	public BookResource(String title, BookStatus status, List<Rating> ratings) {
		super();
		this.title = title;
		this.status = status;
		this.ratings = ratings;
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

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
}
