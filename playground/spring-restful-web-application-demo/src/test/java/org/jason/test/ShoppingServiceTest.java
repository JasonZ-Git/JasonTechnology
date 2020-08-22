package org.jason.test;

import java.util.ArrayList;
import java.util.List;

import org.jason.dao.ShoppingDAO;
import org.jason.dao.ShoppingItem;
import org.jason.service.ShoppingServiceImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.MockitoAnnotations;

/**
 * Test class for shoppingService
 *
 * @author jason.zhang
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingServiceTest {
    private ShoppingServiceImpl shoppingService = new ShoppingServiceImpl();
    @Mock
    private ShoppingDAO shoppingDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetItems() {
        List<ShoppingItem> items = new ArrayList<ShoppingItem>();
        items.add(new ShoppingItem("AAA", 123));
        items.add(new ShoppingItem("BBB", 456));
        Mockito.when(shoppingDAO.getItems()).thenReturn(items);
        this.shoppingService.setShoppingDAO(shoppingDAO);

        List<ShoppingItem> result = this.shoppingService.getAllItems();
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0).getName(), "AAA");
        Assert.assertEquals(result.get(0).getNumber(), 123);
    }

}
