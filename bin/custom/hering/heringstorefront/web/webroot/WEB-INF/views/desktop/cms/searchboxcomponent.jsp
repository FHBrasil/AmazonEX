<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url value="/search/" var="searchUrl"/>
<c:url value="/search/autocomplete/${component.uid}" var="autocompleteUrl"/>

<%-- SEARCH BOX FORM DZARM --%>
<c:if test="${themeName == 'black'}">
	<div class="siteSearch">
		<form name="search_form" method="get" action="${searchUrl}">
			<div class="control-group left">
				<spring:theme code="text.search" var="searchText"/>
				<label class="control-label skip" for="search">${searchText}</label>
				<div class="controls">
					<c:set var="hello" value="${user.firstName=='anonymous' || user.firstName=='Anonymous'?' ':user.firstName}"/>
					<spring:theme code="search.placeholder" var="searchPlaceholder" arguments="${hello}"/>
					<ycommerce:testId code="header_search_input">
						<input id="search" class="siteSearchInput left" type="text" name="text" value="" maxlength="100" placeholder="${searchPlaceholder}" data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'/>
					</ycommerce:testId>
					<ycommerce:testId code="header_search_button">
						<button class="siteSearchSubmit" type="submit"></button>
					</ycommerce:testId>
				</div>
			</div>
		</form>
	</div>
</c:if>

<%-- SEARCH BOX HERING --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<div class="siteSearch">
		<form name="search_form" method="get" action="${searchUrl}">
			<div class="input-search-wrap" style="width: 100%; height: 33px; margin-top: 0px; margin-right: 0px; margin-bottom: 0px;">
				<c:set var="hello" value=" ${user.firstName=='Anonymous'?' ':user.firstName}"/>
				<spring:theme code="search.placeholder" var="searchPlaceholder" arguments="${hello}"/>
		
				<ycommerce:testId code="header_search_input">
					<input id="palavra-mini-header" class="form-control searchInput" type="search" name="text" value="" 
						maxlength="100" data-placefocus="Ol&aacute;, o que est&aacute; procurando hoje?" placeholder="Ol&aacute;, o que est&aacute; procurando hoje?" 
						data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'/>
				</ycommerce:testId>
			
				<ycommerce:testId code="header_search_button">
					<button class="siteSearchSubmit" type="submit"><i class="fa fa-search"></i></button>
				</ycommerce:testId>
				<div class="search-autocomplete-results show-on-desktop"></div>
			</div>
		</form>
	</div>
</c:if>	