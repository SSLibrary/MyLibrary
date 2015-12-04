package com.ss.academy.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.academy.java.model.author.Author;
import com.ss.academy.java.model.author.AuthorCountry;
import com.ss.academy.java.model.message.Message;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.author.AuthorService;
import com.ss.academy.java.service.message.MessageService;
import com.ss.academy.java.service.user.UserService;
import com.ss.academy.java.util.UnreadMessagesCounter;

/**
 * Handles requests for the application authors page.
 */
@Controller
@RequestMapping({ "/authors" })
public class AuthorsController {

	@Autowired
	AuthorService service;

	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;


	/*
	 * This method will list all existing authors.
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String listAuthors(HttpServletRequest request, ModelMap model, Integer offset, Integer maxResults,
			@AuthenticationPrincipal UserDetails userDetails) {

		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);

		List<Author> authors = service.list(offset, maxResults);

		if (authors.isEmpty()) {
			model.addAttribute("emptyListOfAuthors", true);
		}
		model.addAttribute("authors", service.list(offset, maxResults));
		model.addAttribute("count", service.count());
		model.addAttribute("offset", offset);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", user.getId());

		return "authors/all";
	}

	/*
	 * This method provides the ability to search for authors by their names.
	 */
	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String searchAuthorByName(@RequestParam("author_name") String author_name, 
			ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
		List<Author> authors = service.findAuthorsByName(author_name);
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);
		
		model.addAttribute("authors", authors);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", user.getId());
		
		return "authors/all";
	}
	

	/*
	 * This method will provide the medium to add a new author.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String addNewAuthor(ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {

		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);

		Author author = new Author();
		model.addAttribute("author", author);
		model.addAttribute("edit", false);
		model.addAttribute("countries", AuthorCountry.values());
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", user.getId());

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
	public String editAuthor(@PathVariable Long id, ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {

		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);

		Author author = service.findById(id);
		model.addAttribute("author", author);
		model.addAttribute("edit", true);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", user.getId());

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
