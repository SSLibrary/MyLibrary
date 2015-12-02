package com.ss.academy.java.util;

import java.util.List;

import com.ss.academy.java.model.message.Message;


public class UnreadMessagesCounter {

	public static int counter(List<Message> messages) {
	
	int counter = 0;
	for(Message message : messages) {
		if (message.getIsNew() == 1) {
			counter++;
		}
	}
	return counter;
	}
}
