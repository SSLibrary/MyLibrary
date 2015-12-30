package com.ss.academy.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ss.academy.java.model.author.Author;
import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.message.Message;
import com.ss.academy.java.model.rating.Rating;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.author.AuthorService;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.rating.RatingService;
import com.ss.academy.java.service.user.UserService;
import com.ss.academy.java.util.UnreadMessagesCounter;

import sun.misc.BASE64Encoder;

/**
 * Handles requests for the application authors' books page.
 */
@SuppressWarnings("restriction")
@Controller
@RequestMapping(value = "/authors/{author_id}/books")
public class AuthorBooksController {

	@Autowired
	BookService bookService;

	@Autowired
	AuthorService authorService;

	@Autowired
	RatingService ratingService;

	@Autowired
	UserService userService;

	/*
	 * This method will list all existing books and will check whether they have
	 * been rated so far by the current user.
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String listAllBooks(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long author_id,
			ModelMap model, Integer offset, Integer maxResults) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);

		Author author = authorService.findById(author_id);
		List<Book> books = bookService.listAllBooks(offset, maxResults, author_id);
		Long count = bookService.countAllBooks(author_id);

		if (books.isEmpty()) {
			model.addAttribute("emptyList", true);
		} else {
			for (Book book : books) {
				List<Rating> bookRatings = book.getRatings();
				for (Rating rating : bookRatings) {
					if (rating.getUser().getUsername().equals(userDetails.getUsername())) {
						book.setIsRated(true);
						break;
					}
				}
			}
		}
		model.addAttribute("books", books);
		model.addAttribute("count", count);
		model.addAttribute("offset", offset);
		model.addAttribute("author", author);
		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("user_id", user.getId());

		return "books/allAuthorBooks";
	}

	@RequestMapping(value = { "/{book_id}/preview" }, method = RequestMethod.GET)
	public String previewBook(@PathVariable Long book_id, ModelMap model,
			@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);

		Book book = bookService.findById(book_id);

		try {
			byte[] bytes = book.getImage();
			if (bytes.length == 0) {
				model.addAttribute("emptyList", true);
			}
			BASE64Encoder base64Encoder = new BASE64Encoder();
			StringBuilder imageString = new StringBuilder();
			imageString.append("data:image/png;base64,");
			imageString.append(base64Encoder.encode(bytes));

			String image = imageString.toString();
			model.addAttribute("image", image);
		} catch (NullPointerException e) {
			model.addAttribute("emptyList", true);
		}

		model.addAttribute("user_id", user.getId());
		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("book", book);

		return "books/bookPreview";
	}

	/*
	 * This method provides the ability to search for books by their titles.
	 */
	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String searchBookByName(@PathVariable Long author_id, @RequestParam("bookTitle") String bookTitle,
			ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);

		List<Book> books = bookService.findBooksByTitle(bookTitle);
		List<Book> authorBooks = new ArrayList<Book>();

		for (Book book : books) {
			if (book.getAuthor().getId() == author_id) {
				authorBooks.add(book);
			}
		}

		model.addAttribute("books", authorBooks);
		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("user_id", user.getId());

		return "books/allAuthorBooks";
	}

	/*
	 * This method will provide the medium to add a new book.
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String addNewBook(ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {

		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);

		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("edit", false);
		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("user_id", user.getId());

		return "books/addNewBook";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving book in database. It also validates the user input.
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveBook(@Valid Book book, BindingResult result, @RequestParam CommonsMultipartFile[] fileUpload,
			@PathVariable Long author_id) {
		if (result.hasErrors()) {
			return "books/addNewBook";
		}

		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {
				if (aFile.toString().startsWith("FF D8 FF")) {
					// check if format of file is JPG
				} else if (aFile.toString().startsWith("47 49 46 38 37 61")
						|| aFile.toString().startsWith("47 49 46 38 39 61")) {
					// check if format of file is GIF
				} else if (aFile.toString().startsWith("89 50 4E 47 0D 0A 1A 0A")) {
					// check if format of file is PNG
				}
				Author author = authorService.findById(author_id);
				author.getBooks().add(book);
				book.setAuthor(author);
				book.setImage(aFile.getBytes());

				bookService.saveBook(book);
			}
		}
		return "redirect:/authors/{author_id}/books/";
	}

	/*
	 * This method will provide the medium to update an existing book.
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/{book_id}" }, method = RequestMethod.GET)
	public String editBook(@PathVariable Long book_id, ModelMap model,
			@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);

		Book book = bookService.findById(book_id);
		Author author = book.getAuthor();

		model.addAttribute("book", book);
		model.addAttribute("author", author);
		model.addAttribute("edit", true);
		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("user_id", user.getId());

		return "books/addNewBook";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating book in database. It also validates the user input.
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/{book_id}" }, method = RequestMethod.POST)
	public String updateBook(@Valid Book formBook, BindingResult result,
			@RequestParam CommonsMultipartFile[] fileUpload, @PathVariable Long book_id, @PathVariable Long author_id) {
		Author author = new Author();
		Book dbBook = new Book();
		if (result.hasErrors()) {
			return "books/addNewBook";
		}

		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {
				if (aFile.toString().startsWith("FF D8 FF")) {
					// check if format of file is JPG
				} else if (aFile.toString().startsWith("47 49 46 38 37 61")
						|| aFile.toString().startsWith("47 49 46 38 39 61")) {
					// check if format of file is GIF
				} else if (aFile.toString().startsWith("89 50 4E 47 0D 0A 1A 0A")) {
					// check if format of file is PNG
				}
				author = authorService.findById(author_id);
				dbBook = bookService.findById(book_id);
				formBook.setImage(aFile.getBytes());
				dbBook = formBook;
				author.getBooks().add(dbBook);

				bookService.updateBook(dbBook);
			}
		}
		return "redirect:/authors/{author_id}/books/";
	}

	/*
	 * This method will delete a book by it's ID value.
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/{book_id}" }, method = RequestMethod.DELETE)
	public String deleteBook(@PathVariable Long author_id, @PathVariable Long book_id) {
		Book book = bookService.findById(book_id);
		Author author = authorService.findById(author_id);

		author.getBooks().remove(book);
		bookService.deleteBook(book);

		return "redirect:/authors/{author_id}/books/";
	}
}
