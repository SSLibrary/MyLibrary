package com.ss.academy.java.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
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
import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.book.BookStatus;
import com.ss.academy.java.model.message.Message;
import com.ss.academy.java.model.rating.Rating;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.author.AuthorService;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.item.ItemService;
import com.ss.academy.java.service.message.MessageService;
import com.ss.academy.java.service.rating.RatingService;
import com.ss.academy.java.service.user.UserService;
import com.ss.academy.java.util.UnreadMessagesCounter;

/**
 * Handles requests for the application authors' books page.
 */
@Controller
@RequestMapping(value = "/authors/{id}/books")
public class BooksController {

	@Autowired
	ItemService itemService;
	
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
	 * This method will list all existing books and will check whether they have
	 * been rated so far by the current user.
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String listAllBooks(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, ModelMap model,
			Integer offset, Integer maxResults){
		
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();	
		int unread = UnreadMessagesCounter.counter(messages);
		
		
		
		Author author = authorService.findById(id);		
		List<Book> books = bookService.list(offset, maxResults, id);	
		Long count = bookService.count(id);
		 
		if (books.size() == 0) {
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
		model.addAttribute("unread", unread);
		return "books/all";
	}
	
	 
		@RequestMapping(value = { "/{id}/images" }, method = RequestMethod.GET)
		public String listBooksItems(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, ModelMap model,
				Integer offset, Integer maxResults,HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
			
			User user = userService.findByUsername(userDetails.getUsername());
			List<Message> messages = user.getReceivedMessage();	
			int unread = UnreadMessagesCounter.counter(messages);		
			
			Author author = authorService.findById(id);		
			List<Book> books = bookService.list(offset, maxResults, id);	
			Long count = bookService.count(id);
			 
		
			byte[] image = itemService.findById(id).getItemContent();
			
			Book findBook = bookService.findById(id);
			byte[] itemssssss = itemService.findById(id).getItemContent();
//			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");//		  
//			byte[] encodeBase64 = Base64.encodeBase64(itemssssss);//			 
//	   	  	String base64Encoded= new String(encodeBase64, "UTF-8");
//		  
//		    model.addAttribute("galleria", base64Encoded );
//		    response.getOutputStream().close();
			model.addAttribute("books", books);		
			model.addAttribute("count", count);			
			model.addAttribute("offset", offset);
			model.addAttribute("author", author);
			model.addAttribute("unread", unread);
			return "books/all";
		}

	/*
	 * This method provides the ability to search for books by their titles.
	 */
	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String searchBookByName(@PathVariable Long id, @RequestParam("bookTitle") String bookTitle, ModelMap model) {
		List<Book> books = bookService.findBooksByTitle(bookTitle);
		
		List<Book> authorBooks = new ArrayList<Book>();
		
		for (Book book : books) {
			if (book.getAuthor().getId() == id) {
				authorBooks.add(book);
			}
		}

		model.addAttribute("books", authorBooks);

		return "books/all";
	}

	/*
	 * This method will provide the medium to add a new book.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String addNewBook(ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
		
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();	
		int unread = UnreadMessagesCounter.counter(messages);
		
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("edit", false);
		model.addAttribute("statuses", BookStatus.values());
		model.addAttribute("unread", unread);

		return "books/addNewBook";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving book in database. It also validates the user input.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveBook(@Valid Book book, BindingResult result, @PathVariable Long id) {

		if (result.hasErrors()) {
			return "books/addNewBook";
		}

		Author author = authorService.findById(id);
		author.getBooks().add(book);
		book.setAuthor(author);
		bookService.saveBook(book);

		return "redirect:/authors/{id}/books/";
	}

	/*
	 * This method will provide the medium to update an existing book.
	 */
	@RequestMapping(value = { "/{book_id}" }, method = RequestMethod.GET)
	public String editBook(@PathVariable Long id, @PathVariable Long book_id, ModelMap model,
			@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();	
		int unread = UnreadMessagesCounter.counter(messages);
		
		Book book = bookService.findById(book_id);
		Author author = book.getAuthor();

		model.addAttribute("book", book);
		model.addAttribute("author", author);
		model.addAttribute("edit", true);
		model.addAttribute("statuses", BookStatus.values());
		model.addAttribute("unread", unread);

		return "books/addNewBook";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating book in database. It also validates the user input.
	 */
	@RequestMapping(value = { "/{book_id}" }, method = RequestMethod.PUT)
	public String updateBook(@Valid Book formBook, BindingResult result, @PathVariable Long id,
			@PathVariable Long book_id) {

		if (result.hasErrors()) {
			return "books/addNewBook";
		}

		Author author = authorService.findById(id);
		Book dbBook = bookService.findById(book_id);

		dbBook = formBook;

		bookService.updateBook(dbBook);
		author.getBooks().add(dbBook);

		return "redirect:/authors/{id}/books/";
	}

	/*
	 * This method will delete a book by it's ID value.
	 */
	@RequestMapping(value = { "/{book_id}" }, method = RequestMethod.DELETE)
	public String deleteBook(@PathVariable Long id, @PathVariable Long book_id) {
		Book book = bookService.findById(book_id);
		Author author = authorService.findById(id);

		author.getBooks().remove(book);
		bookService.deleteBook(book);

		return "redirect:/authors/{id}/books/";
	}
}
