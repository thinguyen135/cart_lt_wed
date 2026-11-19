<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Giỏ hàng</title>
            <style>
                table {
                    border-collapse: collapse;
                }

                th,
                td {
                    border: 1px solid black;
                    padding: 6px 12px;
                    text-align: left;
                }

                .right {
                    text-align: right;
                }
            </style>
        </head>

        <body>
            <h2 style="color: #008080;">Your cart</h2>

            <c:choose>
                <c:when test="${empty cart.items}">
                    <p>Your cart is empty.</p>
                </c:when>
                <c:otherwise>
                    <table style="border-collapse: collapse; border: 1px solid black;">
                        <thead>
                            <tr>
                                <th>Quantity</th>
                                <th>Description</th>
                                <th class="right">Price</th>
                                <th class="right">Amount</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="lineItem" items="${cart.items}">
                                <tr>
                                    <td>
                                        <form action="cart" method="post">
                                            <input type="hidden" name="action" value="update">
                                            <input type="hidden" name="productCode"
                                                value="<c:out value='${lineItem.product.code}' />">
                                            <input type="number" name="quantity" value="${lineItem.quantity}" min="0"
                                                required>
                                            <input type="submit" value="Update">
                                        </form>
                                    </td>
                                    <td>
                                        <c:out value="${lineItem.product.description}" />
                                    </td>
                                    <td class="right">
                                        <c:out value="${lineItem.product.priceCurrencyFormat}" />
                                    </td>
                                    <td class="right">
                                        <c:out value="${lineItem.totalCurrencyFormat}" />
                                    </td>
                                    <td>
                                        <form action="cart" method="post">
                                            <input type="hidden" name="action" value="remove">
                                            <input type="hidden" name="productCode"
                                                value="<c:out value='${lineItem.product.code}' />">
                                            <input type="submit" value="Remove Item">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <p><b>To change the quantity</b>, enter the new quantity and click on the Update button.</p>
                </c:otherwise>
            </c:choose>

            <section class="actions">
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="shop">
                    <input type="submit" value="Continue Shopping">
                </form><br>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="checkout">
                    <input type="submit" value="Checkout">
                </form>

            </section>
        </body>

        </html>