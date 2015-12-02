package com.ss.academy.java.dao.message;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
        List<Message> messages = (List<Message>) criteria.list();     
        return messages;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Message> listOfSentMessage(Integer offset, Integer maxResults, String username){
//		Criteria criteria =  ((Criteria) getSession()).addOrder(Order.desc("date"));
		List<Message> list= (List<Message>)getSession()
				.createCriteria(Message.class, "message")
				.createAlias("message.sender", "sm") // inner join by default				
				.add(Restrictions.eq("sm.username", username))
				.addOrder( Order.desc("date"))
				.setFirstResult(offset!=null?offset:0)
				.setMaxResults(maxResults!=null?maxResults:5)
				.list();		
		return list;
	}
	
	public Long countOfSentMessage(String username){
		return (Long)getSession()
				.createCriteria(Message.class, "message")
				.createAlias("message.sender", "sm") // inner join by default
				.add(Restrictions.eq("sm.username", username))
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Message> listOfReceivedMessage(Integer offset, Integer maxResults, String username){
		
		List<Message> list= (List<Message>) getSession()
				.createCriteria(Message.class, "message")
				.createAlias("message.receiver", "rm") // inner join by default
				.add(Restrictions.eq("rm.username", username))
				.addOrder( Order.desc("date"))
				.setFirstResult(offset!=null?offset:0)
				.setMaxResults(maxResults!=null?maxResults:5)
				.list();		
		return list;
	}
	
	public Long countOfReceivedMessage(String username){
		return (Long)getSession()
				.createCriteria(Message.class, "message")
				.createAlias("message.receiver", "rm") // inner join by default
				.add(Restrictions.eq("rm.username", username))
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
}
