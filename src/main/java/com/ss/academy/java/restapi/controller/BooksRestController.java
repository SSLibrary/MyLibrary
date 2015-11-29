package com.ss.academy.java.restapi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.academy.java.model.author.Author;
import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.book.BookResource;
import com.ss.academy.java.model.book.BookResourceAssembler;
import com.ss.academy.java.service.author.AuthorService;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.rating.RatingService;
import com.ss.academy.java.service.user.UserService;

/**
 * Handles requests for the application authors' books page.
 */
@RestController
@ExposesResourceFor(Book.class)
@RequestMapping(value = "restapi/authors/{id}/books")
public class BooksRestController {

	@Autowired
	BookService bookService;

	@Autowired
	AuthorService authorService;

	/*
	 * This method will list all existing books and will check whether they have
	 * been rated so far by the current user.
	 */
	// TODO: Put @AuthenticationPrincipal UserDetails userDetails into the MIX.
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ResponseEntity<Resources<BookResource>> listAllBooks(@PathVariable Long id) {
		Author author = authorService.findById(id);
		List<Book> books = author.getBooks();

		BookResourceAssembler assembler = new BookResourceAssembler();
		List<BookResource> listOfBookResources = new ArrayList<BookResource>();

		for (Book book : books) {
			BookResource bookResource = assembler.toResource(book);
			bookResource.add(linkTo(AuthorsRestController.class).slash(author.getId().toString()).slash("/books")
					.slash(book).withSelfRel());

			listOfBookResources.add(bookResource);
		}

		Resources<BookResource> bookResources = new Resources<BookResource>(listOfBookResources);

		return new ResponseEntity<Resources<BookResource>>(bookResources, HttpStatus.OK);
	}

}
