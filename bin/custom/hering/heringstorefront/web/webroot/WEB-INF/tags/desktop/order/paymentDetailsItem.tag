<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="order" required="true"
    type="de.hybris.platform.commercefacades.order.data.OrderData"%>
<%@ attribute name="orderHistory" required="false"
    type="de.hybris.platform.commercefacades.order.data.OrderHistoryData"%>
<%@ attribute name="boletoURL" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${orderHistory.paymentMode eq 'Boleto'}">
    <div class="right">
        <input name="" class="btn" type="button" onClick="window.open('${fn:escapeXml(boletoUrl)}')"
            value="Imprimir Boleto">
    </div>
</c:if>
<c:if test="${orderHistory.paymentMode ne 'Boleto'}">
    <ul>
        <li>${ycommerce:maskCardNumber(fn:escapeXml(order.paymentInfo.cardNumber))}</li>
        <li>${fn:escapeXml(order.paymentInfo.cardTypeData.name)}</li>
    </ul>
</c:if>
