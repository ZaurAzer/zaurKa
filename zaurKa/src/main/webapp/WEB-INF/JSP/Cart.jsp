<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="${language}">

<head>
    <meta charset="UTF-8">
    <title>Customer Cart</title>
</head>

<body>

<h1><fmt:message key="shoppingcart.header" /></h1>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="language.text.english" /></option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="language.text.russian" /></option>
    </select>
</form>
<c:if test="${empty sessionScope.cart}">
    <p><fmt:message key="shoppingcart.empty" />.</p>
</c:if>

<div class="container">
<c:forEach var="CartItem" items="${sessionScope.cart}">
    <div class="product-box">
        <h2>${CartItem.product.productName}</h2>
        <img src="data:image/jpg;base64,${CartItem.product.image}" alt="" width="240" height="300"/>
        <c:choose>
            <c:when test="${CartItem.product.discount == 0 or empty CartItem.product.discount}">
                <p><fmt:message key="shoppingcart.product.price" />: ${CartItem.product.price}</p>
            </c:when>
            <c:otherwise>
                <fmt:formatNumber var="roundedValue" value="${CartItem.product.price*(100-CartItem.product.discount)/100}" pattern="#,##0.00" />

                <p><fmt:message key="shoppingcart.product.price" />e: <del>${CartItem.product.price}</del> <span style="color: red;">${roundedValue}</span></p>
                 </c:otherwise>
        </c:choose>

        <p><fmt:message key="shoppingcart.label.category" />: ${CartItem.product.category}</p>
        <p><fmt:message key="shoppingcart.label.amount" />: ${CartItem.amount}</p>


            <form action="LaptopShop" method="post">
            <input type="hidden" name="command" value="UPDATE_CART">
            <input type="hidden" name="productId" value="${CartItem.product.id}">

                <label for="quantity"><fmt:message key="shoppingcart.label.amount" />:</label>
                <input type="number" id="quantity" name="quantity" value="${CartItem.amount}" min="1" required>

            <button type="submit">Update Quantity</button>
        </form>

        <form action="LaptopShop" method="post">
            <input type="hidden" name="command" value="REMOVE_FROM_CART">
            <input type="hidden" name="productId" value="${CartItem.product.id}">
            <button type="submit"><fmt:message key="shoppingcart.button.remove" /></button>
        </form>
    </div>
</c:forEach>
</div>
<c:if test="${not empty sessionScope.cart}">
<form action="Checkout" method="post">
    <input type="hidden" name="command" value="CHECKOUT">
    <button type="submit"><fmt:message key="shoppingcart.button.order" /></button>
</form>
</c:if>
</body>

</html>