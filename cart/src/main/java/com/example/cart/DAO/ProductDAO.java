package com.example.cart.DAO;

import com.example.cart.BEAN.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDAO {

    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product("8601", "86 (the band) - True Life Songs and Pictures", 14.95));
        products.add(new Product("pf01", "Paddlefoot - The first CD", 12.95));
        products.add(new Product("pf02", "Paddlefoot - The second CD", 14.95));
        products.add(new Product("jr01", "Joe Rut - Genuine Wood Grained Finish", 14.95));
    }

    public static List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public static Product getProduct(String code) {
        if (code == null) {
            return null;
        }
        for (Product product : products) {
            if (code.equals(product.getCode())) {
                return product;
            }
        }
        return null;
    }
}
