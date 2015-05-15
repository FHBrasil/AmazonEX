<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>
<%@ attribute name="pageCss" required="false" fragment="true"%>
<%@ attribute name="pageScripts" required="false" fragment="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/desktop/common/footer"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<template:master pageTitle="${pageTitle}">
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
		
			<header id="main-header" <c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">class="co"</c:if>>
				<nav:topNavigation />
				<header:header />
			</header>

			<div id="main-wrapper" <c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">class="co"</c:if>>

				<c:if test="${pageType == 'SINGLESTEPCHECKOUT'}">
					
					<header class="co" id="page-header">
						<div class="container">
							<h2>Compra Segura</h2>
							<h1>Finalize Sua Compra</h1>
						</div>
					</header>
					
				</c:if>
				
				<c:choose>
					<c:when test="${pageType == 'SINGLESTEPCHECKOUT' || pageType == 'CHECKOUT'}">
						<div class="container">
							<jsp:doBody />
						</div>
					</c:when>
					<c:otherwise>
						<jsp:doBody />
					</c:otherwise>
				</c:choose>
			</div>
			
			<footer id="main-footer">
				<footer:footer />
			</footer>
		
	
	</jsp:body>
</template:master>