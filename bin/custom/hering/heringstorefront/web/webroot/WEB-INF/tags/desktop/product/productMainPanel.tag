<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<c:if test="${not empty message}">
	<spring:theme code="${message}"/>
</c:if>

	 
<div id="globalMessages">
	<common:globalMessages/>
</div>

<cms:pageSlot position="Section1" var="comp" element="div" class="span-24 section1 cms_disp-img_slot">
	<cms:component component="${comp}"/>
</cms:pageSlot>

<product:productDetailsPanel product="${product}" galleryImages="${galleryImages}"/>
