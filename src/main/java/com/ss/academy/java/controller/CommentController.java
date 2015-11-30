package com.ss.academy.java.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.academy.java.model.author.Author;
import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.comment.Comment;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.author.AuthorService;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.comment.CommentService;
import com.ss.academy.java.service.user.UserService;


@Controller
@RequestMapping(value = { "/authors/{author_id}/books/{book_id}"})
public class CommentController {

	@Autowired
	AuthorService authorService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;

	/*
	 * Show all comments by book
	 */
	@RequestMapping(value = { "/comments" }, method = RequestMethod.GET)
	public String listAllComments(@PathVariable Long book_id, @PathVariable Long author_id, ModelMap model) {
		
		Author author = authorService.findById(author_id);
		Book book = bookService.findById(book_id);
		List<Comment> comments = book.getComment();
		model.addAttribute("isEmpty", false);
		
		if (comments.isEmpty()) {
			model.addAttribute("isEmpty", true);
		} 
		
		model.addAttribute("comments", comments);
		model.addAttribute("author", author.getName());
		model.addAttribute("book", book.getTitle());
		return "comments/allComments";
	}
	
	/*
	 *  Add new comment
	 */
	@RequestMapping(value = { "/comments/new" }, method = RequestMethod.GET)
	public String addNewComment(ModelMap model, @PathVariable Long book_id) {
		Comment comment = new Comment();
		Book book = bookService.findById(book_id);
		model.addAttribute("comment", comment);
		model.addAttribute("book", book.getTitle());
		return "comments/addNewComment";
	}

	/*
	 *  Save comment
	 */
	@RequestMapping(value = { "/comments/new" }, method = RequestMethod.POST)
	public String saveComment(@Valid Comment comment, BindingResult result, ModelMap model, @PathVariable Long book_id) {

		if (result.hasErrors()) {
			return "comments/addNewComment";
		}

		Book book = bookService.findById(book_id);
		User user = userService.findByUsername(
				SecurityContextHolder.getContext().getAuthentication().getName());
		
		user.getComment().add(comment);
		book.getComment().add(comment);
		comment.setUser(user);
		comment.setBook(book);
		commentService.saveComment(comment);
		return "redirect:/authors/{author_id}/books/{book_id}/comments";
	}
	
	/*
	 *  Delete comment
	 */
	@RequestMapping(value = { "/comments/{comment_id}" }, method = RequestMethod.DELETE)
	public String deleteComment(@PathVariable Long book_id, @PathVariable Integer comment_id) {
		Comment comment = commentService.findById(comment_id);
		Book book = bookService.findById(book_id);

		book.getComment().remove(comment);
		commentService.deleteCommentById(comment_id);
		return "redirect:/authors/{author_id}/books/{book_id}/comments";
	}
	
}