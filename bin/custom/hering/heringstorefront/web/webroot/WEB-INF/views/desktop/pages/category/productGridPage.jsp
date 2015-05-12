<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="showcase" tagdir="/WEB-INF/tags/desktop/showcase" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<%-- DZARM STORE HEADER --%>
<c:if test="${themeName == 'black'}">

	<template:page pageTitle="${pageTitle}">
		<cms:pageSlot position="Section1" var="feature">
			<cms:component component="${feature}" element="div" class="span-24 section1 cms_disp-img_slot"/>
		</cms:pageSlot>
		<div id="breadcrumb" class="breadcrumb">
			<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
		</div>
		<div id="globalMessages">
			<common:globalMessages/>
		</div>
		
		<div class="categoryData">
			<input type="hidden" class="category" value="${categoryName}" />
	        <input type="hidden" class="categoryGender" value="${categoryGender}" />
		</div>
		
		<div class="span-24">
		<div class="span-6 facetNavigation" style=" margin: 10px 0 0 0;">
			
	
				<nav:facetNavAppliedFilters pageData="${searchPageData}"/>
				<nav:facetNavRefinements pageData="${searchPageData}"/>
	
				<cms:pageSlot position="Section4" var="feature">
					<cms:component component="${feature}" element="div" class="span-4 section4 cms_disp-img_slot"/>
				</cms:pageSlot>
	            
			<!-- Mais vendidos -->
			<div class="chaordic mostpopular" data-skin="vertical"></div>
	        
	        
		</div>
			
					
			
		
			<div class="span-18 last">
			
				<cms:pageSlot position="Section2" var="feature">
					<cms:component component="${feature}" element="div" class="section2 cms_disp-img_slot"/>
				</cms:pageSlot>
			
				
				<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}" numberPagesShown="${numberPagesShown}"/>
	
				<cms:pageSlot position="Section3" var="feature" element="div" class="span-20 last">
					<cms:component component="${feature}" element="div" class="span-5 section3 small_detail"/>
				</cms:pageSlot>
	            
		<!-- Lan�amentos -->
		<div class="chaordic new4you"></div>
	
				<div class="productGrid" id="productGrid">
					<div id="resultsList">
						<c:forEach items="${searchPageData.results}" var="product" varStatus="status">
							<div class="span-6 productDetailsHolder ${(status.index+1)%3 == 0 ? ' last' : ''}${(status.index)%3 == 0 ? ' first clear' : ''}">
								<input type="hidden" name="productCode" value="${product.code}">
								<product:productListerGridItem product="${product}" />
							</div>
						</c:forEach>
					</div>
				</div>
				<c:if test="${true}">
					<common:infiniteScroll/>
				</c:if>
				<nav:pagination top="false"  supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}"  searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>
				
				<c:url value="${requestScope['javax.servlet.forward.servlet_path']}" var="checkoutUrl"/>
	  			<div id="currentPath" data-current-path="${checkoutUrl }"></div>
			</div>
		</div>
	    
		
	<script type="text/javascript" src="https://nxtck.com/act.php?tag=40831&cid=${categoryCode}"></script>
	</template:page>


</c:if>

<%-- HERING STORE HEADER --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<div class="container">	
		<div chaordic="top"></div> 
		<cms:pageSlot position="Section1" var="feature">
		<cms:component component="${feature}" element="div" class="span-24 section1 cms_disp-img_slot"/>
		</cms:pageSlot>
		<div class="filters borders">
		  <c:choose> 
			  <c:when test="${pageType == 'CATEGORY'}">
					<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>			 
			  </c:when>
			  <c:otherwise>
			  		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
			  </c:otherwise>			  
	     </c:choose>   	
				<nav:paginationTop top="true" isShowInfo="${isShowInfo}" isShowListHeringEnabled="${isShowListHeringEnabled}"  supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}"  searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>
		</div>
		
		<div class="categoryData">
			<input type="hidden" class="category" value="${categoryName}" />
	        <input type="hidden" class="categoryGender" value="${categoryGender}" />
		</div>
		<section class="page with-sidebar">
			<div class="left categories">		
					<nav:facetNavAppliedFilters pageData="${searchPageData}"/>
					<nav:facetNavRefinements pageData="${searchPageData}"/>
					<cms:pageSlot position="Section4" var="feature">
						<cms:component component="${feature}" element="div" class="section2 cms_disp-img_slot"/>
					</cms:pageSlot>
			</div>
			<div class="right">
				<div class="disp-banner">
                    <cms:pageSlot position="Section2" var="feature">
                        <cms:component component="${feature}" element="div" class="section2 cms_disp-img_slot"/>
                    </cms:pageSlot>
                </div>
				
				<div chaordic="middle"></div>
			
				<cms:pageSlot position="Section3" var="feature" element="div" class="span-20 last">
					<cms:component component="${feature}" element="div" class="span-5 section3 small_detail"/>
				</cms:pageSlot>
				<div class="infinite-product-scroll showcase" id="results">
					<div class="product-wrapper infinite-scroll-content">
						<c:set var="classNameDisplay" value="${isShowInfo ? 'product-info' : 'resumed-info'}"/>
				   		<showcase:productsToShow 
				   			className="${classNameDisplay}" 
				   			products="${searchPageData.results}" />												
					</div>				
					<c:if test="${false}">
						<common:infiniteScroll/>
					</c:if>
				</div>
		
				<nav:pagination top="false"  supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}"  searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>
				
				<c:url value="${requestScope['javax.servlet.forward.servlet_path']}" var="checkoutUrl"/>
	  			<div id="currentPath" data-current-path="${checkoutUrl }"></div>
			</div>
			
			<div chaordic="bottom"></div>
		</section>
	</div>			
</template:page>
</c:if>