package com.ss.academy.java.dao.user;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ss.academy.java.dao.AbstractDao;
import com.ss.academy.java.model.user.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<String, User> implements UserDao {

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria();
		List<User> users = (List<User>) criteria.list();

		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public User findById(String id) {
		return getByKey(id);
	}

	public User findByUsername(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));

		return (User) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> findUsersByUserName(String userName) {
		String searchBy = "%" + userName + "%";
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("username"))
				.add(Restrictions.like("username", searchBy));

		List<User> users = (List<User>) criteria.list();

		return users;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> list(Integer offset, Integer maxResults) {
		return getSession().createCriteria(User.class).setFirstResult(offset != null ? offset : 0)
				.setMaxResults(maxResults != null ? maxResults : 5).list();
	}

	public Long count() {
		return (Long) getSession().createCriteria(User.class).setProjection(Projections.rowCount()).uniqueResult();
	}
}