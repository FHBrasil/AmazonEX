<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:theme code="text.addToCart" var="addToCartText" />
<c:url value="${product.url}" var="productUrl" />
<c:set value="${not empty product.potentialPromotions}" var="hasPromotion" />
<div id="${product.code}" class="productListItem-li">
    <ycommerce:testId code="test_searchPage_wholeProduct">
        <a href="${productUrl}" title="${product.name}" class="productMainLink" id="productMainLink">
            <div class="searchProductDetails">
                <input type="hidden" class="codeProduct" value="${product.code}" /> <input
                    type="hidden" class="priceProduct" value="${product.price.value}" />
            </div>
            <div class="thumb-li">
                <product:productPrimaryImage product="${product}" format="store" />
                <c:if
                    test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
                    <img src="${product.potentialPromotions[0].productBanner.url}"
                        alt="${product.potentialPromotions[0].description}"
                        title="${product.potentialPromotions[0].description}" />
                </c:if>
            </div> <ycommerce:testId code="searchPage_productName_link_${product.code}">
                <div class="head-li">${product.name}</div>
            </ycommerce:testId>
        </a>
        <div class="searchFloatingColors search-space">
            <ycommerce:testId code="product_productSize">
					${product.colors}
				</ycommerce:testId>
        </div>
        <div class="price-li">
            <c:if test="${product.oldPrice > product.price.value}">
                <span class="line-t"> <spring:theme code="product.volumePrices.from" /> <spring:theme
                        code="product.currencynotation" /> <fmt:formatNumber
                        value="${product.oldPrice}" type="number" minFractionDigits="2" />
                </span>
	   				&nbsp;
	           		<spring:theme code="product.volumePrices.to" />
            </c:if>
            <format:fromPrice priceData="${product.price}" />
        </div>
        <div class="pricex-li">
            <c:choose>
                <c:when test="${product.priceParcels > 1}">
						${product.priceParcels}
						<spring:theme code="product.volumePrices.parcel" />
                </c:when>
                <c:otherwise>
                    <spring:theme code="product.payment.methods.not.parceled" />
                </c:otherwise>
            </c:choose>
            <spring:theme code="product.currencynotation" />
            <fmt:formatNumber value="${product.parcelUnitPrice}" type="number" minFractionDigits="2" />
        </div>
        <div class="cart-li clearfix">
            <c:url value="/cart/add" var="addToCartUrl" />
            <form:form id="addToCartForm${product.code}" action="${addToCartUrl}" method="post"
                class="addToCartForm">
                <input type="hidden" name="productCodePost" value="${product.code}" />
                <ycommerce:testId code="product_addProduct_button">
                    <button type="${buttonType}" disabled="disabled"
                        class="addToCartButton addToCartButton-li <c:if test="${product.stock.stockLevelStatus.code eq 'outOfStock' }">out-of-stock</c:if>"
                        <c:if test="${product.stock.stockLevelStatus.code eq 'outOfStock' }"> disabled="disabled" aria-disabled="true"</c:if>>${addToCartText}</button>
                </ycommerce:testId>
            </form:form>
        </div>
    </ycommerce:testId>
</div>
