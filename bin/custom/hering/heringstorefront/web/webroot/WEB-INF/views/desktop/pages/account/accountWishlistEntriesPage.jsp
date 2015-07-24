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
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="wishUrl" tagdir="/WEB-INF/tags/desktop/wishlist"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="details" tagdir="/WEB-INF/tags/desktop/showcaseproductdetail" %>
<%@ taglib prefix="wishlist" tagdir="/WEB-INF/tags/desktop/wishlist" %>

<template:page pageTitle="${pageTitle}">
<div id="globalMessages">
	<common:globalMessages/>
</div>
<div class="container">
	<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
</div>
<div class="container">
	<h1 class="col-xs-12 col-sm-5"><spring:theme code="text.account.wishlistOf" arguments="${user.firstName}" /> 
	<ycommerce:testId code="searchResults_productsFound_label">
		<small>
			<spring:theme arguments="${searchPageData.pagination.totalNumberOfResults}" />
		</small>
	</ycommerce:testId>
	</h1>
	<div class="col-xs-12 col-sm-7 text-right">
		<!-- render in public mode (user is not owner) 
		<a href="#wishlistModal" data-toggle="modal" class="btn btn-primary btn-lg">Wunschliste teilen <span class="babicon babicon-share"></span></a>
		END render... -->
		<!-- render in provate mode (user is owner of wishlist) 
		<a href="#wishlistOwnerModal" data-toggle="modal" class="btn btn-primary btn-lg">Wunschliste bearbeiten und teilen <span class="babicon babicon-share"></span></a>
		END render... -->
	</div>
</div>
<div class="container">
	<c:choose>
		<c:when test="${not empty searchPageData.results}">
			<div class="text-center panel150506">
				<wishlist:productList className="" wishlistEntries="${searchPageData.results}" />
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
				<spring:theme code="text.account.viewWishlistEntries.noEntries" text="You have no entries" />
			</p>
		</c:otherwise>
	</c:choose>
</div>

</template:page>


<!-- BEGIN Modal HTML (This part should be loaded asynchronous until golive -->

	<div id="wishlistModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">					
				<form>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Wunschliste teilen</h4>
                </div>
                <div class="modal-body">
					<h2>Option 1: Facebook-Freunde einladen</h2>
					<p>Teile diese Wunschliste mit Freunden auf Facebook:</p>
					<a href="http://www.facebook.com/share.php?u=http://www.babyartikel.de/wishlist/8853012284057" class="btn btn-primary btn-lg">Wunschliste auf Facebook teilen</a>
					<h2>Option 2: Link teilen</h2>
					<div class="form-group">
						<p>Kopiere folgenden Link und versende ihn per Mail oder Messenger:</p>
						<input type="text" class="form-control" id="shareUrl" value="http://www.babyartikel.de/wishlist/8853012284057">
					</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Schlie&szlig;en</button>
                </div>
				</form>
            </div>
        </div>
    </div>
	
	
	
	<div id="wishlistOwnerModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">					
				<form>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Wunschliste bearbeiten und teilen</h4>
                </div>
                <div class="modal-body">
					<p>Teile die Wunschliste mit Freunden, damit Sie W&uuml;nsche erf&uuml;llen k&ouml;nnen!</p>
						<div class="form-group">
							<label for="wishlistName">Name der Wunschliste</label>
							<input type="text" class="form-control" id="wishlistName" value="Willi's Wunschliste">
						</div>
						<div class="form-group">
							<label for="inputDeliverySalutation">Anlass</label>
							<select class="form-control" id="wishlistType">
								<option selected="true">Entbindungstermin</option>
								<option>Geburtstag</option>
								<option>Baby Shower Party</option>
								<option>Sonstiges</option>
							</select>
						</div>
						<div class="form-group">
							<label for="deadline" class="control-label">Termin</label>
							<div class="input-group input-append date" id="deadline" data-date="01.01.2015" data-date-format="dd.mm.yyyy">
								<input type="text" size="16" class="form-control span2" value="01.01.2015">
								<span class="input-group-btn add-on">
									<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-calendar"></span></button>
								</span>
							</div>
						</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
                    <a href="#wishlistModal" data-toggle="modal" data-dismiss="modal" class="btn btn-primary">Speichern und weiter</a>
                </div>
				</form>
            </div>
        </div>
    </div>

<!-- END Modal HTML -->



<%-- old code <template:page pageTitle="${pageTitle}">
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
					<a id="share-list-link" class="pull-right" target="_blank" href="http://www.facebook.com/share.php?u=${urlPublicWishlistShare}" title="Compartilharo a lista do evento no Facebook">
						<!-- <img alt="Compartilhar" src="assets/images/text-compartilhar-wishlist.jpg"> -->
						Compartilhar lista
					</a>
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
</template:page>--%>