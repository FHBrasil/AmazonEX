<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="bases" required="true" type="java.lang.String" %>
<%@ attribute name="basesChecked" required="false" type="java.lang.String" %>
<%@ attribute name="id" required="false" type="java.lang.String" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<input type="hidden" name="baseStore" id="baseStore" value="${basesChecked}" />
<spring:theme code="profile.newsletter" text="Receber Newsletter"/>:
<c:forTokens var="base" items="${bases}" delims=",">
	<formElement:formCheckbox idKey="${base}" labelKey="profile.subscribeNewsletter.${base}" path="subscribeNewsletter" labelCSS="text" mandatory="false"/>
</c:forTokens>