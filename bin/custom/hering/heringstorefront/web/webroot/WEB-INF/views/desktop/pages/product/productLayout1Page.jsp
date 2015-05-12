<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<template:page pageTitle="${pageTitle}">
	<c:if test="${themeName == 'black'}">
		<product:productMainPanel/>
		<product:productSecondaryPanel/>
	</c:if>

	<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
		<!-- <div chaordic="top"></div> -->
			<c:if test="${themeName != 'hering' }">
				<div class="container">
					<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
				</div>
				
				<c:if test="${themeName == 'dzarm' }">
					<product:produtDzarmImagens product="${product}" imagens="${galleryImages}"/>
				</c:if>
				<div class="container">
					<product:productMainPanel/>
				</div>
				
				<div class="container">
					<product:productSecondaryPanel/>
				</div>
			</c:if> 
			
			<c:if test="${themeName == 'hering'}">
				<product:productBackgroundImage product="${product}" galleryImages="${galleryImages}"/>
				
				<div class="container" >
					<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
					<product:productMainPanel/>
					<product:productSecondaryPanel/>
				</div>
			</c:if>
		
		
		<!-- <div chaordic="middle"></div> -->
	</c:if>
	
	<c:if test="${themeName == 'black'}">
		<!-- Compre Junto -->
		<div class="chaordic frequentlyboughttogether chaordic-fix1"></div>
		<!--div class="sku myreks_widget"></div-->
		
		<!-- Quem procura este item tambem se interessa por -->
		<!-- Voce tambem pode gostar -->
		<div class="chaordic similaritems chaordic-fix1"></div>
		
		<!-- Apos visitar este item, o que os clientes compram -->
		<!-- Quem visitou acabou comprando -->
		<div class="chaordic ultimatebuy chaordic-fix1"></div>
	</c:if>
	
	<cms:pageSlot position="Section4" var="feature" element="div" class="span-24 section4 cms_disp-img_slot">
		<cms:component component="${feature}"/>

	</cms:pageSlot>	
	
	<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
		<!-- <div chaordic="bottom"></div> -->
	</c:if>
</template:page>