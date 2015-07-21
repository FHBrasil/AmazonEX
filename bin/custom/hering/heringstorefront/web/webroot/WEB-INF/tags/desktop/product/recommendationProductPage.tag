<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="bazaarvoice" tagdir="/WEB-INF/tags/addons/bazaarvoice/desktop/bazaarvoice"%>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>

<c:if test="${pageType == 'PRODUCT'}">
	<script type="text/javascript">
		$BV.ui('rr', 'inline_ratings', {
			productIds : [
				<c:forEach items="${recommendations}" var="product">
					'${product.code}',
				</c:forEach>
			],
			containerPrefix : 'BVRRInlineRating'
		});
	</script>
</c:if>
<p class="h4 text-left">Das k&ouml;nnte Dir auch gefallen:</p>
<div class="text-center panel150120">
	<c:forEach items="${recommendations}" var="recommendation">
		<c:url value="${recommendation.images[0].url}" var="image" />
		<div class="box150102">
			<div class="thumbnail">
				<a href="/p/${recommendation.code}">
<%-- 					<product:productPrimaryImage product="${recommendation}" format="product"/> --%>
					<img src="${image}" alt="${recommendation.name}">
				</a>
			</div>
			<a href="/p/${recommendation.code}">
				<b>${recommendation.name}</b>${recommendation.description}</a>
			<span class="glyphicon glyphicon-stop text-onstock"></span> 
				<format:price priceData="${recommendation.price}" displayFreeForZero="true" />
			<br>
			<span class="glyphicon stars">
				<bazaarvoice:inlineRatings product="${product}" />
			</span>
		</div>
	</c:forEach>
</div>