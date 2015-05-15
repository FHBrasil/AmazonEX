<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="rating" required="true"%>
<%@ attribute name="addClass" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${not empty rating}">
    <c:set var="starWidth" value="16" />
    <c:set var="starCount" value="5" />
    <div class="rating">
        <c:forTokens items="1,2,3,4,5" delims="," var="starNumber">
            <c:choose>
                <c:when test="${rating >= starNumber}">
                    <i class="fa fa-star"></i>
                </c:when>
                <c:otherwise>
                    <i class="fa fa-star-o"></i>
                </c:otherwise>
            </c:choose>
        </c:forTokens>
    </div>
</c:if>
