package com.ss.academy.java.controller;

import java.util.ArrayList;
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

import com.ss.academy.java.model.message.Message;
import com.ss.academy.java.model.user.User;
import com.ss.academy.java.service.message.MessageService;
import com.ss.academy.java.service.user.UserService;
import com.ss.academy.java.util.UnreadMessagesCounter;

/**
 * Handles requests for the application messaging service.
 */
@Controller
@RequestMapping(value = { "{user_id}/messages" })
public class MessageController {

	private final byte UNREAD_MESSAGE = 1;
	private final byte READ_MESSAGE = 0;
	private final byte NOT_REPLIED = 0;
	
	@Autowired
	MessageService messageService;

	@Autowired
	UserService userService;
	
	/*
	 * This method will list all messages which the Authenticated user has received
	 */
	@RequestMapping(value = { "/inbox" }, method = RequestMethod.GET)
	public String listAllReceivedMessages(ModelMap model, @AuthenticationPrincipal UserDetails userDetails,
			Integer offset, Integer maxResults, String username) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> allMessages = user.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(allMessages);

		List<Message> messages = messageService.listAllReceivedMessages(offset, maxResults, userDetails.getUsername());
		Long count = messageService.countReceivedMessages(userDetails.getUsername());

		model.addAttribute("isEmpty", messages.isEmpty());
		model.addAttribute("messages", messages);
		model.addAttribute("count", count);
		model.addAttribute("offset", offset);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", user.getId());

		return "messages/inbox";
	}

	/*
	 * This method will list all messages which the Authenticated user has sent
	 */
	@RequestMapping(value = { "/outbox" }, method = RequestMethod.GET)
	public String listAllSentMessages(ModelMap model, @AuthenticationPrincipal UserDetails userDetails, Integer offset,
			Integer maxResults, String username) {
		User user = userService.findByUsername(userDetails.getUsername());
		List<Message> allMessages = user.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(allMessages);

		List<Message> messages = messageService.listAllSentMessages(offset, maxResults, userDetails.getUsername());
		Long count = messageService.countSentMessages(userDetails.getUsername());

		model.addAttribute("isEmpty", messages.isEmpty());
		model.addAttribute("messages", messages);
		model.addAttribute("count", count);
		model.addAttribute("offset", offset);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", user.getId());

		return "messages/outbox";

	}

	/*
	 * This method will create new message
	 */
	@RequestMapping(value = { "/{user_id}/new" }, method = RequestMethod.GET)
	public String sendNewMessage(ModelMap model, @PathVariable String user_id,
			@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findById(user_id);
		
		if (user == null) {
			return "redirect:/{user_id}/messages/inbox";
		}
		
		User currentUser = userService.findByUsername(userDetails.getUsername());
		List<Message> allMessages = currentUser.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(allMessages);
	
		Message message = new Message();
		model.addAttribute("message", message);
		model.addAttribute("receiver", user.getUsername());
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", currentUser.getId());

		return "messages/new";
	}

	/*
	 * This method will save and send the newly created message.
	 */
	@RequestMapping(value = { "/{user_id}/new" }, method = RequestMethod.POST)
	public String saveMessage(@Valid Message message, BindingResult result, ModelMap model,
			@AuthenticationPrincipal UserDetails userDetails, @PathVariable String user_id) {
		if (result.hasErrors()) {
			return "messages/new";
		}

		User receiver = userService.findById(user_id);
		User sender = userService.findByUsername(userDetails.getUsername());
		
		if (receiver.getUsername().equals(sender.getUsername())) {
			return "redirect:/{user_id}/messages/inbox";
		}
		
		sender.getSentMessage().add(message);
		receiver.getReceivedMessage().add(message);
		message.setReceiver(receiver);
		message.setSender(sender);
		messageService.saveMessage(message);

		return "redirect:/{user_id}/messages/outbox";
	}

	/*
	 * This method will create new message.
	 */
	@RequestMapping(value = { "/{message_id}/reply" }, method = RequestMethod.GET)
	public String replyToMessage(ModelMap model, @PathVariable Integer message_id,
			@AuthenticationPrincipal UserDetails userDetails) {	
		Message parent = messageService.findById(message_id);
		if (parent == null) {
			return "redirect:/{user_id}/messages/inbox";
		}
		
		if (!parent.getReceiver().getUsername().equals(userDetails.getUsername()) &&
				!parent.getSender().getUsername().equals(userDetails.getUsername())) {
			return "redirect:/{user_id}/messages/inbox";
		}
		
		if (parent.getIsNew() == UNREAD_MESSAGE) {
			parent.setIsNew(READ_MESSAGE);
			messageService.updateMessageStatus(parent);
		}
	
		User currentUser = userService.findByUsername(userDetails.getUsername());
		List<Message> allMessages = currentUser.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(allMessages);

		List<Message> previousMessages = new ArrayList<Message>();
		previousMessages.add(parent);

		while (parent.getIn_reply_to() != NOT_REPLIED) {
			parent = messageService.findById(parent.getIn_reply_to());
			previousMessages.add(parent);
		}

		Message message = new Message();
		model.addAttribute("message", message);
		model.addAttribute("parents", previousMessages);
		model.addAttribute("receiver", parent.getSender().getUsername());
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", currentUser.getId());

		return "messages/reply";
	}

	/*
	 * This method will save and reply to the previous message in the thread.
	 */
	@RequestMapping(value = { "/{message_id}/reply" }, method = RequestMethod.POST)
	public String replyToMessage(@Valid Message message, BindingResult result, ModelMap model,
			@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer message_id) {
		if (result.hasErrors()) {
			return "messages/reply";
		}

		Message parent = messageService.findById(message_id);
		User receiver = userService.findByUsername(parent.getSender().getUsername());
		User sender = userService.findByUsername(userDetails.getUsername());
		sender.getSentMessage().add(message);
		receiver.getReceivedMessage().add(message);
		message.setReceiver(receiver);
		message.setSender(sender);
		message.setIn_reply_to(parent.getMessage_id());
		message.setHeader("Re: " + parent.getHeader());
		messageService.saveMessage(message);

		return "redirect:/{user_id}/messages/outbox";
	}

	/*
	 * This method will display all message in the message thread.
	 */
	@RequestMapping(value = { "/{message_id}/display" }, method = RequestMethod.GET)
	public String displayMessage(ModelMap model, @PathVariable Integer message_id,
			@AuthenticationPrincipal UserDetails userDetails) {
		User currentUser = userService.findByUsername(userDetails.getUsername());
		List<Message> allMessages = currentUser.getReceivedMessage();
		int unread = UnreadMessagesCounter.counter(allMessages);

		Message parent = messageService.findById(message_id);
		
		if (parent == null) {
			return "redirect:/{user_id}/messages/outbox";
		}
		
		if (!parent.getReceiver().getUsername().equals(userDetails.getUsername()) &&
				!parent.getSender().getUsername().equals(userDetails.getUsername())) {
			return "redirect:/{user_id}/messages/outbox";
		}
		
		
		List<Message> previousMessages = new ArrayList<Message>();
		previousMessages.add(parent);

		while (parent.getIn_reply_to() != NOT_REPLIED) {
			parent = messageService.findById(parent.getIn_reply_to());
			previousMessages.add(parent);
		}

		model.addAttribute("parents", previousMessages);
		model.addAttribute("unread", unread);
		model.addAttribute("currUser", currentUser.getId());

		return "messages/display";
	}
}
