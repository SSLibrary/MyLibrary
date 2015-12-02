package com.ss.academy.java.dao.item;

import java.util.List;

import com.ss.academy.java.model.item.Item;

public interface ItemDao {
 
	Item findById(Long id);
 
    void saveItem(Item item);
     
    void deleteItemById(Long id);
     
    List<Item> findAllItems();

    List<Item> list(Integer offset, Integer maxResults);
    
    Long count();
}