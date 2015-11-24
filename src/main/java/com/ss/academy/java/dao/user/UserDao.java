package com.ss.academy.java.dao.user;

import java.util.List;

import com.ss.academy.java.model.user.User;

public interface UserDao {

	List<User> findAllUsers();

	void save(User user);

	User findById(Long id);

	User findByUsername(String username);

	List<User> findUsersByUserName(String userName);

}