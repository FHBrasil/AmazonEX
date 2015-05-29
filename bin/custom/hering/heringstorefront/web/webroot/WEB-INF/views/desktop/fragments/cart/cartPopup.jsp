<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:theme code="text.addToCart" var="addToCartText" />
<spring:theme code="text.popupCartTitle" var="popupCartTitleText" />
<c:url value="/cart" var="cartUrl" />
<c:url value="/cart/checkout" var="checkoutUrl" />
<div class="mini-carrinho logo-bar">
    <c:if test="${numberShowing > 0 }">
        <ul class="products">
            <c:forEach items="${entries}" var="entry" end="${numberShowing - 1}">
                <c:url value="${entry.product.url}" var="entryProductUrl" />
                <%-- BRAND IMAGE --%>
                <li>
                    <div class="brand">
                        <product:productBrand product="${entry.product}" />
                    </div> <a href="${entryProductUrl}" class="thumbMiniCart"><product:productPrimaryImage
                            product="${entry.product}" format="thumbnail" /></a>
                    <div class="info">
                        <h2>
                            <a href="${entryProductUrl}">${entry.product.name}</a>
                        </h2>
                        <ul>
                            <c:set value="false" var="colorIsUsed" />
                            <c:forEach items="${entry.product.baseOptions}" var="option">
                                <c:forEach items="${option.selected.variantOptionQualifiers}"
                                    var="selectedOption">
                                    <c:if test="${selectedOption.name == 'Tamanho'}">
                                        <li>${selectedOption.name}: ${selectedOption.value}
                                        <li>
                                    </c:if>
                                    <c:if
                                        test="${selectedOption.name == 'Cor' && colorIsUsed == false}">
                                        <c:set value="true" var="colorIsUsed" />
                                        <li>
                                            <div style="float: left">${selectedOption.name}:</div>
                                            <div class="${selectedOption.value}"
                                                style="background-color:${entry.product.color.RGB}; width:12px !important; height:12px !important;float:left;margin-left:5px"></div>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </ul>
                        <div class="precos">
                            <c:choose>
                                <c:when test="${entry.product.fromPrice}">
                                    <s> <spring:theme code="product.volumePrices.from" /> <spring:theme
                                            code="product.currencynotation" /> <fmt:formatNumber
                                            value="${entry.product.oldPrice}" type="number"
                                            minFractionDigits="2" />
                                    </s>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                            <strong>por: <format:fromPrice priceData="${entry.basePrice}" /></strong>
                        </div>
                    </div> <c:url value="/cart/update" var="cartUpdateFormAction" /> <form:form
                        id="updateCartForm${entry.entryNumber}" action="${cartUpdateFormAction}"
                        method="post" commandName="updateQuantityForm${entry.entryNumber}">
                        <input type="hidden" name="entryNumber" value="${entry.entryNumber}" />
                        <input type="hidden" name="productCode" value="${entry.product.code}" />
                        <input type="hidden" name="initialQuantity" value="${entry.quantity}" />
                        <input type="hidden" name="eraseQuantity_${entry.entryNumber}"
                            id="eraseQuantity_${entry.entryNumber}" value="0" />
                        <input type="hidden" id="quantity${entry.entryNumber}" name="quantity"
                            value="0">
                        <c:if test="${entry.updateable}">
                            <ycommerce:testId code="cart_product_removeProduct">
                                <spring:theme code="text.iconCartRemove" var="iconCartRemove" />
                                <a href="#"
                                    onclick="document.forms['updateCartForm${entry.entryNumber}'].submit()"
                                    id="RemoveProduct_${entry.entryNumber}"
                                    class="submitRemoveProduct" title="Remover"> <i
                                    class="fa fa-times"></i>
                                </a>
                            </ycommerce:testId>
                        </c:if>
                    </form:form>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <div class="actions">
        <c:set var="hasItems" value="${not (empty numberItemsInCart or numberItemsInCart eq 0)}" />
        <div class="info">
            <c:if test="${not hasItems}">
                <div class="empty-popup-cart">
                    <i class="fa fa-meh-o"></i>
                    <spring:theme code="popup.cart.empty" />
                </div>
            </c:if>
            <c:if test="${hasItems}">
                <c:if test="${numberItemsInCart gt 1}">
                    <p>${totalItems}<spring:theme code="text.fliegercommerce.texto6"/></p>
                    <strong><format:price priceData="${cartData.totalPrice}" /></strong>
                </c:if>
                <c:if test="${numberItemsInCart lt 2}">
                    <p>${totalItems}<spring:theme code="text.fliegercommerce.texto7"/></p>
                    <strong><format:price priceData="${cartData.totalPrice}" /></strong>
                </c:if>
                <c:if test="${numberItemsInCart > numberShowing}">
                    <!--<div class="legend">
							<span class="mostrar_todos"><a href="${cartUrl}">mostrar todos</a></span>
						</div>-->
                </c:if>
            </c:if>
        </div>
        <c:if test="${hasItems}">
            <div class="btn-group">
                <a href="${cartUrl}" class="btn btn-full-bag"><spring:theme code="text.fliegercommerce.texto8"/></a> <a
                    href="${checkoutUrl}" class="btn btn-checkout"><spring:theme code="text.fliegercommerce.texto9"/></a>
            </div>
        </c:if>
    </div>
</div>
