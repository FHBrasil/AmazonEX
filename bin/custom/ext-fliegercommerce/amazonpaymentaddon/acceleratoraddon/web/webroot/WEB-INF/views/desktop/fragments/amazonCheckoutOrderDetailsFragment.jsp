<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="single-checkout-amazon" tagdir="/WEB-INF/tags/addons/amazonpaymentaddon/desktop/checkout/single"%>

{
	"deliveryMethodSelector" : "<input type='hidden' class='selectedDeliveryMethodCode' value='${selectedDeliveryMethodId}'></input><c:forEach items='${deliveryMethods}' var='deliveryMethod'><single-checkout-amazon:deliveryMethodDetails deliveryMethod='${deliveryMethod}' isSelected='${count == 0 || deliveryMethod.code eq selectedDeliveryMethodId}' /></c:forEach>",
    "deliveryCost" : "${cartData.deliveryCost.formattedValue}",
    "totalPrice" : "${cartData.totalPrice.formattedValue}",
    "success" : "${amazonAjaxResponse.success}",
    "showMessage" : "${amazonAjaxResponse.showMessage}",
    "redirect" : "${amazonAjaxResponse.redirect}"
}    