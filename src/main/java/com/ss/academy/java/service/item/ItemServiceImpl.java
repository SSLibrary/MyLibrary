package com.ss.academy.java.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.academy.java.dao.item.ItemDao;
import com.ss.academy.java.model.item.Item;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao dao;

	public Item findById(Long id) {
		return dao.findById(id);
	}

	public void saveItem(Item item) {
		dao.saveItem(item);
	}

	public void updateItem(Item item) {
		// Item entity = dao.findById(author.getId());
		//
		// if (entity != null) {
		// entity.setName(author.getName());
		// entity.setCountry(author.getCountry());
		// entity.setBooks(author.getBooks());
		// }
	}

	public void deleteItem(Item item) {
		dao.deleteItemById(item.getItemId());
	}

	public List<Item> findAllItems() {
		return dao.findAllItems();
	}

	public List<Item> list(Integer offset, Integer maxResults) {
		return dao.list(offset, maxResults);
	}

	public Long count() {
		return dao.count();
	}

}
