package com.ss.academy.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.message.Message;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.user.UserService;
import com.ss.academy.java.util.UnreadMessagesCounter;

@Controller
@RequestMapping(value = { "/" })
public class BooksController {

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	/*
	 * This method will show the list of all books.
	 */
	@RequestMapping(value = { "/books" })
	public String showAllBooks(@AuthenticationPrincipal UserDetails user, ModelMap model, Integer offset,
			Integer maxResults) {
		User currentUser = userService.findByUsername(user.getUsername());
		List<Message> messages = currentUser.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);
		List<Book> books = bookService.listAllBooks(offset, maxResults);

		model.addAttribute("books", books);
		model.addAttribute("count", bookService.countAllBooks());
		model.addAttribute("offset", offset);
		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("user_id", currentUser.getId());

		return "books/allBooks";
	}

	/*
	 * This method provides the ability to search for books by their titles.
	 */
	@RequestMapping(value = { "/books/search" }, method = RequestMethod.GET)
	public String searchBookByTitle(@RequestParam("bookTitle") String bookTitle, ModelMap model,
			@AuthenticationPrincipal UserDetails user) {
		User currentUser = userService.findByUsername(user.getUsername());
		List<Message> messages = currentUser.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);
		List<Book> books = bookService.findBooksByTitle(bookTitle);

		if (books.isEmpty()) {
			model.addAttribute("isEmpty", true);
		} else {
			model.addAttribute("isEmpty", false);
			model.addAttribute("books", books);
		}

		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("user_id", currentUser.getId());

		return "books/allBooks";
	}
}
