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
<%@ taglib prefix="notifyme" tagdir="/WEB-INF/tags/desktop/notifyme"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url value="/cart/add" var="addToCartUrl" />
<c:choose>
    <c:when test="${product.purchasable && product.stock.stockLevelStatus.code != 'outOfStock'}">
        <form:form id="code_${product.code}" method="post" action="${addToCartUrl}"
            class="addToCartForm">
            <section class="clear">
                <div class="quantidade">
                    <input class="productStock" type="hidden" value="${product.stock.stockLevel}" />
                    <c:if test="${product.purchasable}">
                        <h3>
                            <spring:theme code="basket.page.quantity" />
                            :
                        </h3>
                        <input id="qtyInput_${product.code}" type="number" value="1" min="1"
                            maxlength="3" name="qty">
                    </c:if>
                    <c:if test="${product.stock.stockLevelStatus.code eq 'lowStock'}">
                        <c:if test="${product.stock.stockLevel < 4}">
                            <c:set var="productStockLevel">
                                <spring:theme code="product.variants.only.left"
                                    arguments="${product.stock.stockLevel}" />
                            </c:set>
                        </c:if>
                    </c:if>
                    <ycommerce:testId code="productDetails_productInStock_label">
                        <span>${productStockLevel}</span>
                    </ycommerce:testId>
                </div>
                <product:productSocialSharing />
            </section>
            <div class="productAddToCartPanel clearfix">
                <input type="hidden" name="productCodePost" value="${product.code}" />
                <c:if test="${allowAddToCart}">
                    <button type="${buttonType}" class="addToCartButton btn btn-big add">
                        <spring:theme code="cart.page.add" />
                    </button>
                </c:if>
            </div>
        </form:form>
    </c:when>
    <c:otherwise>
        <notifyme:notifymeRegister product="${product}" />
    </c:otherwise>
</c:choose>
