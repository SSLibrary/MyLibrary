package com.ss.academy.java.dao.user;

import java.util.List;

import com.ss.academy.java.model.user.User;

public interface UserDao {

	List<User> findAllUsers();

	void saveUser(User user);

	User findById(String id);

	User findByUsername(String username);

	List<User> findUsersByUserName(String userName);

	List<User> list(Integer offset, Integer maxResults);
	    
	Long count();
}