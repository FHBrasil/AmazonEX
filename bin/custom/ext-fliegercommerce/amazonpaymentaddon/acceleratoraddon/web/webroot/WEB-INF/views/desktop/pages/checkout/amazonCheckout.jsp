<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>

<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
	<div id="globalMessages">
		<common:globalMessages />
	</div>
	<cms:pageSlot position="BottomHeaderSlot" var="component">
		<cms:component component="${component}" />
	</cms:pageSlot>
	<cms:pageSlot position="Section1" var="component">
		<cms:component component="${component}" />
	</cms:pageSlot>
	<cms:pageSlot position="Section2A" var="component">
		<cms:component component="${component}" />
	</cms:pageSlot>
	<cms:pageSlot position="Section2B" var="component">
		<cms:component component="${component}" />
	</cms:pageSlot>
	<cms:pageSlot position="Section3" var="component">
		<cms:component component="${component}" />
	</cms:pageSlot>
	
</template:page>