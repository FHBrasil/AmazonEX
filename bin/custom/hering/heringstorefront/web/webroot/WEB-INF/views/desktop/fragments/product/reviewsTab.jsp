<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<c:if test="${not empty reviews}">
    <section class="comment-list">
        <c:forEach items="${reviews}" var="review" varStatus="status">
            <article class="comment">
                <!--<img src="http://placehold.it/80x80">-->
                <div class="content" style="margin-left: 0px !important;">
                    <header>
                        <h1>
                            <c:choose>
                                <c:when test="${review.principal.name != 'anonymous'}">${review.principal.name}</c:when>
                                <c:otherwise>
                                    <spring:theme code="review.submitted.anonymous" />
                                </c:otherwise>
                            </c:choose>
                        </h1>
                        em <span><fmt:formatDate value="${review.date}" pattern="dd/MM/yyyy" /></span>
                        <product:productStars rating="${review.rating}" />
                    </header>
                    <div>${review.comment}</div>
                </div>
            </article>
        </c:forEach>
    </section>
</c:if>
<div class="center">
    <c:set var="reviewButtonCaption"
        value="${not empty reviews ? 'review.write.title' : 'review.no.reviews'}" />
    <a href="#modal-avaliacao" class="fancybox modal-avaliacao btn btn-big btn-blue"><spring:theme
            code="${reviewButtonCaption}" /></a>
</div>
