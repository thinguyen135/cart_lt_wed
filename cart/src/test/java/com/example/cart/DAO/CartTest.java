package com.example.cart.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.cart.BEAN.Cart;
import com.example.cart.BEAN.Product;
import org.junit.Test;

public class CartTest {

    @Test
    public void testCatalogAndCartOperations() {
        Product cd1 = ProductDAO.getProduct("8601");
        Product cd2 = ProductDAO.getProduct("pf01");
        assertNotNull(cd1);
        assertNotNull(cd2);
        assertNull(ProductDAO.getProduct("unknown"));

        Cart cart = new Cart();
        cart.addItem(cd1, 1);
        cart.addItem(cd1, 2);
        cart.addItem(cd2, 1);

        assertEquals(2, cart.getCount());
        assertEquals(4, cart.getTotalQuantity());
        assertEquals(14.95 * 3 + 12.95, cart.getTotal(), 0.001);
        assertEquals("$57.80", cart.getTotalCurrencyFormat());

        cart.updateItem("8601", 1);
        assertEquals(2, cart.getTotalQuantity());

        cart.updateItem("8601", 0);
        assertEquals(1, cart.getCount());
        assertEquals(12.95, cart.getTotal(), 0.001);

        cart.removeItem("pf01");
        assertEquals(0, cart.getCount());

        cart.addItem(null, 1);
        cart.addItem(ProductDAO.getProduct("zzz"), 1);
        assertEquals(0, cart.getCount());
    }
}
