<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="${component.urlLink}" var="paypalExpressCheckoutUrl" />
<a href="${paypalExpressCheckoutUrl}" <c:if test="${inContextCheckoutEnabled}">data-paypal-button="true" data-paypal-id="${merchantId}"</c:if>>
    <img src="${component.media.url}" alt="${component.media.altText}" />
</a>
<c:if test="${inContextCheckoutEnabled}">
	<script>
		(function(d, s, id) {
			var js, ref = d.getElementsByTagName(s)[0];
			if (!d.getElementById(id)) {
				js = d.createElement(s);
				js.id = id;
				js.async = true;
				js.src = "//www.paypalobjects.com/js/external/paypal.v1.js";
				ref.parentNode.insertBefore(js, ref);
			}
		}(document, "script", "paypal-js"));
	</script>
</c:if>
