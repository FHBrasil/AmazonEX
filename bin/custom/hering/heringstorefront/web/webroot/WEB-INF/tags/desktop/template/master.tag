<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true" %>
<%@ attribute name="metaDescription" required="false" %>
<%@ attribute name="metaKeywords" required="false" %>
<%@ attribute name="pageCss" required="false" fragment="true" %>
<%@ attribute name="pageScripts" required="false" fragment="true" %>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="analytics" tagdir="/WEB-INF/tags/shared/analytics" %>
<%@ taglib prefix="debug" tagdir="/WEB-INF/tags/shared/debug" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="${currentLanguage.isocode}">
<head>
	
	<title>
		${not empty pageTitle ? pageTitle : not empty cmsPage.title ? cmsPage.title : 'Accelerator Title'}
	</title>
	
	<%-- Meta Content --%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	
	<%-- Additional meta tags --%>	
	<c:forEach var="metatag" items="${metatags}">
		<c:if test="${not empty metatag.content}" >
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
	<spring:theme code="img.favIcon" text="/" var="favIconPath"/>
	<c:if test="${themeName == 'black' }">
		<link rel="shortcut icon" type="image/x-icon" media="all" href="${originalContextPath}${favIconPath}" />
	</c:if>
	<c:if test="${themeName == 'hering' }">
		<link rel="shortcut icon" href="${originalContextPath}/_ui/desktop/theme-hering/assets/images/favicon.ico" />
	</c:if>
	<c:if test="${themeName == 'dzarm' }">
		<link rel="shortcut icon" href="${originalContextPath}/_ui/desktop/theme-dzarm/assets/images/favicon-new.ico" />
	</c:if>
	<c:if test="${themeName == 'foryou' }">
		<link rel="shortcut icon" href="${originalContextPath}/_ui/desktop/theme-foryou/assets/images/favicon.png" />
	</c:if>

	<%-- CSS Files Are Loaded First as they can be downloaded in parallel --%>
	<template:styleSheets/>

	<%-- Inject any additional CSS required by the page --%>
	<jsp:invoke fragment="pageCss"/>
	<analytics:analytics/>
	
	<c:if test="${themeName == 'black'}" >
		<script type="text/javascript" src="${commonResourcePath}/js/vizuryTag.js"
			id="scriptVizury"></script>	
	</c:if>

<c:if test="${!empty googleApiVersion}">
	<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?v=${googleApiVersion}&amp;key=${googleApiKey}&amp;sensor=false"></script>
</c:if>
	
</head>

	<%-- MASTER TAG FOR DZARM STORE --%>
	<c:if test="${themeName == 'black'}">

		<body class="${pageBodyCssClasses} ${cmsPageRequestContextData.liveEdit ? ' yCmsLiveEdit' : ''} language-${currentLanguage.isocode}">
			<%-- Inject the page body here --%>
			<jsp:doBody/>
		
			<form name="accessiblityForm">
				<input type="hidden" id="accesibility_refreshScreenReaderBufferField" name="accesibility_refreshScreenReaderBufferField" value=""/>
			</form>
			<div id="ariaStatusMsg" class="skip" role="status" aria-relevant="text" aria-live="polite"></div>
			<div class="go-to-top-container"><div class="go-to-top hide"></div></div>
			<%-- Load JavaScript required by the site --%>
			<template:javaScript/>
		
			<!-- User Data -->
			<div class="userData">
				<input type="hidden" class="userCode" value="${user.uid}"/>
				<input type="hidden" class="userId" value="${user.primaryKey}"/>
				<input type="hidden" class="userFistName" value="${user.firstName}" />
				<input type="hidden" class="userLastName" value="${user.lastName}" />
			</div>
				
			<%-- Inject any additional JavaScript required by the page --%>
			<jsp:invoke fragment="pageScripts"/>	
		    <c:if test="${pageType == 'HOMEPAGE'}">
		        <script type="text/javascript" src="https://nxtck.com/act.php?tag=40871"></script>
		    </c:if>
		</body>
	
	</c:if>
	
	<%-- MASTER TAG FOR HERING STORE --%>
	<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	  
		<body id="${themeName}" class="${pageType}">
			<jsp:doBody/>
			<template:javaScript/>
			
			<!-- User Data -->
			<div class="userData">
				<input type="hidden" class="userCode" value="${user.uid}"/>
				<input type="hidden" class="userId" value="${user.primaryKey}"/>
				<input type="hidden" class="userFistName" value="${user.firstName}" />
				<input type="hidden" class="userLastName" value="${user.lastName}" />
			</div>
		</body>
	
	</c:if>
	
<debug:debugFooter/>

</html>
