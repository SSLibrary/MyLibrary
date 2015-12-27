package com.ss.academy.java.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.book.BookHistory;
import com.ss.academy.java.model.book.BookStatus;
import com.ss.academy.java.model.message.Message;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.book.BookHistoryService;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.user.UserService;
import com.ss.academy.java.util.UnreadMessagesCounter;

@Controller
@RequestMapping(value = "/{user_id}/books")
public class BookHistoryController {

	@Autowired
	UserService userService;
	
	@Autowired
	BookHistoryService bookHistoryService;
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String listBooksHistory(ModelMap model, @AuthenticationPrincipal UserDetails userDetails){
		User currentUser = userService.findByUsername(userDetails.getUsername());
		List<BookHistory> booksHistory = currentUser.getBooksHistory();
		
		List<Message> messages = currentUser.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);
		
		if (booksHistory.isEmpty()) {
			model.addAttribute("isEmpty", true);
		} else {
			model.addAttribute("isEmpty", false);
			model.addAttribute("booksHistory", booksHistory);
		}
		
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", currentUser.getId());
		
		return "users/booksHistory";
	}
	
	@RequestMapping(value = { "/{book_id}/addToHistory" }, method = RequestMethod.GET)
	public String addNewBookHistory(@PathVariable Long book_id,
			@AuthenticationPrincipal UserDetails userDetails) {

		BookHistory bookHistory = new BookHistory();
		Book book = bookService.findById(book_id);
		User user = userService.findByUsername(userDetails.getUsername());
		
		if (book.getStatus().equals(BookStatus.Available)) {
		
		user.getBooksHistory().add(bookHistory);
		book.getBooksHistory().add(bookHistory);
		bookService.changeBookStatus(book);
		bookHistory.setBook(book);
		bookHistory.setUser(user);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(bookHistory.getGetDate());
		cal.add(Calendar.DAY_OF_MONTH, 30);
		bookHistory.setReturnDate(cal.getTime());
		
		bookHistoryService.saveBookHistory(bookHistory);
		}
		
		return "redirect:/{user_id}/books/";
	}
	
	@RequestMapping(value = "/{history_id}/return", method = RequestMethod.GET)
	public String returnBook(@PathVariable Long history_id, @AuthenticationPrincipal UserDetails userDetails) {	
		BookHistory bookHistory = bookHistoryService.findById(history_id);
		
		if (bookHistory.getIsReturned() == 0 && bookHistory.getBook().getStatus().equals(BookStatus.Loaned)) {
		Date currDate = new Date(System.currentTimeMillis());
		bookHistory.setReturnDate(currDate);
		bookHistoryService.updateBookHistory(bookHistory);
		bookService.changeBookStatus(bookHistory.getBook());
		}
		
		return "redirect:/{user_id}/books/";
	}
}
