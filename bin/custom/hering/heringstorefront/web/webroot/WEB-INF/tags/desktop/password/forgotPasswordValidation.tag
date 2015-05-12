<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${themeName == 'black'}">
	<div class="alert positive" id="validEmail" tabindex="0">
		<spring:theme code="account.confirmation.forgotten.password.link.sent" arguments="${email}"/>
	</div>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<div style="font-family: 'PT Sans', sans-serif; text-align: center; color: #3E9C00; 
		border-color: #3E9C00; padding: 10px; border: 1px solid;">
		<spring:theme code="account.confirmation.forgotten.password.link.sent" arguments="${email}"/>
	</div>
</c:if>