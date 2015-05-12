<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<c:if test="${themeName == 'black'}">
<br><br>
<div class="tabHead2" id="tab-reviews">
	<spring:theme code="review.reviews"/>
</div>

<div id="read_reviews">
	<div class="actionBar top clearfix">
		<div class="left">
			<button href="#" id="write_review_action" class="positive" type="submit" >
				<c:choose>
					<c:when test="${not empty reviews}">
						<spring:theme code="review.write.title"/>
					</c:when>
					<c:otherwise>
						<spring:theme code="review.no.reviews"/>
					</c:otherwise>
				</c:choose>
			</button>
		</div>
		<br/><br/><br/>
		<div class="tabHead3">
			<div>
				${fn:length(reviews)}&nbsp;<spring:theme code="review.number.of"/>&nbsp;${reviewsTotal}&nbsp;<spring:theme code="review.number.reviews"/>
				<div class="right">
					<c:if test="${fn:length(reviews) ne reviewsTotal}" >
						<a href="#" id="show_all_reviews_top_action"><spring:theme code="review.show.all"/></a>
					</c:if>
				</div>
			</div>
			
		</div>
	</div>

	<c:if test="${not empty reviews}">
		<div class="rewiewList">
			<c:forEach items="${reviews}" var="review" varStatus="status">
				<div class="reviewDetail">
					<br/>
					<product:productStars rating="${review.rating}"  addClass="left" />
					<br/>
					<div class="autor left">
						<spring:theme code="review.submitted.by"/><c:out value=" "/>
						
						<div class="r-bold" style="display: inline">
							<c:choose>
								<c:when test="${not empty review.alias}">
									${review.alias}
								</c:when>
								<c:otherwise>
									<spring:theme code="review.submitted.anonymous"/>
								</c:otherwise>
							</c:choose>
						</div>
						<br/>	
						<c:set var="reviewDate" value="${review.date}"/>
						(<fmt:formatDate value="${reviewDate}" pattern="dd/MM/yyyy" />)
						
					</div>
					
					<div class="rev-details right">
						<div class="headline r-bold" >${review.headline}</div>
					
						<div class="body" >${review.comment}
							<div class="r-apost2"></div>
						</div>
					</div>
					<br/><br/><br/>
				
				</div>
			</c:forEach>
		</div>
		<br/>
		<div class="actionBar bottom clearfix">
			<div class="tabBotton">
				<c:if test="${fn:length(reviews) ne reviewsTotal}" >
					<a href="#" id="show_all_reviews_bottom_action"><spring:theme code="review.show.all"/></a>
				</c:if>
			</div>
		</div>
	</c:if>
</div>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<c:if test="${not empty reviews}">
	
		<section class="comment-list">
			<c:forEach items="${reviews}" var="review" varStatus="status">
				<article class="comment">
					<!--<img src="http://placehold.it/80x80">-->
					<div class="content" style="margin-left: 0px!important;">
						<header>
							<h1>
								<c:choose>
									<c:when test="${review.principal.name != 'anonymous'}">${review.principal.name}</c:when>
									<c:otherwise><spring:theme code="review.submitted.anonymous"/></c:otherwise>
								</c:choose>
							</h1>
							em
							<span><fmt:formatDate value="${review.date}" pattern="dd/MM/yyyy" /></span>
							<product:productStars rating="${review.rating}"/>
						</header>
						<div>${review.comment}</div>
					</div>
				</article>
			</c:forEach>
		</section>
	</c:if>
	<div class="center">
	<c:set var="reviewButtonCaption" value="${not empty reviews ? 'review.write.title' : 'review.no.reviews'}"/>
	<c:if test="${themeName == 'foryou'}">
		<a href="#modal-avaliacao"
				class="fancybox modal-avaliacao btn btn-big btn-purple"><spring:theme code="${reviewButtonCaption}"/></a>
	</c:if>
	<c:if test="${themeName != 'foryou'}">
		<a href="#modal-avaliacao"
				class="fancybox modal-avaliacao btn btn-big btn-blue"><spring:theme code="${reviewButtonCaption}"/></a>
	</c:if>
	</div>
</c:if>