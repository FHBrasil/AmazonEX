<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="json" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/desktop/grid" %>


{
	"productListerHtml":  "<spring:escapeBody javaScriptEscape="true">
								<c:set var="i" value="1"/>
								<c:forEach items="${productSelected}" var="product">
									<li style="float: left; list-style: outside none none; position: relative; width: 24px; margin-right: 2px;" id="${product.code}" class="InStock-1" onclick="getSizeProductPerColor($(this))">
											<a class="${i == 1 ? 'active' : '' } ">${product.size}</a>
									</li>
									<c:set var="i" value="${i + 1}"/>
								</c:forEach>
						  </spring:escapeBody>",
	 "firstProduct": "${firstProduct}"
}