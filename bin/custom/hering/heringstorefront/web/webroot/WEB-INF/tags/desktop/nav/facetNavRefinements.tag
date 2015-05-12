<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<%-- DZARM STORE HEADER --%>
<c:if test="${themeName == 'black'}">

	<div class="headline"><spring:theme code="search.nav.refinements"/></div>
	<c:forEach items="${pageData.facets}" var="facet" varStatus="status">
		<c:if test="${status.index eq 0}">
			<nav:facetNavRefinementColorName/>
		</c:if>
		<nav:facetNavRefinementFacet facetData="${facet}"/>
	</c:forEach>

</c:if>

<%-- HERING STORE HEADER --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">

	<%-- <div class="headline"><spring:theme code="search.nav.refinements"/></div> --%>
	<c:forEach items="${pageData.facets}" var="facet" varStatus="status">
		<c:if test="${status.index eq 0}">
			<nav:facetNavRefinementColorName/>
		</c:if>
		<nav:facetNavRefinementFacet facetData="${facet}"/>
	</c:forEach>

</c:if>