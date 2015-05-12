<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="sizes" required="true" type="java.util.ArrayList" %>

<c:set var="count" value="0"/>

<div class="tamanhos">
	<h3><spring:theme code="product.size"/></h3>
	<c:forEach items="${sizes}" var="size">
		
		<c:set var="className" value="${count > 0 ? 'hide' : 'show'}"/>
		<c:set var="countSize" value="0"/>
		
		<ul id="${size.colorCode}" class="${className}">

		<c:forEach items="${size.sizeValue}" var="sizeDetail">
			<c:set var="isActive" value="${countSize == 0 ? 'active' : ''}"/>
			<li id="${sizeDetail.productCode}" class="InStock-1 ${isActive}">
				${sizeDetail.sizeValue}
			</li>
			<c:set var="countSize" value="${countSize + 1}"/>
		</c:forEach>

		</ul>
		<c:set var="count" value="${count + 1}"/>
		
	</c:forEach>
</div>