<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Mua hàng</title>
            <style>
                table { border-collapse: collapse; }
                th, td { border: 1px solid black; padding: 6px 12px; text-align: left; }
                .right { text-align: right; }
            </style>
        </head>

        <body>
            <h2 style="color: #008080;;">CD list</h2><br>

            <table style="border-collapse: collapse; border: 1px solid black;">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th class="right">Price</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>
                                <c:out value="${product.description}" />
                            </td>
                            <td class="right">
                                <c:out value="${product.priceCurrencyFormat}" />
                            </td>
                            <td>
                                <form action="cart" method="post">
                                    <input type="hidden" name="action" value="add">
                                    <input type="hidden" name="productCode" value="<c:out value='${product.code}' />">
                                    <input type="submit" value="Add To Cart">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </body>

        </html>