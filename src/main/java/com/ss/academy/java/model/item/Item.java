package com.ss.academy.java.model.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="items")
public class Item {

@Id
@Column(name="itemId")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long itemId;

@Column(name="itemName")
private String itemName;

@Lob
@Column(name="itemContent")
private byte[] itemContent;

@ManyToOne
@JoinColumn(name = "book_id")
@JsonBackReference(value = "book-images")
private Item items;


public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public byte[] getItemContent() {
	return itemContent;
}

public void setItemContent(byte[] itemContent) {
	this.itemContent = itemContent;
}

/**
 * @return the itemId
 */
public Long getItemId() {
	return itemId;
}

/**
 * @param itemId the itemId to set
 */
public void setItemId(Long itemId) {
	this.itemId = itemId;
}

/**
 * @return the items
 */
public Item getItems() {
	return items;
}

/**
 * @param items the items to set
 */
public void setItems(Item items) {
	this.items = items;
}


}
