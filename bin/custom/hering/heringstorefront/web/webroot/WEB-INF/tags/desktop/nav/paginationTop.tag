<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="searchUrl" required="true"%>
<%@ attribute name="searchPageData" required="true"
	type="de.hybris.platform.commerceservices.search.pagedata.SearchPageData"%>
<%@ attribute name="top" required="true" type="java.lang.Boolean"%>
<%@ attribute name="supportShowAll" required="true"
	type="java.lang.Boolean"%>
<%@ attribute name="supportShowPaged" required="true"
	type="java.lang.Boolean"%>
<%@ attribute name="msgKey" required="false"%>
<%@ attribute name="numberPagesShown" required="false"
	type="java.lang.Integer"%>
<%@ attribute name="isShowInfo" required="true" 
	type="java.lang.Boolean"%>	
<%@ attribute name="isShowListHeringEnabled" required="false"
	type="java.lang.Boolean"%>	

<%@ taglib prefix="pagination"
	tagdir="/WEB-INF/tags/desktop/nav/pagination"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<%-- HERING STORE HEADER --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">	
<c:set var="themeMsgKey"
	value="${not empty msgKey ? msgKey : 'search.page'}" />
	
<c:if test="${searchPageData.pagination.totalNumberOfResults == 0 && top }">
	<div class="paginationBar top clearfix">		
		<ycommerce:testId code="searchResults_productsFound_label">
			<div class="totalResults">
				<spring:theme code="${themeMsgKey}.totalResults"
					arguments="${searchPageData.pagination.totalNumberOfResults}" />
			</div>
		</ycommerce:testId>
	</div>
</c:if>
<c:if test="${searchPageData.pagination.totalNumberOfResults > 0}">
	
	
		<c:catch>
			<spring:eval expression="searchPageData.currentQuery.query" var="dummyVar" />
		</c:catch>
		 				
		<c:catch>
				<spring:eval expression="searchPageData.currentQuery.query" var="dummyVar" />
				<div class="view-info">
						<form action="#" method="GET">
						<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}">
						<input type="hidden" name="isShowListHeringEnabled" id="isShowListHeringEnabled">
						<input type="hidden" name="isShowInfo" id="isShowInfo">
				
				<c:if test="${not isShowListHeringEnabled}">
		
						 <c:choose>
						<c:when test="${isShowInfo}">
							<label class="show-on-desktop"><spring:theme code="${themeMsgKey}.ocultarInfo" /></label>
							<a href="#" onclick="$(this).closest('form').find('input[name=isShowInfo]').val('false');$(this).closest('form').submit()">
						    	<span class="hide-on-desktop"></span> <i class="fa fa-eye" style="opacity: 0.65;"></i>
							</a>	
						</c:when>
						<c:otherwise>	
							<label class="show-on-desktop"><spring:theme code="${themeMsgKey}.verInfo" /></label>
							<a href="#" onclick="$(this).closest('form').find('input[name=isShowInfo]').val('true');$(this).closest('form').submit()">
								<span class="hide-on-desktop"></span> <i class="fa fa-eye selected"></i>
							</a>
					 	</c:otherwise>
					</c:choose> 
					
				</c:if>				
							<c:catch>
								<spring:eval expression="searchPageData.currentQuery.query" var="dummyVar" />
								<%-- <div class="list-grid">
									<form action="#" method="GET">
										<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}">
										<input type="hidden" name="isShowListHeringEnabled" id="isShowListHeringEnabled">
										<a class="grid-on" href="#" onclick="$(this).closest('form').find('input[name=isShowListHeringEnabled]').val('false');$(this).closest('form').submit()">
											<spring:theme code="${themeMsgKey}.showGrid" />
										</a>
										<a class="list-on" href="#" onclick="$(this).closest('form').find('input[name=isShowListHeringEnabled]').val('true');$(this).closest('form').submit()">
											<spring:theme code="${themeMsgKey}.showList" />
										</a>
									</form>
								</div> --%>
							</c:catch>
						</form>
					</div>
					
				
				</c:catch>
			 	<div class="diagramming">
					<form action="#" method="GET">
						<label><spring:theme code="${themeMsgKey}.diagramacao"/></label>
						<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}">
						<input type="hidden" name="isShowListHeringEnabled" id="isShowListHeringEnabled">
						<c:choose>
							<c:when test="${isShowListHeringEnabled || isShowListEnabled}">							   
								<a class="fa fa-th-large" style="cursor: pointer;" onclick="$(this).closest('form').find('input[name=isShowListHeringEnabled]').val('false');$(this).closest('form').submit()"></a>
								<a class="fa fa-list selected" ></a>
							</c:when>
							<c:otherwise>								
								<a class="fa fa-th-large selected" ></a>
								<a class="fa fa-list" style="cursor: pointer;"  onclick="$(this).closest('form').find('input[name=isShowListHeringEnabled]').val('true');$(this).closest('form').submit()"></a>
							</c:otherwise>
						</c:choose>
					</form>
				</div> 
	<ycommerce:testId code="searchResults_productsFound_label">			
 		<div class="search-result">
 			<spring:theme code="${themeMsgKey}.totalResults"
							  arguments="${searchPageData.pagination.totalNumberOfResults}" />
		</div>
	</ycommerce:testId>
	
	<div class="relevance">
		<c:if test="${not empty searchPageData.sorts}">
			<form id="sort_form${top ? '1' : '2'}"
				name="sort_form${top ? '1' : '2'}" method="get" action="#">
				<input type="hidden" name="isShowInfo" id="isShowInfo">
					 <select
						id="sortOptions${top ? '1' : '2'}" name="sort" >
						<div class="relevance">
						<c:forEach items="${searchPageData.sorts}" var="sort">
							<option value="${sort.code}"
								${sort.selected ? 'selected="selected"' : ''}>
								<c:choose>
									<c:when test="${not empty sort.name}">
									${sort.name}
								</c:when>
									<c:otherwise>
										<spring:theme code="${themeMsgKey}.sort.${sort.code}" />
									</c:otherwise>
								</c:choose>
							</option>
						</c:forEach>
						</div>
					</select>
				
				<c:catch var="errorException">
					<spring:eval expression="searchPageData.currentQuery.query" var="dummyVar" />
					<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}" />
				</c:catch>
				<c:if test="${supportShowAll}">
					<ycommerce:testId code="searchResults_showAll_link">
						<input type="hidden" name="show" value="Page" />
					</ycommerce:testId>
				</c:if>
				<c:if test="${supportShowPaged}">
					<ycommerce:testId code="searchResults_showPage_link">
						<input type="hidden" name="show" value="All" />
					</ycommerce:testId>
				</c:if>
			</form>
		</c:if>
	</div>	
</c:if>
</c:if>