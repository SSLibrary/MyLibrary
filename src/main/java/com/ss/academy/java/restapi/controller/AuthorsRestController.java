package com.ss.academy.java.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ss.academy.java.model.author.Author;
import com.ss.academy.java.service.author.AuthorService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping({ "/restapi/authors" })
@Api(value = "onlinestore", description = "Operations pertaining to Online Store")
public class AuthorsRestController {

	@Autowired
	AuthorService authorService;

	/**
	 * Retrieve All Authors in JSON format
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View the Specific info of the product")
	public ResponseEntity<List<Author>> listAllAuthorsInJSON() {
		List<Author> authors = authorService.findAllAuthors();

		if (authors.isEmpty()) {
			return new ResponseEntity<List<Author>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
	}

	/**
	 * Retrieve All Authors in XML format
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<List<Author>> listAllAuthorsInXML() {
		List<Author> authors = authorService.findAllAuthors();

		if (authors.isEmpty()) {
			return new ResponseEntity<List<Author>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
	}

	/**
	 * Retrieve Author by ID in JSON format
	 */
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View the Specific info of the product")
	public ResponseEntity<Author> getAuthorInJSON(@PathVariable("id") long id) {
		Author author = authorService.findById(id);

		if (author == null) {
			return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}

	/**
	 * Retrieve Author by ID in XML format
	 */
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET, produces = "application/xml")
	@ApiOperation(value = "View the Specific info of the product")
	public ResponseEntity<Author> getAuthorInXML(@PathVariable("id") long id) {
		Author author = authorService.findById(id);

		if (author == null) {
			return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}

	/**
	 * Create Author
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<Void> createAuthor(@RequestBody Author author, UriComponentsBuilder ucBuilder) {
		if (authorService.findAuthorsByName(author.getName()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		authorService.saveAuthor(author);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/authors/{id}").buildAndExpand(author.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
