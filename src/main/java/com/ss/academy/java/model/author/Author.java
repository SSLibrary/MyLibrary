package com.ss.academy.java.model.author;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.ss.academy.java.model.book.Book;

@Entity
@Table(name = "authors")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("authors")
public class Author  {

	@Id
	@Column(name = "author_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private Long id;

	@Size(min = 3, max = 50)
	@Column(name = "name")
	@NotNull
	@JsonProperty("name")
	private String name;

	@Column(name = "country")
	@Enumerated(EnumType.STRING)
	@NotNull
	@JsonProperty("country")
	private AuthorCountry country;

	@OneToMany(mappedBy = "author")
	@JsonManagedReference(value = "author-books")
	private List<Book> books;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AuthorCountry getCountry() {
		return country;
	}

	public void setCountry(AuthorCountry country) {
		this.country = country;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
