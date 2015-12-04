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

	/*
	 * Find message by message_id
	 */
	public Message findById(Integer message_id) {
		return getByKey(message_id);
	}

	/*
	 * Save message
	 */
	public void saveMessage(Message message) {
		persist(message);
	}
	
	/*
	 * Find all messages
	 */
	@SuppressWarnings("unchecked")
	public List<Message> findAllMessages() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("date"));
        List<Message> messages = (List<Message>) criteria.list();     
        return messages;
	}
	
	/*
	 * Find all sent messages of the authenticated user
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Message> listAllSentMessages(Integer offset, Integer maxResults, String username){
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
	
	public Long countSentMessages(String username){
		return (Long)getSession()
				.createCriteria(Message.class, "message")
				.createAlias("message.sender", "sm") // inner join by default
				.add(Restrictions.eq("sm.username", username))
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	/*
	 * Find all received messages of the authenticated user
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Message> listAllReceivedMessages(Integer offset, Integer maxResults, String username){		
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
	
	public Long countReceivedMessages(String username){
		return (Long)getSession()
				.createCriteria(Message.class, "message")
				.createAlias("message.receiver", "rm") // inner join by default
				.add(Restrictions.eq("rm.username", username))
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
}
