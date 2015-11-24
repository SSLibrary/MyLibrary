package com.ss.academy.java.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ss.academy.java.dao.AbstractDao;
import com.ss.academy.java.model.user.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria();// .addOrder(Order.asc("username"));
													// // Order
		// // ascending
		// // by
		// // title
		// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // To
		// // avoid
		// // duplicates.
		List<User> users = (List<User>) criteria.list();

		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public User findById(Long id) {
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
}