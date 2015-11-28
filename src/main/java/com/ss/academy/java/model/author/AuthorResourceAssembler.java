package com.ss.academy.java.model.author;

import org.apache.log4j.lf5.util.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class AuthorResourceAssembler extends ResourceAssemblerSupport<Author, AuthorResource> {

	public AuthorResourceAssembler() {
		super(Author.class, AuthorResource.class);
	}

	public AuthorResource toResource(Author author) {
		AuthorResource resource = new AuthorResource(author.getName(), author.getCountry(), author.getBooks());
		
		return resource;
	}

}
