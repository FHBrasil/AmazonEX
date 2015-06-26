<%@ attribute name="product" required="true"
	type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="BVQAContainer">${sBvOutputQA}</div>
<script type="text/javascript">
	
	$BV.ui('qa', 'show_questions', {
		<c:if test="${not empty cmsSite.bvConfig.showQuestionsScript}">
		doShowContent : function() {
			${cmsSite.bvConfig.showQuestionsScript}
		}
		</c:if>
	});
	
</script>
