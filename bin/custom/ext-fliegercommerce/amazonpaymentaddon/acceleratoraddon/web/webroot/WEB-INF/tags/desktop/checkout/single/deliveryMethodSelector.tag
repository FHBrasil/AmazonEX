<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="deliveryMethods" required="true" type="java.util.List" %>
<%@ attribute name="selectedDeliveryMethodId" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="single-checkout-amazon" tagdir="/WEB-INF/tags/addons/amazonpaymentaddon/desktop/checkout/single"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<section id="shipping-methods">
    <input type="hidden" class="selectedDeliveryMethodCode" value="${selectedDeliveryMethodId}" />
    <c:forEach items="${deliveryMethods}" var="deliveryMethod">
        <single-checkout-amazon:deliveryMethodDetails deliveryMethod="${deliveryMethod}"
            isSelected="${count == 0 || deliveryMethod.code eq selectedDeliveryMethodId}" />
    </c:forEach>
</section>