<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<%-- DZARM ROTATION COMPONENT --%>
<c:if test="${themeName == 'black'}">
	<div class="slider_component">
		<div id="homepage_slider" class="svw">
			<ul>
				<c:forEach items="${banners}" var="banner" varStatus="status">
					<c:if test="${ycommerce:evaluateRestrictions(banner)}">
						<c:url value="${banner.urlLink}" var="encodedUrl" />
						<li><a tabindex="-1" href="${encodedUrl}"<c:if test="${banner.external}"> target="_blank"</c:if>><img src="${banner.media.url}" alt="${not empty banner.headline ? banner.headline : banner.media.altText}" title="${not empty banner.headline ? banner.headline : banner.media.altText}"/></a></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>

<%-- HERING ROTATION COMPONENT --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<section id="main-slider" class="big-slider">
		<ul class="bxslider">
		<c:forEach items="${banners}" var="banner" varStatus="status">
			<c:url value="${banner.urlLink}" var="encodedUrl" />
			<li><a href="${encodedUrl}" <c:if test="${banner.external}"> target="_blank"</c:if>><img src="${banner.media.url}" alt="${not empty banner.headline ? banner.headline : banner.media.altText}" title="${not empty banner.headline ? banner.headline : banner.media.altText}" /></a></li>
		</c:forEach>
		</ul>
	</section>
</c:if>