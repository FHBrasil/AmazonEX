<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<c:if test="${statusRegister == 'error'}">
	{ "message" : "<spring:theme code="product.stock-out.register.user.fail"/>" }
</c:if>

<c:if test="${statusRegister == 'registered'}">
	{ "message" : "<spring:theme code="product.stock-out.register.user.sucess"/>" }
</c:if>

<c:if test="${statusRegister == 'existent'}">
	{ "message" : "<spring:theme code="product.stock-out.register.user.existent"/>" }
</c:if>