package com.ss.academy.java.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.academy.java.model.message.Message;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.book.BookService;
import com.ss.academy.java.service.message.MessageService;
import com.ss.academy.java.service.user.UserService;
import com.ss.academy.java.util.UnreadMessagesCounter;

/**
 * Handles requests for the application users page plus user login/registration.
 */
@Controller
@RequestMapping(value = "/")
public class UsersController {

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	MessageService messageService;

	/*
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	public String listAllUsers(@AuthenticationPrincipal UserDetails userDetails, ModelMap model, Integer offset,
			Integer maxResults) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = user.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(messages);

		model.addAttribute("allUsers", userService.list(offset, maxResults));
		model.addAttribute("count", userService.count());
		model.addAttribute("offset", offset);
		model.addAttribute("unread", unread);

		return "users/all";
	}

	/*
	 * This method provides the ability to search for user by its username.
	 */
	@RequestMapping(value = { "/users/search" }, method = RequestMethod.GET)
	public String searchUserByUsername(@RequestParam("username") String username, ModelMap model) {
		List<User> users = userService.findUsersByUserName(username);
		model.addAttribute("allUsers", users);

		return "users/all";
	}

	/*
	 * This method provides the ability the admin to be able to update the
	 * users' status.
	 */
	@RequestMapping(value = { "/users/{id}" }, method = RequestMethod.PUT)
	public String updateUserStatus(ModelMap model, @PathVariable Long id) {
		User user = userService.findById(id);

		userService.updateUserStatus(user);

		return "redirect:/users/";
	}

	/*
	 * This method will provide the medium to log in existing user into the
	 * system.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "users/login";
	}

	/*
	 * This method will provide the medium to register a new user.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);

		return "users/register";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input and check
	 * whether the username is unique.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute @Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "users/register";
		}

		if (!userService.isUsernameUnique(user.getId(), user.getUsername())) {
			FieldError loginError = new FieldError("username", "username", messageSource
					.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(loginError);
			return "users/register";
		}

		userService.save(user);

		return "redirect:/authors/";
	}
}
