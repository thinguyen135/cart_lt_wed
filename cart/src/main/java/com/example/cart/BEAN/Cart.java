package com.example.cart.BEAN;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<LineItem> items = new ArrayList<>();

    public List<LineItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public int getTotalQuantity() {
        int total = 0;
        for (LineItem item : items) {
            total += item.getQuantity();
        }
        return total;
    }

    public double getTotal() {
        double total = 0.0;
        for (LineItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        return currency.format(getTotal());
    }

    /** Adds quantity of the product; if it is already in the cart, increases its quantity. */
    public void addItem(Product product, int quantity) {
        if (product == null || product.getCode() == null || quantity <= 0) {
            return;
        }
        LineItem existing = findByCode(product.getCode());
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            items.add(new LineItem(product, quantity));
        }
    }

    /** Sets the quantity of a product in the cart; a quantity of 0 removes it. */
    public void updateItem(String code, int quantity) {
        LineItem existing = findByCode(code);
        if (existing == null) {
            return;
        }
        if (quantity <= 0) {
            items.remove(existing);
        } else {
            existing.setQuantity(quantity);
        }
    }

    public void removeItem(String code) {
        LineItem existing = findByCode(code);
        if (existing != null) {
            items.remove(existing);
        }
    }

    private LineItem findByCode(String code) {
        if (code == null) {
            return null;
        }
        for (LineItem item : items) {
            if (code.equals(item.getProduct().getCode())) {
                return item;
            }
        }
        return null;
    }
}
