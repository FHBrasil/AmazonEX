<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<%-- DZARM STORE HEADER --%>
<c:if test="${themeName == 'black'}">
	<c:if test="${not empty pageData.breadcrumbs}">
	
		<div class="headline"><spring:theme code="search.nav.appliedFilters"/>	</div>
		<div class="facet">
		<div class="facetValues">
			<ul class="facet_block">
				<c:forEach items="${pageData.breadcrumbs}" var="breadcrumb" varStatus="status">
					<li class="remove_item_left">
						<c:if test="${status.first}">
							<input type="hidden" value="${breadcrumb.facetValueName}" class="chaordicSearchCategory"/>
						</c:if>
						<c:url value="${breadcrumb.removeQuery.url}" var="removeQueryUrl"/>						
						
							<c:if test="${fn:startsWith(breadcrumb.facetValueName, '#')}">
								<div class="remove_item_left_name facet_block-label itemSelecionadoBreadcrumb faceta-${breadcrumb.facetValueName}" style="background-color:${breadcrumb.facetValueName}; width:15px; height:15px;"></div>
							</c:if>
							<c:if test="${ not fn:startsWith(breadcrumb.facetValueName, '#')}">
								<span class="remove_item_left_name itemSelecionadoBreadcrumb faceta-${breadcrumb.facetValueName}">${breadcrumb.facetValueName}</span>
							</c:if>
						
						<span class="remove"><a href="${removeQueryUrl}" ></a></span>
						<div class="clear"></div>
					</li>
				</c:forEach>
			</ul>
		</div>
		</div>
	
	</c:if>
</c:if>

<%-- HERING STORE HEADER --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<c:if test="${not empty pageData.breadcrumbs}">	
			
			
				<c:forEach items="${pageData.breadcrumbs}" var="breadcrumb" varStatus="status">
					
						<c:if test="${status.first}">
							<input type="hidden" value="${breadcrumb.facetValueName}" class="chaordicSearchCategory"/>
						</c:if>
						
						
						<%-- <c:if test="${fn:startsWith(breadcrumb.facetValueName, '#')}">
							<div class="remove_item_left_name facet_block-label itemSelecionadoBreadcrumb faceta-${breadcrumb.facetValueName}" style="background-color:${breadcrumb.facetValueName}; width:15px; height:15px;"></div>
						</c:if> --%>
						<c:choose>
							<c:when test="${themeName == 'foryou'}">
								<c:if test="${ not fn:startsWith(breadcrumb.facetValueName, '#')}">
									<c:if test="${breadcrumb.facetValueName eq 'Activewear' or breadcrumb.facetValueName eq 'Beachwear' or breadcrumb.facetValueName eq 'Underwear' or breadcrumb.facetValueName eq 'Loungewear' or breadcrumb.facetValueName eq 'Sleepwear' or breadcrumb.facetValueName eq 'Promoção'}">
										<header>
											<h1>
												<span class="remove_item_left_name itemSelecionadoBreadcrumb faceta-${breadcrumb.facetValueName}">${breadcrumb.facetValueName}</span>
											</h1>
										</header>	
									</c:if>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if test="${ not fn:startsWith(breadcrumb.facetValueName, '#')}">
									<c:if test="${breadcrumb.facetValueName eq 'Feminino' or breadcrumb.facetValueName eq 'Masculino' or breadcrumb.facetValueName eq 'Jeans' or breadcrumb.facetValueName eq 'Acessórios' or breadcrumb.facetValueName eq 'Sales'}">
										<header>
											<h1>
												<span class="remove_item_left_name itemSelecionadoBreadcrumb faceta-${breadcrumb.facetValueName}">${breadcrumb.facetValueName}</span>
											</h1>
										</header>	
									</c:if>
								</c:if>
							</c:otherwise>
						</c:choose>					
						<div class="clear"></div>
				</c:forEach>
				<div id="product-filters" class="category"><div class="cat-links"><ul style="dsisplay:inline">
				<c:forEach items="${pageData.breadcrumbs}" var="breadcrumb" varStatus="status">
					<c:url value="${breadcrumb.removeQuery.url}" var="removeQueryUrl"/>
					<c:if test="${breadcrumb.facetValueName != 'Activewear' && breadcrumb.facetValueName != 'Beachwear' && breadcrumb.facetValueName != 'Underwear' && breadcrumb.facetValueName != 'Loungewear' && breadcrumb.facetValueName != 'Sleepwear' && breadcrumb.facetValueName != 'Promoção' &&
						breadcrumb.facetValueName != 'Feminino' && breadcrumb.facetValueName != 'Masculino' && breadcrumb.facetValueName != 'Jeans' && breadcrumb.facetValueName != 'Acessórios' && breadcrumb.facetValueName != 'Sales'}">
					<c:choose>
						<c:when test="${fn:startsWith(breadcrumb.facetValueName, '#')}">
							<li><a href="${removeQueryUrl}"><b class="close">x</b>
							 <span>${breadcrumb.facetName}: <div class="remove_item_left_name facet_block-label itemSelecionadoBreadcrumb faceta-${breadcrumb.facetValueName}" style="background-color:${breadcrumb.facetValueName}; width:15px; height:15px;display:inline!important;position: absolute;top: 9px;left: 35px"></div></span></a></li>
							
							
						</c:when>
						<c:otherwise>
						 <li><a href="${removeQueryUrl}"><b class="close">x</b> <span>${breadcrumb.facetName}: ${breadcrumb.facetValueName}</span></a></li>
						</c:otherwise>
					</c:choose>
					</c:if>
				</c:forEach>
				</ul></div></div>
			
	
	
	</c:if>

</c:if>
