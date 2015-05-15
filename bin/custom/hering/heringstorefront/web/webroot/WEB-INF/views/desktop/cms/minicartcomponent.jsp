<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%-- for acc.minicart.js --%>
<div id="layoutTheme" style="display: none !important;">${themeName}</div>
<c:url value="/cart/miniCart/${totalDisplay}" var="refreshMiniCartUrl" />
<c:url value="/cart/rollover/${component.uid}" var="rolloverPopupUrl" />
<c:url value="/cart" var="cartUrl" />
<div id="carrinho" class="show-on-desktop">
    <span class="bag"> <span class="count">${totalItems}</span>
    </span> <span class="price"> <format:price priceData="${totalPrice}" />
    </span> <a href="${cartUrl}" class="btn btn-checkout">Sacola</a>
    <div id="miniCartLayer" class="miniCartPopup" data-refreshMiniCartUrl="${refreshMiniCartUrl}/?"
        data-rolloverPopupUrl="${rolloverPopupUrl}" style="padding: 0 !important;"></div>
</div>
