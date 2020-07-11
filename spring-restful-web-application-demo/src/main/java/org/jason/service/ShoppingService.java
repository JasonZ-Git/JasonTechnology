package org.jason.service;

import java.util.List;

import org.jason.dao.ShoppingItem;

/**
 * Service class for processing shopping logic
 *
 * @author jason.zhang
 */

public interface ShoppingService {
    /**
     * Get all shopping items from stock
     *
     * @return All shopping items in stock
     */
    List<ShoppingItem> getAllItems();

    /**
     * Decrease shopping items from stock.
     *
     * @param name   name of the shopping item
     * @param number Quantity to decrease.
     */
    void decreaseItem(String name, int number);
}
