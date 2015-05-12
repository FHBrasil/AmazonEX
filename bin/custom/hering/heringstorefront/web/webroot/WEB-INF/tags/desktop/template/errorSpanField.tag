<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="path" required="true" rtexprvalue="true"%>
<%@ attribute name="errorPath" required="false" rtexprvalue="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${themeName == 'black'}">
<spring:bind path="${not empty errorPath ? errorPath : path}">
<c:choose>
	<c:when test="${not empty status.errorMessages}">
		<div class="control-group error">
			<jsp:doBody/>
			<div class="help-inline">
				<c:if test="false">
					<form:errors path="${not empty errorPath ? '' : path}"/>
				</c:if>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="control-group">
			<jsp:doBody/>
		</div>
	</c:otherwise>
</c:choose>
</spring:bind>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<spring:bind path="${not empty errorPath ? errorPath : path}">
	<c:choose>
	<c:when test="${not empty status.errorMessages}">
		<jsp:doBody/>
		<div class="help-inline" style="display: none !important;">
			<c:if test="false">
				<form:errors path="${not empty errorPath ? '' : path}"/>
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
		<jsp:doBody/>
	</c:otherwise>
	</c:choose>
	</spring:bind>
</c:if>