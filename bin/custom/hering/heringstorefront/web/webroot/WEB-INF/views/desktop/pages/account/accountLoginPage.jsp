<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<%-- ACCOUNT LOGIN FOR DZARM STORE --%>
<c:if test="${themeName == 'black'}">
	<template:page pageTitle="${pageTitle}">
	
		<div id="breadcrumb" class="breadcrumb">
			<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
		</div>
	
		<div id="globalMessages">
			<common:globalMessages/>
		</div>
		<cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 side-content-slot cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	
		<div class="span-24">
	
			<cms:pageSlot position="TopContent" var="feature" element="div" class="span-24 top-content-slot cms_disp-img_slot last">
				<cms:component component="${feature}"/>
			</cms:pageSlot>
	
			<div class="span-12">
				<c:url value="/login/register" var="registerActionUrl" />
				<user:register actionNameKey="register.submit" action="${registerActionUrl}"/>
			</div>
	
			<div class="span-12 last">
				<c:url value="/login/loginOver" var="loginActionUrl" />
				<user:login actionNameKey="login.login" action="${loginActionUrl}"/>
			</div>
	
		</div>
	
	</template:page>
</c:if>

<%-- ACCOUNT LOGIN FOR HERING STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">

	<template:page pageTitle="${pageTitle}">
	
		<%-- globalMessages.tag --%>
		<common:globalMessages/>
		
		<cms:pageSlot position="SideContent" var="feature" element="div">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	
		<cms:pageSlot position="TopContent" var="feature" element="div">
			<cms:component component="${feature}"/>
		</cms:pageSlot>

	<div class="container">
		<header id="page-header">
			<h1>Identificação</h1>
		</header>
		
		<section class="identificacao page">
			<div class="column-wrapper">

				<%-- register.tag --%>
				<c:url value="/login/register" var="registerActionUrl" />
				<user:register actionNameKey="register.submit" action="${registerActionUrl}"/>

				<%-- login.tag --%>
				<c:url value="/login/loginOver" var="loginActionUrl" />
				<user:login actionNameKey="login.login" action="${loginActionUrl}"/>
			
				<%-- loginBanner.tag --%>
				<user:loginBanner/>
				
			</div>
		</section>
	</div>	
	</template:page>
		
</c:if>