package com.ss.academy.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ss.academy.java.model.message.Message;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.author.AuthorService;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.message.MessageService;
import com.ss.academy.java.service.user.UserService;
import com.ss.academy.java.util.UnreadMessagesCounter;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = { "/" })
public class HomeController {

	@Autowired
	AuthorService authorService;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/" })
	public String home(@AuthenticationPrincipal UserDetails user, ModelMap model) {
		model.addAttribute("logged", false);

		if (user != null) {
			User currentUser = userService.findByUsername(user.getUsername());
			List<Message> messages = currentUser.getReceivedMessage();
			int unread = UnreadMessagesCounter.counter(messages);
			model.addAttribute("logged", true);
			model.addAttribute("unread", unread);
			model.addAttribute("user", currentUser.getUsername());
		}

		int authorsCount = authorService.findAllAuthors().size();
		int booksCount = bookService.findAllBooks().size();
		int usersCount = userService.findAllUsers().size();

		model.addAttribute("authorsCount", authorsCount);
		model.addAttribute("booksCount", booksCount);
		model.addAttribute("usersCount", usersCount);

		return "home";
	}

}