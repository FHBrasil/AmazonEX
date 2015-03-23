<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="hideHeaderLinks" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<%-- Test if the UiExperience is currently overriden and we should show the UiExperience prompt --%>
<c:if test="${uiExperienceOverride and not sessionScope.hideUiExperienceLevelOverridePrompt}">
	<c:url value="/_s/ui-experience?level=" var="clearUiExperienceLevelOverrideUrl"/>
	<c:url value="/_s/ui-experience-level-prompt?hide=true" var="stayOnDesktopStoreUrl"/>
	<div class="backToMobileStore">
		<a href="${clearUiExperienceLevelOverrideUrl}"><span class="greyDot">&lt;</span><spring:theme code="text.swithToMobileStore" /></a>
		<span class="greyDot closeDot"><a href="${stayOnDesktopStoreUrl}">x</a></span>
	</div>
</c:if>
<cms:pageSlot position="TopHeaderSlot" var="component">
	<cms:component component="${component}"/>
</cms:pageSlot>




<div class="container head141217">
	<div class="row">
		<cms:pageSlot position="HeaderLinks" var="component">
			<cms:component component="${component}"/>
		</cms:pageSlot>
		
		<cms:pageSlot position="SiteLogo" var="logo">
			<cms:component component="${logo}"/>
		</cms:pageSlot>
		
		<div class="col-sm-4 text-right">
			<div class="headmenu">
				<!--<a class="cart active" href="index-cart.html"><span>3</span></a>-->
				<cms:pageSlot position="MiniCart" var="cart" limit="1">
					<cms:component component="${cart}" />
				</cms:pageSlot>
				
				<!--<a class="herz active" href="#"><span>12</span></a>-->
				<cms:pageSlot position="wishlist" var="wishlist">
					<cms:component component="${wishlist}" />
				</cms:pageSlot>
				
				<!-- 
				dynamic class missing for image change 
				<a class="user" href="index-accountDash.html"></a>
				-->
				<cms:pageSlot position="myaccount" var="myaccount">
					<cms:component component="${myaccount}" />
				</cms:pageSlot>
			</div>
			<div class="clearfix"></div>
			<div class="headmenu2 text-center hidden-xs">
				<a href="index-cart.html">Warenkorb</a>
				<a href="#">Wunschliste</a>
				<sec:authorize ifAnyGranted="ROLE_ANONYMOUS"><a href="index-accountDash.html">Login</a></sec:authorize>
				<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
					<c:set var="maxNumberChars" value="5"/>
					<c:if test="${fn:length(user.firstName) gt maxNumberChars}">
						<c:set target="${user}" property="firstName" value="${fn:substring(user.firstName, 0, maxNumberChars)}..."/>
					</c:if>
					<a><ycommerce:testId code="header_LoggedUser"><spring:theme code="header.welcome" arguments="${user.firstName},${user.lastName}" htmlEscape="true"/></ycommerce:testId></a>
				</sec:authorize>
			</div>
			<a href="http://www.babyartikel.de" class="home150312 visible-xs"></a>
		</div>
	</div>
</div>

<div class="container head141217">
    <nav role="navigation" class="navbar navbar-default">
		<!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
			<form role="search" class="navbar-xs navbar-form visible-xs">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Suche&hellip;">
					<span class="input-group-btn">
						<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
					</span>
				</div>
            </form>
        </div>
        <!-- Collection of nav links, forms, and other content for toggling -->
        <div id="navbarCollapse" class="collapse navbar-collapse">
			<!--
            <ul class="nav navbar-nav">
                <li><a href="index-category.html">Unterwegs</a></li>
                <li><a href="index-category.html">F&uuml;ttern &amp; Pflege</a></li>
                <li><a href="index-category.html">Wohnen &amp; Schlafen</a></li>
                <li><a href="index-category.html">Kleidung</a></li>
				<li><a href="index-category.html">Spielen</a></li>
            </ul>
			-->
			<cms:pageSlot position="MainMenu" var="component">
				<cms:component component="${component}" />
			</cms:pageSlot>
			
			<!--
            <form role="search" class="navbar-form navbar-right hidden-xs">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Suche&hellip;">
					<span class="input-group-btn">
						<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
					</span>
				</div>
            </form>
			-->
			<cms:pageSlot position="SearchBox" var="component">
				<cms:component component="${component}" />
			</cms:pageSlot>
        </div>
    </nav>
</div>