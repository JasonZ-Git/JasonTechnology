package org.jason.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Fetch shopping item from DB.
 * 
 * @author jason.zhang
 */
@Component
public class ShoppingDAOImpl implements ShoppingDAO {
	private static List<ShoppingItem> items;
	static {
		items = new ArrayList<ShoppingItem>();
		ShoppingItem itemA = new ShoppingItem("A", 20);
		ShoppingItem itemB = new ShoppingItem("B", 12);

		items.add(itemA);
		items.add(itemB);
	}

	@Override
	public List<ShoppingItem> getItems() {
		// The real value should be fetched from DB.
		return items;
	}

	@Override
	public void decreaseItem(String name, int number) {
		// The real action should decrease the item in database.
		for (ShoppingItem item : items) {
			if (item.getName().equals(name)) {
				item.setNumber(item.getNumber() - number);
			}
		}
	}

}
