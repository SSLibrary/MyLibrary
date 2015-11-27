package com.ss.academy.java.service.message;

import java.util.List;

import com.ss.academy.java.model.message.Message;


public interface MessageService {
		
		Message findById(Integer message_id);
		
		void saveMessage(Message message);
		
		List<Message> findAllMessages();
		
		void updateMessageStatus(Message message);
	
}
