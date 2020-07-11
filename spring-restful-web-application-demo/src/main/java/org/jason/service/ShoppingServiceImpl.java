package org.jason.service;

import java.util.List;

import org.jason.dao.ShoppingDAO;
import org.jason.dao.ShoppingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Shopping service class implementation.
 *
 * @author jason.zhang
 */

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingDAO shoppingDAO;

    @Override
    public List<ShoppingItem> getAllItems() {

        return this.shoppingDAO.getItems();
    }

    public void setShoppingDAO(ShoppingDAO shoppingDAO) {
        this.shoppingDAO = shoppingDAO;
    }

    @Override
    public void decreaseItem(String name, int number) {
        shoppingDAO.decreaseItem(name, number);
    }

}
