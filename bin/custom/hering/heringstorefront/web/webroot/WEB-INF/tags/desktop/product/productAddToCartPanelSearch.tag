<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="allowAddToCart" required="true" type="java.lang.Boolean"%>
<%@ attribute name="isMain" required="true" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url value="${product.url}" var="productUrl" />
<form:form id="id_${product.code}" class="add_to_cart_form" action="${productUrl}">
    <div class="qty"></div>
    <div class="productAddToCartPanel clearfix">
        <c:if test="${true}">
            <input type="hidden" name="qty" class="qty" value="1">
        </c:if>
        <input type="hidden" name="productCodePost" value="${product.code}" />
        <c:if test="${allowAddToCart}">
            <c:set var="buttonType">button</c:set>
            <div class="SearchBuyButton">
                <c:choose>
                    <c:when test="${fn:contains(buttonType, 'button')}">
                        <div class="addToCartButtonSearch">
                            <a href="${productUrl}" class="add"> <spring:theme
                                    code="cart.page.add" />
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="addToCartButtonSearch">
                            <a href="${productUrl}" class="add"> <%-- <spring:theme code="cart.page.shop"/> --%>
                                <spring:theme code="cart.page.add" />
                            </a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
    </div>
</form:form>
