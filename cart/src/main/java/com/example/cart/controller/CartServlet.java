package com.example.cart.controller;

import com.example.cart.BEAN.Cart;
import com.example.cart.BEAN.Product;
import com.example.cart.DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = { "/cart" })
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "shop";
        }

        HttpSession session = request.getSession();
        final Object lock = request.getSession().getId().intern();
        Cart cart;
        synchronized (lock) {
            cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
        }

        // perform action and set URL to appropriate page
        String url = "/WEB-INF/view/home.jsp";
        String productCode = request.getParameter("productCode");

        synchronized (lock) {
            if (action.equals("shop")) {
                url = "/WEB-INF/view/home.jsp";

            } else if (action.equals("add")) {
                Product product = ProductDAO.getProduct(productCode);
                cart.addItem(product, 1);
                url = "/WEB-INF/view/cart.jsp";

            } else if (action.equals("update")) {
                int quantity;
                try {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                } catch (NumberFormatException e) {
                    quantity = 1;
                }
                cart.updateItem(productCode, quantity);
                url = "/WEB-INF/view/cart.jsp";

            } else if (action.equals("remove")) {
                cart.removeItem(productCode);
                url = "/WEB-INF/view/cart.jsp";

            } else if (action.equals("checkout")) {
                session.removeAttribute("cart");
                url = "/WEB-INF/view/cart.jsp";

            } else if (action.equals("cart")) {
                url = "/WEB-INF/view/cart.jsp";
            }
        }

        if (url.endsWith("home.jsp")) {
            request.setAttribute("products", ProductDAO.getProducts());
        }

        // forward to the view
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
