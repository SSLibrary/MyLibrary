package com.ss.academy.java.dao.message;

import java.util.List;

import com.ss.academy.java.model.message.Message;


public interface MessageDao {
	
	Message findById(Integer message_id);
	
	void saveMessage(Message message);
	
	List<Message> findAllMessages();
	
}
