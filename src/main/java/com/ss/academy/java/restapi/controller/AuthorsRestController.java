package com.ss.academy.java.restapi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
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

	@Autowired
	CurieProvider curieProvider;

	/**
	 * Retrieve All Authors in JSON format
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ResponseEntity<Resources<Author>> listAllAuthorsInJSON() {
		List<Author> authors = authorService.findAllAuthors();

		Link authorsLink = linkTo(AuthorsRestController.class).withSelfRel();

		for (Author author : authors) {
			buildAuthorResource(author);
		}

		Resources<Author> resourceList = new Resources<Author>(authors, authorsLink);

		return new ResponseEntity<Resources<Author>>(resourceList, HttpStatus.OK);

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

	private Resource<Author> buildAuthorResource(Author author) {
		Link authorLink = linkTo(AuthorsRestController.class).slash("/authors").slash(author.getId()).withSelfRel();

		return new Resource<Author>(author, authorLink.expand(author.getId()));
	}
}
