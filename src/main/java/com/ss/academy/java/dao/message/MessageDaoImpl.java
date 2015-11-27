package com.ss.academy.java.dao.message;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.ss.academy.java.dao.AbstractDao;
import com.ss.academy.java.model.message.Message;


@Repository("messageDao")
public class MessageDaoImpl extends AbstractDao<Integer, Message> implements MessageDao  {

	public Message findById(Integer message_id) {
		return getByKey(message_id);
	}

	public void saveMessage(Message message) {
		persist(message);
		
	}

	@SuppressWarnings("unchecked")
	public List<Message> findAllMessages() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("date"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Message> messages = (List<Message>) criteria.list();     
        return messages;
	}
	
}
