<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>


<template:page pageTitle="${pageTitle}">

	<cms:pageSlot position="Section20" var="feature" element="div">
		<cms:component component="${feature}"/>
	</cms:pageSlot>
	
	<cms:pageSlot position="Section1" var="feature" element="div">
		<cms:component component="${feature}"/>
	</cms:pageSlot>

	<div class="container">
		<section class="page with-sidebar">
			
			<div class="left categories">
				<header>
					<h1>Bermudas e shorts</h1>
				</header>
			</div>
			
			<div class="right">
				<cms:pageSlot position="Special" var="feature" element="div">
					<cms:component component="${feature}"/>
				</cms:pageSlot>
			</div>
			
		</section>
	</div>


</template:page>