package com.ss.academy.java.controller;

import java.util.List;

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

import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.message.Message;
import com.ss.academy.java.model.rating.Rating;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.author.AuthorService;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.message.MessageService;
import com.ss.academy.java.service.rating.RatingService;
import com.ss.academy.java.service.user.UserService;
import com.ss.academy.java.util.RatingCalculator;
import com.ss.academy.java.util.UnreadMessagesCounter;

/**
 * Handles requests for the application books's rating page.
 */
@Controller
@RequestMapping(value = "/authors/{author_id}/books/{book_id}")
public class RatingsController {

	@Autowired
	BookService bookService;

	@Autowired
	AuthorService authorService;

	@Autowired
	RatingService ratingService;

	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;

	/*
	 * This method will provide the medium to add a new rating.
	 */
	@RequestMapping(value = { "/rating" }, method = RequestMethod.GET)
	public String addNewRating(@PathVariable Long book_id, ModelMap model,
			@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);

		Book book = bookService.findById(book_id);
		Rating rating = new Rating();
		
		model.addAttribute("rating", rating);
		model.addAttribute("book", book);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", user.getId());

		return "books/rating";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving book's rating in database.
	 */
	@RequestMapping(value = { "/rating" }, method = RequestMethod.POST)
	public String saveRating(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long book_id,
			@Valid Rating rating, BindingResult result, ModelMap model) {
		User user = userService.findByUsername(userDetails.getUsername());
		Rating ratingToSave = new Rating();

		ratingToSave.setBook(bookService.findById(book_id));
		ratingToSave.setRatingValue(rating.getRatingValue());
		ratingToSave.setUser(user);
		System.out.println();

		ratingService.saveRating(ratingToSave);

		return "redirect:/authors/{author_id}/books/{book_id}/preview";
	}

	/*
	 * This method provides the ability the average rating to be calculated and
	 * displayed.
	 */
	@RequestMapping(value = { "/ratingCheck" }, method = RequestMethod.GET)
	public String checkRating(@PathVariable Long book_id, ModelMap model,
			@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);

		Book book = bookService.findById(book_id);
		book.setAverageRating(RatingCalculator.calculate(book.getRatings()));
		System.out.println(book.getAverageRating());

		model.addAttribute("book", book);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", user.getId());

		return "books/ratingCheck";
	}
}
