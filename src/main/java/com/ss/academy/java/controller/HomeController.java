package com.ss.academy.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ss.academy.java.service.author.AuthorService;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.user.UserService;

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

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/" })
	public String home(@AuthenticationPrincipal UserDetails user, ModelMap model) {
		int authorsCount = authorService.findAllAuthors().size();
		int booksCount = bookService.findAllBooks().size();
		int usersCount = userService.findAllUsers().size();
		
		model.addAttribute("authorsCount", authorsCount);
		model.addAttribute("booksCount", booksCount);
		model.addAttribute("usersCount", usersCount);
		
		return "home";
	}
	

}