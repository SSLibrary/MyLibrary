package com.ss.academy.java.controller;

import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.history.HistoryService;
import com.ss.academy.java.service.user.UserService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = { "/authors/{id}/books" })
public class HistoryController {

	@Autowired
	UserService userService;

	@Autowired
	BookService bookService;

	@Autowired
	HistoryService historyService;

	@RequestMapping(value = { "/{book_id}" }, method = { RequestMethod.GET })
	public String getBook(Long user_id, Long book_id) {
		User user = this.userService.findById(user_id);
		Book book = this.bookService.findById(book_id);
		this.historyService.getBook(user, book);
		return "redirect:/myhistory";
	}

	@RequestMapping(value = { "/{book_id}" }, method = { RequestMethod.GET })
	public String returnBook(Long book_id) {
		Book book = this.bookService.findById(book_id);
		historyService.returnBook(book);
		return "redirect:/myhistory";
	}

	@RequestMapping(value = { "/history" }, method = RequestMethod.GET)
	public String showMyHistory(HttpServletRequest request, ModelMap model, Long userId,
			@AuthenticationPrincipal UserDetails userDetails) {

		List<Book> myBooks = historyService.showMyHistory(userId);

		if (myBooks.isEmpty()) {
			model.addAttribute("emptyListOfBooks", true);
		}
		model.addAttribute("books", myBooks);

		return "/history";
	}

}
