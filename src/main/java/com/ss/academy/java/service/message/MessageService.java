package com.ss.academy.java.service.message;

import java.util.List;

import com.ss.academy.java.model.message.Message;

public interface MessageService {
		
		Message findById(Integer message_id);
		
		void saveMessage(Message message);
		
		List<Message> findAllMessages();
		
		void updateMessageStatus(Message message);
	
		List<Message> listOfSentMessage(Integer offset, Integer maxResults, String username);
		
		Long countOfSentMessage(String username);
		
		List<Message> listOfReceivedMessage(Integer offset, Integer maxResults, String username);
		
		Long countOfReceivedMessage(String username);
}
