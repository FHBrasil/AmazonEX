<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>
<%@ attribute name="pageCss" required="false" fragment="true"%>
<%@ attribute name="pageScripts" required="false" fragment="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>
<%@ attribute name="showBV" required="false" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/desktop/common/footer"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<template:master pageTitle="${pageTitle}" showBV="${showBV}">
    <jsp:attribute name="pageCss">
		<jsp:invoke fragment="pageCss" />
	</jsp:attribute>
    <jsp:attribute name="pageScripts">
		<jsp:invoke fragment="pageScripts" />
	</jsp:attribute>
    <jsp:body>
	
<!-- 			<input type="hidden" name="baseStore" id="baseStore" value="hering"/>			 -->
			<c:if test="${pageType != 'SINGLESTEPCHECKOUT'}">
				<div id="header" class="clearfix">
					<header:miniHeader />
				</div>
			</c:if>
		
			<div class="container ${pageType == 'SINGLESTEPCHECKOUT' ? 'co' : ''}">
				<%--<nav:topNavigation />--%>
				<header:header />
			</div>
			
			<div class="container ${pageType == 'SINGLESTEPCHECKOUT' ? 'onestep150526' : ''}">
				<%-- 
				<c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">
					
					<header class="co" id="page-header">
						<div class="container">
							<h2><spring:theme code="text.fliegercommerce.texto63"/></h2>
							<h1><spring:theme code="text.fliegercommerce.texto9"/></h1>
						</div>
					</header>
					
				</c:if>
				--%>
				<jsp:doBody />				
			</div>
			
			<footer>
				<footer:footer />
			</footer>
	</jsp:body>
</template:master>