<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<link rel="stylesheet" href="${themeResourcePath}/assets/fonts/steelfish.css">
	<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/reset.css" />
	<link rel="stylesheet" type="text/css"  media="all" href="${themeResourcePath}/assets/css/base.css" />
	<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/assets/css/pages/identificacao.css" />
	<style>html, body { background: transparent; }.modal { display: block; }</style>
	
	<section id="modal-email-recuperacao">
		<section class="result">
			<header>
				<h1><spring:theme code="forgottenEmail.titleRecovered" /></h1>
			</header>
	
			<p><spring:theme code="forgottenEmail.descriptionRecovered" /><b>${email}</b></p>
	
			<a href="#" onClick="parent.jQuery.fancybox.close();" class="btn btn-red"><spring:theme code="forgottenEmail.btnClose" /></a>
		</section>
	</section>
</c:if>