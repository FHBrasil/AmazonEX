<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<template:page pageTitle="${pageTitle}">
    <product:productBackgroundImage product="${product}" galleryImages="${galleryImages}" />
    <div class="container">
        <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
        <product:productMainPanel />
        <product:productSecondaryPanel />
    </div>
    <cms:pageSlot position="Section4" var="feature" element="div"
        class="span-24 section4 cms_disp-img_slot">
        <cms:component component="${feature}" />
    </cms:pageSlot>
</template:page>