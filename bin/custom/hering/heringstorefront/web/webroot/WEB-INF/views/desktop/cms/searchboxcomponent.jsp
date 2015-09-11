<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<c:url value="/search/" var="searchUrl" />
<c:url value="/search/autocomplete/${component.uid}" var="autocompleteUrl" />

<form name="search_form" method="get" action="${searchUrl}" class="navbar-form navbar-right hidden-xs">
    <div class="input-search-wrap input-group">
        <c:set var="hello" value=" ${user.firstName=='Anonymous'?' ':user.firstName}" />
        <spring:theme code="search.placeholder" var="searchPlaceholder" arguments="${hello}" />
        <ycommerce:testId code="header_search_input">
            <input id="palavra-mini-header" class="form-control searchInput" type="search"
                name="text" value="" maxlength="100"
                data-placefocus="<spring:theme code="text.fliegercommerce.texto13"/>"
                placeholder="<spring:theme code="text.fliegercommerce.texto13"/>"
                data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}' />
        </ycommerce:testId>
        <ycommerce:testId code="header_search_button">
            <span class="input-group-btn">
                <button class="btn btn-default" type="submit">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </span>
        </ycommerce:testId>
        <div class="search-autocomplete-results show-on-desktop"></div>
    </div>
</form>
