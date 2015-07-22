<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://granule.com/tags" prefix="g" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${cssCompression}"><g:compress>
		<%@ include file="compressables/css.tag" %>
	</g:compress></c:when>
	<c:otherwise>
		<%@ include file="compressables/css.tag" %>
	</c:otherwise>
</c:choose>
