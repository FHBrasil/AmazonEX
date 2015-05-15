<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
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
<ycommerce:testId code="product_wholeProduct">
    <input type="hidden" class="codeProduct" value="${product.code}" />
    <input type="hidden" class="priceProduct" value="${product.price.value}" />
    <product:productPrimaryImage product="${product}" format="store" />
    <div class="info">
        <a href="${productUrl}" title="${product.name}" class="productMainLink" id="productMainLink"
            style="color: #fff">
            <h2>
                <ycommerce:testId code="product_productName">${product.name}</ycommerce:testId>
            </h2>
        </a>
        <div class="selos">
            <ul>
                <c:if test="${product.freeShipping}">
                    <li class="frete"><spring:theme code="product.attractions.freeshipping" />
                    </li>
                </c:if>
                <%-- se produto em promo��o e � lan�amento, mostrar somente promo��o --%>
                <c:if test="${product.newProduct && !(product.oldPrice > product.price.value)}">
                    <li class="lancamento"><spring:theme code="product.attractions.new" /></li>
                </c:if>
                <c:choose>
                    <c:when test="${product.blackfriday}">
                        <!-- Black Friday Enable -->
                        <li class="blackfriday">BLACK FRIDAY</li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${product.oldPrice > product.price.value}">
                            <!-- Sale Enable -->
                            <li class="promocao"><spring:theme
                                    code="product.attractions.sale.${themeName}" /></li>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <div class="cores">
            <h3>Cores:</h3>
            <ycommerce:testId code="product_productSize">${product.colors}</ycommerce:testId>
        </div>
        <div class="tamanhos sizes-box">
            <h3>
                <spring:theme code="product.size" />
            </h3>
            <ul class="sizeVariantEventHandler">
                <c:set var="i" value="1" />
                <c:forEach items="${product.sizes}" var="size">
                    <li id="${product.code}-${i}" class="InStock-1"
                        onclick="getSizeProductPerColor($(this))"><a>${size}</a></li>
                    <c:set var="i" value="${i + 1}" />
                </c:forEach>
            </ul>
        </div>
        <div class="precos">
            <c:choose>
                <c:when test="${product.oldPrice > product.price.value}">
                    <!-- Have OLD PRICE -->
                    <s> <spring:theme code="product.volumePrices.from" /> <spring:theme
                            code="product.currencynotation" /> <fmt:formatNumber
                            value="${product.oldPrice}" type="number" minFractionDigits="2" />
                    </s>
                    <strong> <spring:theme code="product.volumePrices.to" /> <format:fromPrice
                            priceData="${product.price}" />
                    </strong>
                </c:when>
                <c:otherwise>
                    <strong><format:fromPrice priceData="${product.price}" /></strong>
                </c:otherwise>
            </c:choose>
            <!-- Parcels -->
            <c:choose>
                <c:when test="${product.priceParcels > 1}">
                    <p>
                        ${product.priceParcels}
                        <spring:theme code="product.volumePrices.parcel" />
                        <b>${product.parcelUnitPrice}</b>
                    </p>
                </c:when>
                <c:otherwise>
                    <spring:theme code="product.payment.methods.not.parceled" />
                </c:otherwise>
            </c:choose>
            <!-- /Parcels -->
        </div>
        <%-- <hr class="clear"> --%>
        <c:url value="/p/${product.code}" var="viewMoreUrl" />
        <%-- 				<c:url value="/cart/add" var="addToCartUrl" /> --%>
        <%-- 				<form:form id="addToCartForm${product.code}" action="${addToCartUrl}"  --%>
        <%-- 					method="fpost" class="addToCartForm clear_fix"> --%>
        <%-- 					<input type="hidden" id="productCodePost" name="productCodePost" value="${product.code}" /> --%>
        <a href="${viewMoreUrl}" class="btn btn-cta add addToCartButton"> <%-- - 						<spring:theme code="cart.page.add"/> --%>
            <%-- 						<spring:theme code="product.viewmore"/> --%> Ver detalhes
        </a>
        <%-- 				</form:form> --%>
        <%-- 				<span class="detalhes"><a href="${viewMoreUrl}" ><spring:theme code="product.viewmore" /></a><span> --%>
    </div>
</ycommerce:testId>