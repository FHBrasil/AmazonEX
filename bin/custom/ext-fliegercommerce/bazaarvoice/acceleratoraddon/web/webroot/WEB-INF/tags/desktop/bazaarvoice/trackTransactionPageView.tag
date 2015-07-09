<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ attribute name="order" required="true"
              type="de.hybris.platform.commercefacades.order.data.OrderData"%>
<%@ attribute name="orderGroup" required="true"
              type="de.hybris.platform.acceleratorfacades.order.data.OrderEntryGroupData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script type="text/javascript">
	$BV.SI.trackTransactionPageView({
		"orderId" : "${order.code}",
		"tax" : "${order.totalTax.value}",
		"shipping" : "${order.deliveryCost.value}",
		"total" : "${order.totalPrice.value}",
		<c:if test="${not empty order.deliveryAddress.town}">"city" : 		"${order.deliveryAddress.town}",</c:if>
		<c:if test="${not empty order.deliveryAddress.region.name}">"state" : 		"${order.deliveryAddress.region.name}",</c:if>
		<c:if test="${not empty order.deliveryAddress.country.name}">"country" : 	"${order.deliveryAddress.country.name}",</c:if>
		"currency" : 	"${order.totalPrice.currencyIso}",

		"items" : [
			<c:forEach items="${order.entries}" var="entry" varStatus="status">
			<s:eval expression="T(com.bazaarvoice.hybris.utils.BazaarVoiceUtils).ReplaceUnsupportedCharacters(entry.product.code)" var="productCode" />
			{
				"sku" : 	"${productCode}",
				"name" : 	"${entry.product.name}",
				"price" : 	"${entry.totalPrice.value}",
				"quantity": "${entry.quantity}" <c:if test="${not empty entry.product.categories}"> ,
				"category": "${entry.product.categories[0].name}"
				</c:if>
			}<c:if test="${not status.last}">,</c:if>
			</c:forEach>
		],
		"locale" : "${cmsSite.bvLocale}",
		"partnerSource" : "Bazaarvoice for hybris extension v${cmsSite.bvConfig.extensionVersion}",
		"email" : "${email}"


	});

</script>
