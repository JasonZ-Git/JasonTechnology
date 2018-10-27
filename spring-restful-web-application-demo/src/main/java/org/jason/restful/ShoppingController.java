package org.jason.restful;

import java.util.List;

import org.apache.log4j.Logger;
import org.jason.dao.ShoppingItem;
import org.jason.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Shopping controller class.
 * 
 * @author jason.zhang
 */
@RestController
public class ShoppingController {
	private static final Logger logger = Logger.getLogger(ShoppingController.class);
	@Autowired
	private ShoppingService shoppingService;

	/**
	 * Get all shopping items.
	 * 
	 * @return shopping items.
	 */
	@RequestMapping(value = "/getShoppingItems", method = RequestMethod.GET)
	public List<ShoppingItem> getShppingItems() {
		logger.info(" Getting items ");
		return this.shoppingService.getAllItems();
	}

	/**
	 * Decrease item from stock.
	 * 
	 * @param name It should be product ID. we can use name for now.
	 * @param number Number of item to be decreased.
	 */
	@RequestMapping(value = "/decreaseShoppingItem", method = RequestMethod.POST)
	public void decreaseShoppingItem(@RequestParam("name")String name,  @RequestParam("number")String number){
		logger.info(" name " + name + " ,number" + number);
		this.shoppingService.decreaseItem(name, Integer.valueOf(number));
	}
	
	public void setShoppingService(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}

}
