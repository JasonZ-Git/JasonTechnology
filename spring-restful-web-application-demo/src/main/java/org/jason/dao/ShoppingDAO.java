package org.jason.dao;

import java.util.List;
/**
 * Shopping DAO 
 * 
 * @author jason.zhang
 *
 */
public interface ShoppingDAO {
	/**
	 * Get all shopping item from database.
	 * 
	 * @return All shopping items.
	 */
	List<ShoppingItem> getItems();

	/**
	 * Decrease item from database.
	 * 
	 * @param name Item name (It should be ID in production environment.)
	 * @param number number to be decreased. It should be bigger than zero.
	 */
	void decreaseItem(String name, int number);
}
