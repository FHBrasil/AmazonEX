<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="colors" type="java.util.ArrayList" %>

<c:set var="count" value="0"/>
<div class="cores"> 
	<h3><spring:theme code="showcase.colors.title"/></h3>
	<ul id="box-color">
	<c:forEach items="${colors}" var="color" >
		<c:set var="className" value="${count > 0 ? '' : 'active'}"/>
		<li class="${className}"
			style="background-color:${color.RGB}" 
			data-tooltip="${color.colorName}"
			data-code="${color.colorCode}">
		</li>
		<c:set var="count" value="${count + 1}"/>
	</c:forEach>
	</ul>
</div> 