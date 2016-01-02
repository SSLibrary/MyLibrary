package com.ss.academy.java.util;

import java.util.List;

import com.ss.academy.java.model.message.Message;

public class UnreadMessagesCounter {

	private static UnreadMessagesCounter instance;

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private UnreadMessagesCounter() {
	}

	/* Static 'instance' method */
	public static UnreadMessagesCounter getInstance() {
		if (instance == null) {
			instance = new UnreadMessagesCounter();
		}
		
		return instance;
	}

	/* Other methods protected by singleton-ness */
	public static int count(List<Message> messages) {
		int counter = 0;
		for (Message message : messages) {
			if (message.getIsNew() == 1) {
				counter++;
			}
		}
		
		return counter;
	}
}
