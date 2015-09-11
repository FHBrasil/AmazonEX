<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="cms" uri="/cms2lib/cmstags/cmstags.tld" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="/WEB-INF/tld/ycommercetags.tld" %>

<template:page pageTitle="${pageTitle}">
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>

	<div id="globalMessages">
		<common:globalMessages/>
	</div>

	<div class="span-4 side-content-slot cms_banner_slot">
		<cms:slot var="feature" contentSlot="${slots.SideContent}">
			<cms:component component="${feature}"/>
		</cms:slot>
	</div>

	<div class="span-20 right last">
		<ycommerce:testId code="orderConfirmation_yourOrderResults_text">
			<div class="span-20 your_order">
			
			
				<h1>
					<span id="bestellnummer" style="display:block;">
						${orderData.code}
					</span>
					<spring:theme code="checkout.orderConfirmation.thankYou" arguments="${orderData.code}"/>
				</h1>

				<p>
					<spring:theme code="checkout.orderConfirmation.copySentTo" arguments="${email}"/>
				</p>
			</div>
		</ycommerce:testId>

		<div class="span-20 last">
			<div class="span-10 top-content-slot cms_banner_slot">
				<cms:slot var="feature" contentSlot="${slots.TopContent}">
					<cms:component component="${feature}"/>
				</cms:slot>
			</div>
		</div>

		<div class="span-20 last delivery_stages">
			<div class="span-5">
				<order:deliveryAddressItem order="${orderData}"/>
			</div>

			<div class="span-5">
				<order:deliveryMethodItem order="${orderData}"/>
			</div>

			<div class="span-10 last">
				<order:paymentMethodItem order="${orderData}"/>
			</div>
		</div>

		<div class="span-20 last">
			<order:orderDetailsItem order="${orderData}"/>
			<div class="span-12">
				<order:receivedPromotions order="${orderData}"/>
			</div>
			<div class="span-8 right last">
				<order:orderTotalsItem order="${orderData}" containerCSS="positive"/>
			</div>
		</div>
	</div>
</template:page>
