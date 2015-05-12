<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:if test="${themeName == 'black'}">
<div id="productTabs">
	<style>#accessibletabsnavigation0-1, #accessibletabsnavigation0-2 {display: inline!important;}</style>
	<div class="tabHead"><spring:theme code="product.product.details" /></div>
	<div class="tabBody"><product:productDetailsTab product="${product}"/></div>
	<div class="tabHead" id="tab-reviews"><spring:theme code="review.reviews" /></div>
	<div class="tabBody" ><product:productPageReviewsTab product="${product}"/></div>
	<c:if test="${false}">
	<cms:pageSlot position="Tabs" var="tabs">
		<cms:component component="${tabs}"/>
	</cms:pageSlot>
	</c:if>
</div>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
<div class="tabs">
	<ul class="tabs-header">
		<li class="active"><a href="#"><spring:theme code="product.product.details" /></a></li>
		<li><a href="#"><spring:theme code="review.reviews" /></a></li>
	</ul>
	<div class="tabs-content">
		<product:productDetailsTab product="${product}" />
		<product:productPageReviewsTab product="${product}" />
	</div>
</div>
</c:if>