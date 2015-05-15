<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="deliveryMethods" required="true" type="java.util.List"%>
<%@ attribute name="selectedDeliveryMethodId" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="single-checkout-hering"
    tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>
<section id="shipping-methods">
    <input type="hidden" class="selectedDeliveryMethodCode" value="${selectedDeliveryMethodId}" />
    <c:forEach items="${deliveryMethods}" var="deliveryMethod">
        <single-checkout-hering:deliveryMethodDetails deliveryMethod="${deliveryMethod}"
            isSelected="${count == 0 || deliveryMethod.code eq selectedDeliveryMethodId}" />
    </c:forEach>
</section>
