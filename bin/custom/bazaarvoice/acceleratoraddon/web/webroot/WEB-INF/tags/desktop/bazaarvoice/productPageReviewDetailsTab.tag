<%@ attribute name="product" required="true"
	type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="BVRRContainer">${sBvOutputReviews}</div>
<script type="text/javascript">

	<script type="text/javascript">
	$BV.configure('global', {
		productId : "${product.code}"
	});
	
	$BV.ui('rr', 'show_reviews', {
		<c:if test="${not empty cmsSite.bvConfig.showReviewsScript}">
		doShowContent : function() {
			${cmsSite.bvConfig.showReviewsScript}
		}
		</c:if>
	});
</script>
