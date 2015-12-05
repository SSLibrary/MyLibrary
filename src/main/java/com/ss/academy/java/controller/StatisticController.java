package com.ss.academy.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ss.academy.java.service.author.AuthorService;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.user.UserService;
import com.ss.academy.java.util.UnreadMessagesCounter;
import com.ss.academy.java.model.author.Author;
import com.ss.academy.java.model.message.Message;
import com.ss.academy.java.model.user.User;

@Controller
//@RequestMapping(value = "statistics/choiceList")
public class StatisticController {

	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = { "/statistics/choiceList" }, method = RequestMethod.GET)
	public String choiceList(@AuthenticationPrincipal UserDetails user, ModelMap model) {
		User currentUser = userService.findByUsername(user.getUsername());
		List<Message> messages = currentUser.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);
		model.addAttribute("logged", true);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", currentUser.getId());
		
				
		return "statistics/choiceList";
	}
	
//	
	
	@RequestMapping(value = { "statistics/authorCount" }, method = RequestMethod.GET)
	public String authorCount(@AuthenticationPrincipal UserDetails user, ModelMap model) {
		int authorsCount = authorService.findAllAuthors().size();
		model.addAttribute("authorsCount", authorsCount);
		
		User currentUser = userService.findByUsername(user.getUsername());
		List<Message> messages = currentUser.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);
		model.addAttribute("logged", true);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", currentUser.getId());
		
		List<Author> authors = authorService.findAllAuthors();
		model.addAttribute("authors", authors);
		
		return "statistics/authorCount";
	}
	
		
	@RequestMapping(value = { "statistics/booksCount" }, method = RequestMethod.GET)
	public String bookCount(@AuthenticationPrincipal UserDetails user, ModelMap model) {
		int booksCount = bookService.findAllBooks().size();
		
		User currentUser = userService.findByUsername(user.getUsername());
		List<Message> messages = currentUser.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);
		model.addAttribute("logged", true);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", currentUser.getId());
		
		model.addAttribute("booksCount", booksCount);
	
		return "statistics/booksCount";
	}
	
	@RequestMapping(value = { "statistics/userCount" }, method = RequestMethod.GET)
	public String userCount(@AuthenticationPrincipal UserDetails user, ModelMap model) {
		int userCount = userService.findAllUsers().size();
		
		User currentUser = userService.findByUsername(user.getUsername());
		List<Message> messages = currentUser.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);
		model.addAttribute("logged", true);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", currentUser.getId());
		
		model.addAttribute("userCount", userCount);
		return "statistics/userCount";
	}

}
