package com.ss.academy.java.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	public String listAllUsers(@AuthenticationPrincipal UserDetails userDetails, ModelMap model, Integer offset,
			Integer maxResults) {
		User currentUser = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = currentUser.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);

		// Removing the current user from the list of users
		List<User> allUsers = userService.listAllUsers(offset, maxResults);
		List<User> filteredList = new ArrayList<User>();

		for (User user : allUsers) {
			if (!user.getUsername().equals(currentUser.getUsername())) {
				filteredList.add(user);
			}
		}

		model.addAttribute("allUsers", filteredList);
		model.addAttribute("count", userService.countAllUsers());
		model.addAttribute("offset", offset);
		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("used_id", currentUser.getId());

		return "users/all";
	}

	/*
	 * This method provides the ability to search for user by its username.
	 */
	@RequestMapping(value = { "/users/search" }, method = RequestMethod.GET)
	public String searchUserByUsername(@RequestParam("username") String username, ModelMap model,
			@AuthenticationPrincipal UserDetails userDetails) {
		User currentUser = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = currentUser.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);

		List<User> users = userService.findUsersByUserName(username);
		model.addAttribute("allUsers", users);
		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("used_id", currentUser.getId());

		return "users/all";
	}

	/*
	 * This method provides the ability the admin to be able to update the
	 * users' status.
	 */
	@RequestMapping(value = { "/users/{user_id}" }, method = RequestMethod.PUT)
	public String updateUserStatus(ModelMap model, @PathVariable String user_id) {
		User user = userService.findById(user_id);
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

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
		User currentUser = userService.findByUsername(userDetails.getUsername());
		List<Message> messages = currentUser.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);

		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("used_id", currentUser.getId());
		model.addAttribute("username", currentUser.getUsername());

		return "users/accessDenied";
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
	public String registerUser(@ModelAttribute @Valid User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "users/register";
		}

		if (userService.isUsernameUnique(user.getUsername())) {
			FieldError loginError = new FieldError("username", "username", messageSource
					.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(loginError);
			return "users/register";
		}

		userService.saveUser(user);

		model.addAttribute("newUser", user.getUsername());

		return "users/registrationSuccess";
	}

	/*
	 * This method will provide the medium to update current user profile
	 * details.
	 */
	@RequestMapping(value = "users/{user_id}/profile", method = RequestMethod.GET)
	public String showMyProfile(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String user_id,
			ModelMap model) {
		User currentUser = userService.findByUsername(userDetails.getUsername());

		List<Message> messages = currentUser.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);

		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("used_id", currentUser.getId());
		model.addAttribute("user", currentUser);

		return "users/editProfile";
	}

	/*
	 * This method will be called on form submission, handling PUT request for
	 * editing user profile in database. It also validates the user input and
	 * check whether the entered password match with the one stored in the DB.
	 */
	@RequestMapping(value = "users/{user_id}/profile", method = RequestMethod.POST)
	public String editMyProfile(@ModelAttribute @Valid User user, BindingResult result,
			@AuthenticationPrincipal UserDetails userDetails, ModelMap model) {
		User currentUser = userService.findByUsername(userDetails.getUsername());
		
		List<Message> messages = currentUser.getReceivedMessage();
		int unreadMessages = UnreadMessagesCounter.count(messages);

		model.addAttribute("unreadMessages", unreadMessages);
		model.addAttribute("used_id", currentUser.getId());
		model.addAttribute("user", currentUser);

		if (result.hasFieldErrors("firstName") || result.hasFieldErrors("lastName") || result.hasFieldErrors("email")) {
			return "users/editProfile";
		}
		
		userService.updateUser(user);

		return "users/editProfileSuccess";
	}
}
