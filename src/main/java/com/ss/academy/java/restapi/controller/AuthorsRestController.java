package com.ss.academy.java.restapi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
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
@ExposesResourceFor(Author.class)
public class AuthorsRestController {

	@Autowired
	AuthorService authorService;

	@Autowired
	CurieProvider curieProvider;

	/**
	 * Retrieve All Authors in JSON format
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ResponseEntity<Resources<AuthorResource>> listAllAuthorsInJSON() {
		List<Author> authors = authorService.findAllAuthors();

		AuthorResourceAssembler assembler = new AuthorResourceAssembler();
		List<AuthorResource> authorsResources = new ArrayList<AuthorResource>();

		for (Author author : authors) {
			AuthorResource authorResource = assembler.toResource(author);

			authorResource.add(linkTo(AuthorsRestController.class).slash(author).withSelfRel());
			authorResource.add(linkTo(AuthorsRestController.class).slash(author).slash("/books").withRel("books"));

			authorsResources.add(authorResource);
		}

		Resources<AuthorResource> authorResources = new Resources<AuthorResource>(authorsResources);

		return new ResponseEntity<Resources<AuthorResource>>(authorResources, HttpStatus.OK);

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
		authorResource.add(linkTo(AuthorsRestController.class).slash(author).withSelfRel());
		authorResource.add(linkTo(methodOn(AuthorsRestController.class).listAllAuthorsInJSON()).withRel("authors"));
		authorResource.add(linkTo(AuthorsRestController.class).slash(author).slash("/books").withRel("books"));

		return new ResponseEntity<AuthorResource>(authorResource, HttpStatus.OK);
	}

	/**
	 * Create Author
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<Void> createAuthor(@RequestBody Author author) {
		if (!authorService.findAuthorsByName(author.getName()).isEmpty()) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		authorService.saveAuthor(author);

		UriComponentsBuilder uriBuilderRib = linkTo(AuthorsRestController.class).toUriComponentsBuilder();
		URI uriSelf = uriBuilderRib.path("/{id}").buildAndExpand(author.getId()).toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriSelf);

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
