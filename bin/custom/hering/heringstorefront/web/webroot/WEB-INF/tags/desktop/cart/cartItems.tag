<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true"
    type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<table>
    <thead>
        <tr>
            <th></th>
            <th class="talign-left"><spring:theme code="basket.page.product" /></th>
            <th><spring:theme code="basket.page.quantity" /></th>
            <th><spring:theme code="basket.page.table.unitValue" /></th>
            <th><spring:theme code="basket.page.table.totalValue" /></th>
            <th><spring:theme code="basket.page.remove" /></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${cartData.entries}" var="entry">
            <c:url value="${entry.product.url}" var="productUrl" />
            <tr id="${entry.entryNumber}">
                <%-- BRAND IMAGE --%>
                <td class="brand"><product:productBrand product="${entry.product}" /></td>
                <td class="product-details talign-left">
                    <%-- PRODUTO // --%> <%-- PRODUCT IMAGE --%> <a href="${productUrl}"><product:productPrimaryImage
                            product="${entry.product}" format="cartIcon" /></a>
                    <div class="info">
                        <ul>
                            <%-- NOME PRODUTO --%>
                            <li class="titulo"><a href="${productUrl}">${entry.product.name}</a></li>
                            <%-- TAMANHO E COR --%>
                            <c:set value="false" var="colorIsUsed" />
                            <c:forEach items="${entry.product.baseOptions}" var="option">
                                <c:forEach items="${option.selected.variantOptionQualifiers}"
                                    var="selectedOption">
                                    <c:if test="${selectedOption.name == 'Tamanho'}">
                                        <li class="tamanho">${selectedOption.name}:
                                            ${selectedOption.value}</li>
                                    </c:if>
                                    <c:if
                                        test="${selectedOption.name == 'Cor' && colorIsUsed == false}">
                                        <c:set value="true" var="colorIsUsed" />
                                        <li class="cor">${selectedOption.name}:
                                            <div class="cores">
                                                <ul>
                                                    <li class="${selectedOption.value}"
                                                        style="background-color:${entry.product.color.RGB};">
                                                        <div class="tooltip" style="display: none;">${selectedOption.value}</div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                            <%-- CODIGO DO PRODUTO --%>
                            <li class="codigo"><br>C&oacute;digo: ${entry.product.code}</li>
                        </ul>
                    </div>
                </td>
                <%--  QUANTIDADE // --%>
                <td class="quantity"><c:url value="/cart/update" var="cartUpdateFormAction" />
                    <form:form id="updateCartForm${entry.entryNumber}"
                        action="${cartUpdateFormAction}" method="post"
                        commandName="updateQuantityForm${entry.entryNumber}">
                        <input type="hidden" name="entryNumber" value="${entry.entryNumber}" />
                        <input type="hidden" name="productCode" value="${entry.product.code}" />
                        <input type="hidden" name="initialQuantity" value="${entry.quantity}" />
                        <input type="hidden" name="initialQuantity_${entry.entryNumber}"
                            id="initialQuantity_${entry.entryNumber}" value="${entry.quantity}" />
                        <ycommerce:testId code="cart_product_quantity">
                            <form:input disabled="${not entry.updateable}" type="number" size="1"
                                id="quantity${entry.entryNumber}" class="qty" path="quantity"
                                min="1" maxlength="3" required="required" step="1" />
                        </ycommerce:testId>
                    </form:form> <c:if test="${entry.product.stock.stockLevel < 4}">
                        <c:choose>
                            <c:when test="${entry.product.stock.stockLevel > 1}">
                                <small><spring:theme code="cart.page.qtys"
                                        arguments="${entry.product.stock.stockLevel}" /></small>
                            </c:when>
                            <c:otherwise>
                                <small><spring:theme code="cart.page.qty"
                                        arguments="${entry.product.stock.stockLevel}" /></small>
                            </c:otherwise>
                        </c:choose>
                    </c:if></td>
                <%-- VALOR UNITARIO // --%>
                <td class="product-price talign-left">
                    <%-- SE O PRODUTO ESTIVER COM DESCONTO --%> <c:if
                        test="${entry.product.fromPrice}">
                        <div class="precos">
                            <s>de: <fmt:formatNumber value="${entry.product.oldPrice}"
                                    type="number" minFractionDigits="2" /></s> <strong><spring:theme
                                    code="product.volumePrices.to" /> <format:fromPrice
                                    priceData="${entry.basePrice}" /><strong>
                        </div>
                        <div class="selos">
                            <ul>
                                <li class="desconto">produto com desconto</li>
                            </ul>
                        </div>
                    </c:if> <c:if test="${!entry.product.fromPrice}">
                        <div class="precos">
                            <strong><format:fromPrice priceData="${entry.basePrice}" /><strong>
                        </div>
                    </c:if>
                </td>
                <%-- VALOR TOTAL // --%>
                <td class="product-price total">
                    <div class="precos">
                        <c:if test="${entry.product.fromPrice}">
                            <strong>por: <format:price priceData="${entry.totalPrice}"
                                    displayFreeForZero="true" /></strong>
                        </c:if>
                        <c:if test="${!entry.product.fromPrice}">
                            <strong><format:price priceData="${entry.totalPrice}"
                                    displayFreeForZero="true" /></strong>
                        </c:if>
                    </div>
                </td>
                <%-- EXCLUIR // --%>
                <c:if test="${entry.updateable}">
                    <td><ycommerce:testId code="cart_product_removeProduct">
                            <a href="#" class="btn-excluir-produto"
                                id="RemoveProduct_${entry.entryNumber}" class="submitRemoveProduct"
                                title="Remover"
                                onClick="hering.cart.removeProduct('${entry.entryNumber}');"></a>
                        </ycommerce:testId></td>
                </c:if>
            </tr>
        </c:forEach>
    </tbody>
</table>
