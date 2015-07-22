<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="/cms2lib/cmstags/cmstags.tld" %>
<%@ taglib prefix="ycommerce" uri="/WEB-INF/tld/ycommercetags.tld" %>
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

<div id="header">
	<div class="siteLogo">
		<cms:slot var="logo" contentSlot="${slots.SiteLogo}">
			<cms:component component="${logo}"/>
		</cms:slot>
	</div>
	<span id="Branding"></span>
	
	<cms:slot var="cart" contentSlot="${slots.MiniCart}">
		<cms:component component="${cart}"/>
	</cms:slot>
	
	<div class="headerContent">
		<ul class="nav">
			<c:if test="${uiExperienceOverride}">
				<li class="backToMobileLink">
					<c:url value="/_s/ui-experience?level=" var="backToMobileStoreUrl"/>
					<a href="${backToMobileStoreUrl}"><spring:theme code="text.backToMobileStore"/></a>
				</li>
			</c:if>
			<sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
				<li class="logged_in"><ycommerce:testId code="header_LoggedUser"><spring:theme code="header.welcome" arguments="${user.firstName},${user.lastName}" htmlEscape="true"/></ycommerce:testId></li>
			</sec:authorize>
			<sec:authorize ifNotGranted="ROLE_CUSTOMERGROUP">
				<li><ycommerce:testId code="header_Login_link"><a href="<c:url value="/login"/>"><spring:theme code="header.link.login"/></a></ycommerce:testId></li>
			</sec:authorize>
			<sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
				<li><ycommerce:testId code="header_myAccount"><a href="<c:url value="/my-account"/>"><spring:theme code="header.link.account"/></a></ycommerce:testId></li>
			</sec:authorize>
			<cms:slot var="link" contentSlot="${slots.HeaderLinks}">
				<li><cms:component component="${link}"/></li>
			</cms:slot>
			<li><a href="<c:url value="/store-finder"/>"><spring:theme code="general.find.a.store" /></a></li>
			<sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP"><li><ycommerce:testId code="header_signOut"><a href="<c:url value='/logout'/>"><spring:theme code="header.link.logout"/></a></ycommerce:testId></li></sec:authorize>
		</ul>
		<ul class="language">
			<li><header:languageSelector languages="${languages}" currentLanguage="${currentLanguage}" /></li>
			<li><header:currencySelector currencies="${currencies}" currentCurrency="${currentCurrency}" /></li>
		</ul>
		<div class="search">
			<form name="search_form" method="get" action="<c:url value="/search"/>">
				<spring:theme code="text.search" var="searchText"/>
				<label class="skip" for="search">${searchText}</label>
				<spring:theme code="search.placeholder" var="searchPlaceholder"/>
				<ycommerce:testId code="header_search_input">
					<input id="search" class="text" type="text" name="text" value="" maxlength="100" placeholder="${searchPlaceholder}"/>
				</ycommerce:testId>
				<ycommerce:testId code="header_search_button">
					<spring:theme code="img.searchButton" text="/" var="searchButtonPath"/>
					<input class="button" type="image" value="${searchText}" src="<c:url value="${searchButtonPath}"/>" alt="${searchText}"/>
				</ycommerce:testId>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>

<script>
	<c:if test="${request.secure}">
		<c:url value="/search/autocompleteSecure"  var="autocompleteUrl"/>
	</c:if>
	<c:if test="${not request.secure}">
		<c:url value="/search/autocomplete"  var="autocompleteUrl"/>
	</c:if>
	
	$(function() {
		$( "#search" ).autocomplete({
			source: function( request, response ) {
				$.getJSON(
						"${autocompleteUrl}", 
						{
							term : $('#search').val()
						},
						function(data) {
							response(data);
						}
					);
			},
			minLength: 2,
			open: function(event, ui) { $(".ui-menu").css("z-index", 10000); },
			close: function(event, ui) { $(".ui-menu").css("z-index", -1); },
			select: function(event, ui) { 
				if(ui.item) {
		            $('#search').val(ui.item.value.trim());
		            
		        }
		        document.forms['search_form'].submit();
			},
			autoFocus: false

		});
	});
</script>
