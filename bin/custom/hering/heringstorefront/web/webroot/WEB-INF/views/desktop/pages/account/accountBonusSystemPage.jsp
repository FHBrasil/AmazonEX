<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>


<template:page pageTitle="${pageTitle}">
<div class="container">
	<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>	
</div>
<!-- 	<div id="main-wrapper"> -->
<!-- 		<div class="container"> -->
<!-- 			<header id="page-header"> -->
<%-- 				<h1><spring:theme code="text.account.yourAccount" text="Your Account" /></h1> --%>
<!-- 				<div class="breadcrumb"> -->
<%-- 					<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/> --%>
<!-- 				</div> -->
<!-- 			</header> -->
<!-- 			<section class="order-details page with-sidebar loyalty-program"> -->
<%-- 				<nav:accountNav/> --%>
<!-- 				<div class="right"> -->
					<cms:pageSlot position="Section1" var="feature" element="div" class="span-6 zoneA">
						<cms:component component="${feature}" />
					</cms:pageSlot>
<!-- 				</div> -->
<!-- 			</section> -->
<!-- 		</div> -->
<!-- 	</div> -->
</template:page>
