package com.ss.academy.java.dao.item;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.ss.academy.java.dao.AbstractDao;
import com.ss.academy.java.model.author.Author;
import com.ss.academy.java.model.item.Item;

@Repository("itemDao")
public class ItemDaoImpl extends AbstractDao<Long, Item> implements ItemDao {

	public Item findById(Long id) {
		return getByKey(id);
	}

	public void saveItem(Item item) {
		persist(item);
	}

	public void deleteItemById(Long id) {
		Query query = getSession().createSQLQuery("delete from items where items_id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Item> findAllItems() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name")); // Order
																				// ascending
																				// by
																				// name
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // To
																		// avoid
																		// duplicates.
		List<Item> items = (List<Item>) criteria.list();

		return items;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Item> list(Integer offset, Integer maxResults){
		return getSession()
				.createCriteria(Item.class)
				.setFirstResult(offset!=null?offset:0)
				.setMaxResults(maxResults!=null?maxResults:5)
				.list();
	}
	
	public Long count(){
		return (Long)getSession()
				.createCriteria(Author.class)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}

}