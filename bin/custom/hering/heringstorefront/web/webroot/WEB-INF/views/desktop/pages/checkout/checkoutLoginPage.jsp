<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<template:page pageTitle="${pageTitle}">
	
	<%-- CHECKOUT LOGIN FOR DZARM STORE --%>
	<c:if test="${themeName == 'black'}">
	
		<div id="breadcrumb" class="breadcrumb">
			<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
		</div>
	
		<div id="globalMessages">
			<common:globalMessages/>
		</div>
		
		<cms:pageSlot position="TopContent" var="feature" element="div" class="span-24 cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	
		<div class="span-24">
			<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
				<c:set var="spanStyling" value="span-8"/>
			</sec:authorize>
			<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
				<c:set var="spanStyling" value="span-12"/>
				<c:set var="spanStylingLast" value=" last"/>
			</sec:authorize>
	
			<div class="${spanStyling}">
				<c:url value="/login/checkout/register" var="registerAndCheckoutActionUrl" />
				<user:register actionNameKey="checkout.login.registerAndCheckout" action="${registerAndCheckoutActionUrl}"/>
			</div>
	
			<c:if test="false">
				<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
					<div class="${spanStyling}">
						<c:url value="/login/checkout/guest" var="guestCheckoutUrl" />
						<user:guestCheckout actionNameKey="checkout.login.guestCheckout" action="${guestCheckoutUrl}"/>
					</div>
				</sec:authorize>
			</c:if>
	
			<div class="${spanStyling} last">
				<c:url value="/checkout/j_spring_security_check" var="loginAndCheckoutActionUrl" />
				<user:login actionNameKey="checkout.login.loginAndCheckout" action="${loginAndCheckoutActionUrl}"/>
			</div>
		</div>
		
		<cms:pageSlot position="BottomContent" var="feature" element="div" class="span-24 cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	
	</c:if>
	
	<%-- CHECKOUT LOGIN FOR HERING STORE --%>
	<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	
		<div id="globalMessages">
			<common:globalMessages/>
		</div>
		
		<cms:pageSlot position="TopContent" var="feature" element="div" class="span-24 cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
		
		<header id="page-header">
			<h1>Identificação</h1>
		</header>
		
		<section class="identificacao-checkout page">
			
				<%-- REGISTER --%>
				<%-- register.tag --%>
				<c:url value="/login/checkout/register" var="registerAndCheckoutActionUrl" />
				<user:register actionNameKey="checkout.login.registerAndCheckout" action="${registerAndCheckoutActionUrl}"/>
			
			<section class="col-2 column">
				
				<div class="container half">
				
					<%-- LOGIN --%>	
					<%-- login.tag --%>
					<div class="left">
						<c:url value="/checkout/j_spring_security_check" var="loginAndCheckoutActionUrl" />
						<user:login actionNameKey="checkout.login.loginAndCheckout" action="${loginAndCheckoutActionUrl}"/>
					</div>
				
					<%-- GUEST REGISTER --%>
					<%-- guestCheckout.tag --%>
					<c:if test="false">
						<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
							<div class="right">
								<c:url value="/login/checkout/guest" var="guestCheckoutUrl" />
								<user:guestCheckout actionNameKey="checkout.login.guestCheckout" action="${guestCheckoutUrl}"/>
							</div>
						</sec:authorize>
					</c:if>
				</div>
				
				<div class="banner">
					<c:choose>
						<c:when test="${themeName == 'dzarm'}">
							<img src="/store/_ui/desktop/theme-dzarm/images/banner-checkout.jpg" alt="Dzarm">
							<p>Faça o seu cadastro e aproveite as vantagens em ser o nosso cliente.</p>
							<p><strong>Segurança:</strong> Site 100% seguro.</p>
						</c:when>
						<c:when test="${themeName == 'foryou'}">
							<img src="/store/_ui/desktop/theme-foryou/images/banner-identificacao-checkout.jpg" alt="Hering for you">
							<p>Faça o seu cadastro e aproveite as vantagens em ser o nosso cliente.</p>
							<p><strong>Segurança:</strong> Site 100% seguro.</p>
						</c:when>
						<c:otherwise>
							<img src="/store/_ui/desktop/theme-hering/images/identificacao-banner-wide.jpg" alt="Cia. Hering">
							<p>Faça o seu cadastro e aproveite as vantagens em ser o nosso cliente.</p>
							<p><strong>Segurança:</strong> Site 100% seguro.</p>
						</c:otherwise>
					</c:choose>
				</div>
				
			</section>
			
		</section>
		
	</c:if>
	
</template:page>
