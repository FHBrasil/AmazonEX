<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<c:if test="${component.visible}">
<div class="${cssClass}">
    <c:forEach var="component" items="${component.components}">
        <cms:component component="${component}" evaluateRestriction="true"/>
    </c:forEach>
</div>
</c:if>