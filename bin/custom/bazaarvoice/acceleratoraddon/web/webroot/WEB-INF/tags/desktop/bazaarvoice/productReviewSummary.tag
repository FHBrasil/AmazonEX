<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ attribute name="product" required="true"
              type="de.hybris.platform.commercefacades.product.data.ProductData"%>

<s:eval expression="T(com.bazaarvoice.hybris.utils.BazaarVoiceUtils).ReplaceUnsupportedCharacters(product.code)" var="productCode" />

<script type="text/javascript">
	$BV.configure('global', {
		productId : "${productCode}"
	});
</script>
<div id="BVRRSummaryContainer">${sBvOutputSummary}</div>