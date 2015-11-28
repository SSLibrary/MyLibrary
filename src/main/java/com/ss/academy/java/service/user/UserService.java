package com.ss.academy.java.service.user;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ss.academy.java.model.user.User;

public interface UserService {

	List<User> findAllUsers();

	List<User> findUsersByUserName(String userName);

	void save(User user);

	User findById(Long id);

	User findByUsername(String username);

	@PreAuthorize("hasAuthority('ADMIN')")
	void updateUserStatus(User user);

	boolean isUsernameUnique(Long id, String username);
	
	List<User> list(Integer offset, Integer maxResults);
    
	Long count();

}