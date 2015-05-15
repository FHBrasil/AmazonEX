<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true"
    type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="showPotentialPromotions" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<table>
    <tbody>
        <c:forEach items="${cartData.entries}" var="entry">
            <tr>
                <%-- BRAND IMAGE --%>
                <td class="brand"><product:productBrand product="${entry.product}" /></td>
                <td class="product-details talign-left"><product:productPrimaryImage
                        product="${entry.product}" format="cartIcon" />
                    <div class="info">
                        <ul>
                            <li class="titulo">${entry.product.name}</li>
                            <c:if test="${not empty entry.product.size}">
                                <li class="tamanho">Tamanho: ${entry.product.size}</li>
                            </c:if>
                            <c:if test="${not empty entry.product.color}">
                                <li class="cor"><spring:theme
                                        code="checkout.single.details.cartItems.color" />:
                                    <div class="cores">
                                        <ul>
                                            <li class="${entry.product.color.RGB}"
                                                style="background-color:${entry.product.color.RGB};"></li>
                                        </ul>
                                    </div></li>
                            </c:if>
                            <li class="codigo"><spring:theme
                                    code="checkout.single.details.cartItems.code" />:
                                ${entry.product.code}</li>
                            <li class="precos"><c:choose>
                                    <c:when test="${entry.product.fromPrice}">
                                        <s> <spring:theme
                                                code="checkout.single.details.cartItems.productPrice.from" />:
                                            <fmt:formatNumber value="${entry.product.oldPrice}"
                                                type="number" minFractionDigits="2" /></s>
                                        <strong> <spring:theme
                                                code="checkout.single.details.cartItems.productPrice.to" />:
                                            <format:price priceData="${entry.basePrice}"
                                                displayFreeForZero="true" />
                                        </strong>
                                    </c:when>
                                    <c:otherwise>
                                        <strong> <spring:theme
                                                code="checkout.single.details.cartItems.productPrice.from" />:
                                            <format:price priceData="${entry.basePrice}"
                                                displayFreeForZero="true" />
                                        </strong>
                                    </c:otherwise>
                                </c:choose></li>
                        </ul>
                    </div></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
