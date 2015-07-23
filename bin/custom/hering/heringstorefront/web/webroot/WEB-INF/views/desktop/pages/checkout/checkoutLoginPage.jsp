<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<template:page pageTitle="${pageTitle}">
<section class="identificacao-checkout page">
	<div class="col-sm-4">
<%-- 		<c:url value="/login/checkout/register" var="registerAndCheckoutActionUrl" /> --%>
<%-- 		    <user:register actionNameKey="checkout.login.registerAndCheckout" --%>
<%-- 		          action="${registerAndCheckoutActionUrl}" /> --%>
<%--                 <sec:authorize ifAnyGranted="ROLE_ANONYMOUS"> --%>
		                <c:url value="/login/checkout/guest" var="guestCheckoutUrl" />
		                <user:guestCheckout actionNameKey="checkout.login.guestCheckout"
		                      action="${guestCheckoutUrl}" /> 
<%--         		</sec:authorize> --%>
	</div>
	<div class="col-sm-4">
	       <c:url value="/checkout/j_spring_security_check" var="loginAndCheckoutActionUrl" />
	       <user:login actionNameKey="checkout.login.loginAndCheckout"
	             action="${loginAndCheckoutActionUrl}" /> 
	    <c:if test="false">
	        <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
	            <div class="right">
	                <c:url value="/login/checkout/guest" var="guestCheckoutUrl" />
	                <user:guestCheckout actionNameKey="checkout.login.guestCheckout"
	                      action="${guestCheckoutUrl}" /> 
	            </div>
	        </sec:authorize>
	    </c:if>
	</div>
	<div class="col-sm-4">
		<div class="panel panel-default panel-minicart150203">
				<div class="panel-body">
					<h3>Ihr Warenkorb</h3>
					<div class="row">
					<div class="col-xs-6">
						Warenwert
					</div>
					<div class="col-xs-6 text-right">
						<format:price priceData="${cartData.subTotal}" />
					</div>
					<div class="col-xs-6">
						Rabatt
					</div>
					<div class="col-xs-6 text-right">
						<format:price priceData="${cartData.totalDiscounts}" />
					</div>
					<div class="col-xs-6">
						Versand
					</div>
					<div class="col-xs-6 text-right">
						<format:price priceData="${cartData.deliveryCost}" displayFreeForZero="TRUE" />
					</div>
					<div class="col-xs-6 sum150203">
						<h4>Summe</h4>
					</div>
					<div class="col-xs-6 sum150203 text-right">
						<h4><format:price priceData="${cartData.totalPrice}" /></h4>
					</div>
				</div>
				<div class="col-xs-12 row margin-top">
					<small class="trusted150203">
					inkl. <span class="hidden-sm">kostenloser </span> <a href="http://www.trustedshops.de/guetesiegel/kaeuferschutz.html#fifth_aspect" target="_blank">Geld-Zur&uuml;ck-Garantie</a>
					</small>
				</div>
			</div>
		
			<div class="col-xs-12 margin-top">
				<p><small>Probleme beim Einkauf?</small><br><span class="h3"><span class="glyphicon glyphicon-earphone"></span> 089 904 750 62 00</span></p>
			</div>
		</div>
	</div>
</section>
    
<!--       <div id="globalMessages"> -->
<%--         <common:globalMessages /> --%>
<!--     </div> -->
<%--     <cms:pageSlot position="TopContent" var="feature" element="div" --%>
<%--          class="span-24 cms_disp-img_slot">  --%>
<%--         <cms:component component="${feature}" /> --%>
<%--     </cms:pageSlot> --%>
<!--     <header id="page-header"> -->
<!--         <h1><spring:theme code="text.fliegercommerce.texto43"/></h1> -->
<!--     </header> -->
<%--     <section class="identificacao-checkout page"> --%>
<%--         <c:url value="/login/checkout/register" var="registerAndCheckoutActionUrl" /> --%>
<%--         <user:register actionNameKey="checkout.login.registerAndCheckout" --%>
<%--              action="${registerAndCheckoutActionUrl}" />  --%>
<%--         <section class="col-2 column"> --%>
<!--             <div class="container half"> -->
<!--                 <div class="left"> -->
<%--                     <c:url value="/checkout/j_spring_security_check" var="loginAndCheckoutActionUrl" /> --%>
<%--                     <user:login actionNameKey="checkout.login.loginAndCheckout" --%>
<%--                          action="${loginAndCheckoutActionUrl}" />  --%>
<!--                 </div> -->
<%--                 <c:if test="false"> --%>
<%--                     <sec:authorize ifAnyGranted="ROLE_ANONYMOUS"> --%>
<!--                         <div class="right"> -->
<%--                             <c:url value="/login/checkout/guest" var="guestCheckoutUrl" /> --%>
<%--                             <user:guestCheckout actionNameKey="checkout.login.guestCheckout" --%>
<%--                                  action="${guestCheckoutUrl}" /> --%>
<!--                         </div> -->
<%--                     </sec:authorize> --%>
<%--                 </c:if> --%>
<!--             </div> -->
<!--             <div class="banner"> -->
<%--                 <img src="/store/_ui/desktop/theme-${themeName}/images/identificacao-banner-wide.jpg"> --%>
<!--                 <p><spring:theme code="text.fliegercommerce.texto44"/></p> -->
<!--                 <p> -->
<!--                     <strong><spring:theme code="text.fliegercommerce.texto45"/></strong>  -->
<!--                 </p> -->
<!--             </div> -->
<%--         </section> --%>
<%--     </section> --%>
</template:page>

