<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<template:page pageTitle="${pageTitle}">
	<jsp:attribute name="pageScripts">
		<product:productDetailsJavascript/>
	</jsp:attribute>

	<jsp:body>
		<c:if test="${not empty message}">
			<spring:theme code="${message}"/>
		</c:if>
		<div id="breadcrumb" class="breadcrumb">
			<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
		</div>
		<div id="globalMessages">
			<common:globalMessages/>
		</div>
		<cms:pageSlot position="Section1" var="comp" element="div" class="span-24 section1 cms_disp-img_slot">
			<cms:component component="${comp}"/>
		</cms:pageSlot>
		
		<div class="span-20">
			<div class="span-20" id="productDetailUpdateable">

				<cms:pageSlot position="ProductImages" var="comp"  >
					<cms:component component="${comp}"/>
				</cms:pageSlot>	

				<div class="span-8 last">
					<div class="prod">
						<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
							<h1>${product.name}</h1>
							<product:productPricePanel product="${product}"/>
						</ycommerce:testId>
						<cms:pageSlot position="ProductDetailPanelSection" var="feature" element="div" class="span-8 detailpanelsection last">
							<cms:component component="${feature}"/>
						</cms:pageSlot>					
						<cms:pageSlot position="SocialButtons" var="feature" element="div" class="span-8 socialbuttons last">
							<div class="socialbutton span-2 ${(elementPos%4 == 3) ? 'last' : ''}">
								<cms:component component="${feature}"  />
							</div>
						</cms:pageSlot>	
						
						<cms:pageSlot position="SocialWidgets" var="feature" element="div" class="span-8 socialwidgets last">
							<cms:component component="${feature}"  />
						</cms:pageSlot>	
					</div>
				</div>
				
				<cms:pageSlot position="Section2" var="feature" element="div" class="span-8 section2 cms_disp-img_slot last">
					<cms:component component="${feature}"/>
				</cms:pageSlot>				
			</div>
			
			<cms:pageSlot position="Section3" var="feature" element="div" class="span-20 section3 cms_disp-img_slot">
				<cms:component component="${feature}"/>
			</cms:pageSlot>
			
			<div class="span-20">
				<cms:pageSlot position="UpSelling" var="comp" element="div" class="span-4">
					<cms:component component="${comp}"/>
				</cms:pageSlot>
				<div class="span-16 right last">
					<product:productPageTabs />
				</div>
			</div>
		</div>
		<cms:pageSlot position="CrossSelling" var="comp" element="div" class="span-4 last">
			<cms:component component="${comp}"/>
		</cms:pageSlot>

		<cms:pageSlot position="Section4" var="feature" element="div" class="span-24 section4 cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	</jsp:body>
</template:page>