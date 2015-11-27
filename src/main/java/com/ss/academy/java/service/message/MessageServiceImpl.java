package com.ss.academy.java.service.message;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.academy.java.dao.message.MessageDao;
import com.ss.academy.java.model.message.Message;

@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao dao;
	
	public Message findById(Integer message_id) {
		return dao.findById(message_id);
	}

	public void saveMessage(Message message) {
		dao.saveMessage(message);
		
	}

	public List<Message> findAllMessages() {
		return dao.findAllMessages();
	}

	public void updateMessageStatus(Message message) {

		Message entity = dao.findById(message.getMessage_id());

		if (entity != null) {
			entity.setIsNew(0);
		}
		
	}
	
	
}
