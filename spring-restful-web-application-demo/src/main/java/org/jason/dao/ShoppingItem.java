package org.jason.dao;

/**
 * Shopping Item DB object
 * 
 * @author jason.zhang
 */
public class ShoppingItem {
	// Name of shopping item
	private String name;
	
	// Number of shopping item
	private int number;

	public ShoppingItem(){}
	
	public ShoppingItem(String name, int number) {
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number =  number;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
