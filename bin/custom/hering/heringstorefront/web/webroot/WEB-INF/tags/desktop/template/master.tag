<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true"%>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>
<%@ attribute name="metaDescription" required="false"%>
<%@ attribute name="metaKeywords" required="false"%>
<%@ attribute name="pageCss" required="false" fragment="true"%>
<%@ attribute name="pageScripts" required="false" fragment="true"%>
<%@ attribute name="showBV" required="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="analytics" tagdir="/WEB-INF/tags/shared/analytics"%>
<%@ taglib prefix="debug" tagdir="/WEB-INF/tags/shared/debug"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bvJavascript" tagdir="/WEB-INF/tags/addons/bazaarvoice/desktop/template"%>
<%@ taglib prefix="bazaarvoice" tagdir="/WEB-INF/tags/addons/bazaarvoice/desktop/bazaarvoice"%>
<!DOCTYPE html>
<html lang="${currentLanguage.isocode}">
<head>
<title>${not empty pageTitle ? pageTitle : not empty cmsPage.title ? cmsPage.title : 'Accelerator Title'}
</title>
<%-- Meta Content --%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- Additional meta tags --%>
<c:forEach var="metatag" items="${metatags}">
    <c:if test="${not empty metatag.content}">
        <meta name="${metatag.name}" content="${metatag.content}" />
    </c:if>
</c:forEach>
<c:choose>
    <c:when test="${pageType == 'PRODUCT'}">
        <meta property='og:title' content='${product.name}' />
        <meta property='og:description' content='${product.description}' />
        <meta property='og:url' content='${product.url}' />
        <c:forEach var="img" items="${product.images}" begin="1" end="1">
            <meta property='og:image' content='<c:out value="${img.url }" />' />
        </c:forEach>
        <meta property='og:type' content='website' />
        <meta property='og:site_name' content='DZARM.' />
    </c:when>
</c:choose>
<%-- Favourite Icon --%>
<spring:theme code="img.favIcon" text="/" var="favIconPath" />
<link rel="shortcut icon"
    href="${originalContextPath}/_ui/desktop/theme-${themeName}/assets/images/favicon.ico" />
<%-- CSS Files Are Loaded First as they can be downloaded in parallel --%>
<template:styleSheets />
<%-- Inject any additional CSS required by the page --%>
<jsp:invoke fragment="pageCss" />
<analytics:analytics />
<c:if test="${!empty googleApiVersion}">
    <script type="text/javascript"
        src="http://maps.googleapis.com/maps/api/js?v=${googleApiVersion}&amp;key=${googleApiKey}&amp;sensor=false"></script>
</c:if>

<%-- BazaarVoice JavaScript --%>
<c:if test="${showBV == 'true'}">
	<bvJavascript:bazaarvoiceHeaderJavascript />
	<script type="text/javascript">
		<c:if test="${not empty searchPageData.results}">
		$BV.ui('rr', 'inline_ratings', {
		productIds : [
			<c:forEach items="${searchPageData.results}" var="product">
			'${product.code}',
			</c:forEach>
		],
		containerPrefix : 'BVRRInlineRating'
		});
		</c:if>
	</script>
</c:if>
</head>
<body id="${themeName}" class="${pageType}">
    <jsp:doBody />
    <template:javaScript />
    <!-- User Data -->
    <div class="userData">
        <input type="hidden" class="userCode" value="${user.uid}" /> <input type="hidden"
            class="userId" value="${user.primaryKey}" /> <input type="hidden" class="userFistName"
            value="${user.firstName}" /> <input type="hidden" class="userLastName"
            value="${user.lastName}" />
    </div>
    
</body>
<debug:debugFooter />
</html>
