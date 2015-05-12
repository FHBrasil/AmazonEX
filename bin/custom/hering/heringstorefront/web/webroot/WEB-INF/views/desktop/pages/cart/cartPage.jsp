<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<spring:theme text="Your Shopping Cart" var="title" code="cart.page.title"/>
<c:url value="/cart/checkout/" var="checkoutUrl"/>

<template:page pageTitle="${pageTitle}">

		<spring:theme code="basket.add.to.cart" var="basketAddToCart"/>
		<spring:theme code="cart.page.checkout" var="checkoutText"/>
		<common:globalMessages/>
		<cart:cartRestoration/>
		<cart:cartValidation/>
		
		
	
	<%-- CART PAGE FOR DZARM STORE --%>
	<c:if test="${themeName == 'black'}">
		
		<cms:pageSlot position="TopContent" var="feature" element="div" class="span-24">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
		
		<!-- CART Data -->
		<div class="cartData">
			<input type="hidden" class="cartCode" value="${cartData.code}" />
		</div>
	    
	    <!-- Compre tamb�m -->
		<div class="chaordic shoppingcart chaordic-fix3"></div>
		
			<spring:url value="${continueUrl}" var="continueShoppingUrl" htmlEscape="true"/>
			<c:if test="${not empty cartData.entries}">

					<button id="checkoutButtonTop" class="doCheckoutBut positive right" type="button" data-checkout-url="${checkoutUrl}"><spring:theme code="checkout.checkout" /></button>
					<cart:cartItems cartData="${cartData}" product="${product}"/>
	
						<div class="clearfix">
							<div class="span-16">
								<cart:cartPromotions cartData="${cartData}"/>
								&nbsp;
								<c:if test="${true}">
								<cart:cartPotentialExpressCheckoutInfoBox/>
								</c:if>
								<c:if test="${not empty cartData.entries}">
									<cart:cartPotentialPromotions cartData="${cartData}"/>
								</c:if>
								
								<div id="voucher">
									<cart:cartVoucher />
								</div>
							</div>
							<div class="span-8 last">
								<cart:cartTotals cartData="${cartData}" showTaxEstimate="true" showCalculateDeliveryComponent="true"/>
							</div>
						</div>
					<!-- Banner FRETE -->
						<cms:pageSlot position="BottomContent" var="feature" element="div" class="span-24">
							<cms:component component="${feature}"/>
						</cms:pageSlot>
					<!-- / Banner FRETE -->
					
					<button id="checkoutButtonBottom" class="doCheckoutBut positive right" type="button" data-checkout-url="${checkoutUrl}"><spring:theme code="checkout.checkout" /></button>
					<a class="button-continue" href="${continueShoppingUrl}">Continuar Comprando</a>
						
			</c:if>
			
			<c:if test="${empty cartData.entries}">
				<div class="span-24">
					<div class="span-24 wide-content-slot cms_disp-img_slot">
					
				<div class="carrinho-vazio">
					<h2><spring:theme code="basket.page.empty" text="Sua sacola esta vazia"/></h2>
					<div class="mensagem-carrinho">
						<spring:theme code="basket.page.emptyInfo" text="Sacola vazia informacao"/>
					</div>
					<div class="bt-voltar">
						<c:if test="${empty cartData.entries}">
							<a class="button-continue" href="${continueShoppingUrl}">Continuar Comprando</a>
						</c:if>
					</div>
				</div>
					
<%-- 						<cms:pageSlot position="MiddleContent" var="feature" element="div"> --%>
<%-- 							<cms:component component="${feature}"/> --%>
<%-- 						</cms:pageSlot> --%>

<%-- 						<cms:pageSlot position="BottomContent" var="feature" element="div"> --%>
<%-- 							<cms:component component="${feature}"/> --%>
<%-- 						</cms:pageSlot> --%>
					</div>
				</div>
			</c:if>
		
			
			<c:if test="${showCheckoutStrategies && not empty cartData.entries}" >
				<div class="span-24">
					<div class="right">
						<input type="hidden" name="flow" id="flow"/>
						<input type="hidden" name="pci" id="pci"/>
						<select id="selectAltCheckoutFlow" class="doFlowSelectedChange">
							<option value="multistep"><spring:theme code="checkout.checkout.flow.select"/></option>
							<option value="multistep"><spring:theme code="checkout.checkout.multi"/></option>
							<option value="multistep-pci"><spring:theme code="checkout.checkout.multi.pci"/></option>
						</select>
						<select id="selectPciOption" style="margin-left: 10px; display: none;">
							<option value=""><spring:theme code="checkout.checkout.multi.pci.select"/></option>
							<c:if test="${!isOmsEnabled}">
								<option value="default"><spring:theme code="checkout.checkout.multi.pci-ws"/></option>
								<option value="hop"><spring:theme code="checkout.checkout.multi.pci-hop"/></option>
							</c:if>
							<option value="sop"><spring:theme code="checkout.checkout.multi.pci-sop" text="PCI-SOP" /></option>
						</select>
					</div>
				</div>
			</c:if>
	
			<c:if test="${not empty cartData.entries}" >
				<cms:pageSlot position="Suggestions" var="feature" element="div" class="span-24">
					<cms:component component="${feature}"/>
				</cms:pageSlot>
			</c:if>
	</c:if>
	
	<%-- CART PAGE FOR HERING STORE --%>
	<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
		<div id="main-wrapper">
		<div class="container">
			<!-- CART Data -->
			<div class="cartData">
				<input type="hidden" class="cartCode" value="${cartData.code}" />
			</div>
			
			<%-- HEADER CART --%>
			<div chaordic="top">
			</div>			
			<header id="page-header"><h1>Minha Sacola</h1></header>			 
			
			<%-- SE O CART ESTIVER VAZIO, ADICIONA A CLASSE EMPTY NA DIV --%>
			<section id="cart" <c:if test="${empty cartData.entries}">class="empty"</c:if>>
			
			<%-- // SE O CARRINHO nao ESTIVER VAZIO --%>
			<c:if test="${not empty cartData.entries}">
				
				<%-- cartItems.tag --%>
				<cart:cartItems cartData="${cartData}" product="${product}"/>
				
				<%-- cartTotals.tag --%>
				<cart:cartTotals cartData="${cartData}" showTaxEstimate="true" showCalculateDeliveryComponent="true"/>

			</c:if>

			<%-- // SE O CARRINHO ESTIVER VAZIO --%>
			<c:if test="${empty cartData.entries}">
			<div class="container">
				<header><h1><spring:theme code="basket.page.empty" text="Sua sacola esta vazia"/></h1></header>
				<p><spring:theme code="basket.page.emptyInfoHering" text="Sacola vazia informacao"/></p>
				<a href="${request.contextPath}" class="btn-continuar-navegando btn">Continuar navegando</a>
			</div>
			</c:if>			
		</div>
		</div>		
	</c:if>
</template:page>