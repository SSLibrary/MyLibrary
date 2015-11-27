package com.ss.academy.java.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.academy.java.model.author.Author;
import com.ss.academy.java.model.author.AuthorCountry;
import com.ss.academy.java.service.author.AuthorService;

/**
 * Handles requests for the application authors page.
 */
@Controller
@RequestMapping({ "/authors" })
public class AuthorsController {

	@Autowired
	AuthorService service;

//	/*
//	 * This method will list all existing authors.
//	 */
//	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
//	public String listAllAuthors(ModelMap model) {
//		List<Author> authors = service.findAllAuthors();
//		model.addAttribute("authors", authors);
//
//		return "authors/all";
//	}
	
	

	/*
	 * This method provides the ability to search for authors by their names.
	 */
	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String searchAuthorByName(@RequestParam("author_name") String author_name, ModelMap model) {
		List<Author> authors = service.findAuthorsByName(author_name);
		model.addAttribute("authors", authors);

		return "authors/all";
	}

	/*
	 * This method will provide the medium to add a new author.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String addNewAuthor(ModelMap model) {
		Author author = new Author();
		model.addAttribute("author", author);
		model.addAttribute("edit", false);
		model.addAttribute("countries", AuthorCountry.values());

		return "authors/addNewAuthor";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving author in the database. It also validates the user input.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveAuthor(@Valid Author author, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "authors/addNewAuthor";
		}

		service.saveAuthor(author);

		return "redirect:/authors/";
	}

	/*
	 * This method will provide the medium to update an existing author.
	 */
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	public String editAuthor(@PathVariable Long id, ModelMap model) {
		Author author = service.findById(id);
		model.addAttribute("author", author);
		model.addAttribute("edit", true);

		return "authors/addNewAuthor";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating author in database. It also validates the user input.
	 */
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.PUT)
	public String updateAuthor(@Valid Author author, BindingResult result, ModelMap model, @PathVariable Long id) {

		if (result.hasErrors()) {
			return "authors/addNewAuthor";
		}

		service.updateAuthor(author);

		return "redirect:/authors/";
	}

	/*
	 * This method will delete an author by it's ID value.
	 */
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.DELETE)
	public String deleteAuthor(@PathVariable Long id) {
		service.deleteAuthor(service.findById(id));

		return "redirect:/authors/";
	}
}
