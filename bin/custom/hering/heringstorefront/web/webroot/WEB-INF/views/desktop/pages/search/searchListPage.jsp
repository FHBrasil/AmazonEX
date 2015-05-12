<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="showcase" tagdir="/WEB-INF/tags/desktop/showcase" %>

<%-- DZARM STORE HEADER --%>
<c:if test="${themeName == 'black'}">
	<template:page pageTitle="${pageTitle}">
		<cms:pageSlot position="Section1" var="feature">
			<cms:component component="${feature}" element="div" 
					class="span-24 section1 cms_disp-img_slot"/>
		</cms:pageSlot>
		<div id="breadcrumb" class="breadcrumb">
			<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
		</div>
		<div id="globalMessages">
			<common:globalMessages/>
		</div>
		<div class="span-6 facetNavigation" style=" margin: 10px 0 0 0;">
			<nav:facetNavAppliedFilters pageData="${searchPageData}"/>
			<nav:facetNavRefinements pageData="${searchPageData}"/>
			<cms:pageSlot position="Section4" var="feature">
				<cms:component component="${feature}" />
			</cms:pageSlot>
		</div>
		<div class="span-18 last">
			<cms:pageSlot position="Section2" var="feature" element="div" >
				<cms:component component="${feature}" />
			</cms:pageSlot>
			<nav:searchSpellingSuggestion
					spellingSuggestion="${searchPageData.spellingSuggestion}" />
			<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" 
					supportShowAll="${isShowAllAllowed}" 
					searchPageData="${searchPageData}" 
					searchUrl="${searchPageData.currentQuery.url}"
					numberPagesShown="${numberPagesShown}"/>
			<c:if test="${!(searchPageData.freeTextSearch == '')}">
				<div class="youSearchedFor">
					 <spring:theme code="search.page.searchText"
					 		arguments="${searchPageData.freeTextSearch}"/>
				</div>
			</c:if>
			<div class="productList-li">
				<div id="resultsList">
					<c:forEach items="${searchPageData.results}" var="product">
						<product:productListerItem product="${product}"/>
					</c:forEach>
				</div>
				<c:if test="${true}">
					<common:infiniteScroll/>
				</c:if>
			</div>
			<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"
					supportShowAll="${isShowAllAllowed}"
					searchPageData="${searchPageData}"
					searchUrl="${searchPageData.currentQuery.url}"
					numberPagesShown="${numberPagesShown}"/>
					
			<c:url value="${requestScope['javax.servlet.forward.servlet_path']}" var="checkoutUrl"/>
	  		<div id="currentPath" data-current-path="${checkoutUrl}"></div>
		</div>
	</template:page>
</c:if>

<%-- HERING STORE HEADER --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">		
	
	<template:page pageTitle="${pageTitle}">	
	
		<div id="globalMessages">
			<common:globalMessages/>
		</div>
		
		<div class="container">
		
			<cms:pageSlot position="Section1" var="feature">
				<cms:component component="${feature}" 
					element="div" 
					class="span-24 section1 cms_disp-img_slot"/>
			</cms:pageSlot>
			
			<header id="page-header">
				<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
				<c:if test="${!(searchPageData.freeTextSearch == '')}">
					<h1>
						<spring:theme code="search.page.searchText" 
							arguments="${searchPageData.freeTextSearch}"/>
					</h1>
					<nav:searchSpellingSuggestion 
						spellingSuggestion="${searchPageData.spellingSuggestion}" />
				</c:if>
			</header>
			<div chaordic="middle"></div>
			<div class="searchData">
				<input type="hidden" class="userCode" value="${searchPageData.freeTextSearch}" />
				<input type="hidden" class="textSearch" value="${searchPageData.freeTextSearch}" />
			</div>
		
			<section class="page with-sidebar" style="border-top: 1px dotted #ddd;">
				
				<div class="left categories" style="margin-top: 7px;">
					<nav:facetNavAppliedFilters pageData="${searchPageData}"/>
					<nav:facetNavRefinements pageData="${searchPageData}"/>
					<cms:pageSlot position="Section4" var="feature">
						<cms:component component="${feature}" 
							element="div" 
							class="span-24 section1 cms_disp-img_slot"/>
					</cms:pageSlot>
				</div>
				
				<div class="right">
			
					<c:if test="${searchPageData.pagination.currentPage == 0}">
						<cms:pageSlot position="Section2" var="feature" >
							<cms:component component="${feature}" />
						</cms:pageSlot>
					</c:if>
					
					<div class="filters borders" style="border-top: 0px!important;">	
						<nav:paginationTop top="true" 
							isShowInfo="${isShowInfo}" 
							isShowListHeringEnabled="${isShowListHeringEnabled}" 
							supportShowPaged="${isShowPageAllowed}" 
							supportShowAll="${isShowAllAllowed}"  
							searchPageData="${searchPageData}" 
							searchUrl="${searchPageData.currentQuery.url}"  
							numberPagesShown="${numberPagesShown}"/>
					</div>
		
					<div class="list-mode infinite-product-scroll showcase" id="results">
						<div class="product-wrapper infinite-scroll-content" id="resultsList">
					   		<showcase:productsToShow 
					   			className="" 
					   			products="${searchPageData.results}" />
						</div>
					</div>					
			
					<nav:pagination top="false" 
						supportShowPaged="${isShowPageAllowed}" 
						supportShowAll="${isShowAllAllowed}"  
						searchPageData="${searchPageData}" 
						searchUrl="${searchPageData.currentQuery.url}" 
						numberPagesShown="${numberPagesShown}"/>
						
					<div chaordic="bottom"></div>
				</div>	
			</section>
		
		</div>
		
		<c:url value="${requestScope['javax.servlet.forward.servlet_path']}" var="checkoutUrl"/>
  		<div id="currentPath" data-current-path="${checkoutUrl }"></div>
	
	</template:page>	
</c:if>
	
