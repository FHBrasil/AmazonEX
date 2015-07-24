<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="details" tagdir="/WEB-INF/tags/desktop/showcaseproductdetail" %>
<%@ taglib prefix="wishlist" tagdir="/WEB-INF/tags/desktop/wishlist" %>
<%@ attribute name="className" required="false" type="java.lang.String" %>
<%@ attribute name="wishlistEntries" required="true" type="java.util.List" %>
<%@ taglib prefix="bazaarvoice" tagdir="/WEB-INF/tags/addons/bazaarvoice/desktop/bazaarvoice"%>

<c:if test="${pageType == 'PRODUCT'}">
 <script type="text/javascript">
  $BV.ui('rr', 'inline_ratings', {
   productIds : [
    <c:forEach items="${wishlistEntries}" var="product">
     '${product.code}',
    </c:forEach>
   ],
   containerPrefix : 'BVRRInlineRating'
  });
 </script>
</c:if>

<c:forEach items="${wishlistEntries}" var="entry" varStatus="status">
	<c:set var="product" value="${entry.product}"/>
	<c:url var="productUrl" value="${product.url}"></c:url>
	<div class="wish150506">
		<div class="col-xs-12 wrap150506">
			<div class="thumbnail">
				<a href="${productUrl}" class="productMainLink">
					<details:image images="${product.previewImages}" format="store"/>
				</a>
			</div> 	
			<details:title productName="${product.name}" productUrl="${productUrl}"/>
			<details:prices product="${product}"/>
			<br />
			<span class="glyphicon stars">
				<bazaarvoice:inlineRatings product="${product}"/>
			</span>
			<div class="row qty150506">
				<div class="col-xs-12 text-right">
					<span class="title150506">Schenken</span>
					<wishlist:addToCart product="${product}"/>	
				</div>
			</div>
		</div>
		<!-- Render only, if wishlist owner logged in -->
		<a href="/w/${product.code}/remove" class="removeitem"><span class="glyphicon glyphicon-remove-sign"></span></a>
		<!-- END Render... -->
	</div>
</c:forEach>
