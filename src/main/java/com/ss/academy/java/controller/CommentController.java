package com.ss.academy.java.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.academy.java.model.author.Author;
import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.comment.Comment;
import com.ss.academy.java.model.message.Message;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.author.AuthorService;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.comment.CommentService;
import com.ss.academy.java.service.message.MessageService;
import com.ss.academy.java.service.user.UserService;
import com.ss.academy.java.util.UnreadMessagesCounter;

@Controller
@RequestMapping(value = { "/authors/{author_id}/books/{book_id}" })
public class CommentController {

	@Autowired
	AuthorService authorService;

	@Autowired
	BookService bookService;

	@Autowired
	CommentService commentService;

	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;

	/*
	 * Show all comments by book
	 */
	@RequestMapping(value = { "/comments" }, method = RequestMethod.GET)
	public String listAllComments(@PathVariable Long book_id, @PathVariable Long author_id, ModelMap model,
			@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);

		Author author = authorService.findById(author_id);
		Book book = bookService.findById(book_id);
		List<Comment> comments = book.getComments();
		
		model.addAttribute("isEmpty", false);

		if (comments.isEmpty()) {
			model.addAttribute("isEmpty", true);
		}

		model.addAttribute("comments", comments);
		model.addAttribute("author", author.getName());
		model.addAttribute("book", book.getTitle());
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", user.getId());

		return "comments/allComments";
	}

	/*
	 * Add new comment
	 */
	@RequestMapping(value = { "/comments/new" }, method = RequestMethod.GET)
	public String addNewComment(ModelMap model, @PathVariable Long book_id,
			@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);

		Comment comment = new Comment();
		Book book = bookService.findById(book_id);
		
		model.addAttribute("comment", comment);
		model.addAttribute("book", book.getTitle());
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", user.getId());

		return "comments/addNewComment";
	}

	/*
	 * Save comment
	 */
	@RequestMapping(value = { "/comments/new" }, method = RequestMethod.POST)
	public String saveComment(@Valid Comment comment, BindingResult result, ModelMap model,
			@PathVariable Long book_id) {
		if (result.hasErrors()) {
			return "comments/addNewComment";
		}

		Book book = bookService.findById(book_id);
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.getComments().add(comment);
		book.getComments().add(comment);
		comment.setUser(user);
		comment.setBook(book);
		commentService.saveComment(comment);

		return "redirect:/authors/{author_id}/books/{book_id}/comments";
	}

	/*
	 * Delete comment
	 */
	@RequestMapping(value = { "/comments/{comment_id}" }, method = RequestMethod.DELETE)
	public String deleteComment(@PathVariable Long book_id, @PathVariable Integer comment_id) {
		Comment comment = commentService.findById(comment_id);
		Book book = bookService.findById(book_id);
		book.getComments().remove(comment);
		commentService.deleteCommentById(comment_id);

		return "redirect:/authors/{author_id}/books/{book_id}/comments";
	}
}
