package com.ss.academy.java.restapi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.hal.CurieProvider;
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
import com.ss.academy.java.model.author.AuthorResource;
import com.ss.academy.java.model.author.AuthorResourceAssembler;
import com.ss.academy.java.service.author.AuthorService;

@RestController
@RequestMapping({ "/restapi/authors" })
@ExposesResourceFor(AuthorResource.class)
public class AuthorsRestController {

	@Autowired
	AuthorService authorService;

	/**
	 * Retrieve All Authors in JSON format
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Author>> listAllAuthorsInJSON() {
		List<Author> authors = authorService.findAllAuthors();

		if (authors.isEmpty()) {
			return new ResponseEntity<List<Author>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
	}

	/**
	 * Retrieve Author by ID in JSON format
	 */
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	public ResponseEntity<AuthorResource> getAuthorInJSON(@PathVariable("id") long id) {
		Author author = authorService.findById(id);

		if (author == null) {
			return new ResponseEntity<AuthorResource>(HttpStatus.NO_CONTENT);
		}

		AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		AuthorResource authorResource = assembler.toResource(author);

		
		authorResource.add(linkTo(AuthorsRestController.class).withRel(author.getId().toString()));

		return new ResponseEntity<AuthorResource>(authorResource, HttpStatus.OK);
	}

	/**
	 * Create Author
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> createAuthor(@RequestBody Author author, UriComponentsBuilder ucBuilder) {
		System.out.println(authorService.findAuthorsByName(author.getName()));
		if (!authorService.findAuthorsByName(author.getName()).isEmpty()) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		authorService.saveAuthor(author);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/authors/{id}").buildAndExpand(author.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
