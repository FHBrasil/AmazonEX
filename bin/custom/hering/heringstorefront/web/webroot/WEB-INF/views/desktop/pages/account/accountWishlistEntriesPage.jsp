<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="breadcrumb"
	tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="wishUrl" tagdir="/WEB-INF/tags/desktop/wishlist"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="details" tagdir="/WEB-INF/tags/desktop/showcaseproductdetail" %>
<%@ taglib prefix="wishlist" tagdir="/WEB-INF/tags/desktop/wishlist" %>


<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<div class="container">
		<header id="page-header">
			<h1>
				<spring:theme code="text.account.yourAccount" text="Your Account" />
			</h1>
			<div class="breadcrumb">
				<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
			</div>
		</header>
		<section class="page with-sidebar">
			<nav:accountNav selected="profile" />
			<div class="right">
				<section id="acc-dados-form">
					<%--<a id="share-list-link" class="pull-right" target="_blank" href="http://www.facebook.com/share.php?u=${urlPublicWishlistShare}" title="Compartilharo a lista do evento no Facebook">
						<!-- <img alt="Compartilhar" src="assets/images/text-compartilhar-wishlist.jpg"> -->
						Compartilhar lista
					</a>--%>
				<header class="header-minhas-listas">
				<h2>
					<!-- <img alt="" src="/assets/images/heart-wishlist.jpg"> -->
					<spring:theme code="text.account.wishlistOf" arguments="${user.firstName}" />
				</h2>
				</header>
			<c:choose>
				<c:when test="${not empty searchPageData.results}">
				
					<div class="list-mode product-wishlist">
						<wishlist:productList
								className="" 
								wishlistEntries="${searchPageData.results}" />
					</div>

					<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"
						supportShowAll="${isShowAllAllowed}"
						searchPageData="${searchPageData}"
						searchUrl="/my-account/my-wishlist?sort=${searchPageData.pagination.sort}"
						msgKey="text.account.viewWishlistEntries.page"
						numberPagesShown="${numberPagesShown}" />
				</c:when>
				<c:otherwise>
					<p>
						<spring:theme code="text.account.viewWishlistEntries.noEntries"
							text="You have no entries" />
					</p>
				</c:otherwise>
			</c:choose>
				</section>
			</div>
		</section>
	</div>
</template:page>