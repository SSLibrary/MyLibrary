package com.ss.academy.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.academy.java.model.author.Author;
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
	
	   /*
     * This method will list all existing authors.
     */ 
    @RequestMapping(value = { "/authors" }, method = RequestMethod.GET)
  public String listAuthors(HttpServletRequest request,ModelMap model, Integer offset, Integer maxResults) { 

        List<Author> authors = authorService.list(offset, maxResults); 
        
      if (authors.isEmpty()) {
			model.addAttribute("emptyListOfAuthors", true);
		}        
      model.addAttribute("authors", authorService.list(offset, maxResults));
      model.addAttribute("count", authorService.count());
      model.addAttribute("offset", offset);

      return "authors/all";
  }   
}