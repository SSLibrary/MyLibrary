package com.ss.academy.java.model.author;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class AuthorResourceAssembler extends ResourceAssemblerSupport<Author, AuthorResource> {

	public AuthorResourceAssembler() {
		super(Author.class, AuthorResource.class);
	}

	public AuthorResource toResource(Author author) {
		AuthorResource resource = createResourceWithId(author.getId(), author);
		resource.setName(author.getName());
		resource.setCountry(author.getCountry());
		resource.setBooks(author.getBooks());
		
		return resource;
	}

}
