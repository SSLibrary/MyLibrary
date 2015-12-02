package com.ss.academy.java.service.item;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ss.academy.java.model.item.Item;

public interface ItemService {

	Item findById(Long id);

	List<Item> findAllItems();

	@PreAuthorize("hasAuthority('ADMIN')")
	void saveItem(Item item);

	@PreAuthorize("hasAuthority('ADMIN')")
	void updateItem(Item item);

	@PreAuthorize("hasAuthority('ADMIN')")
	void deleteItem(Item item);
	
	List<Item> list(Integer offset, Integer maxResults);	
	
	Long count();
}
