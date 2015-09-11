<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="product" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<c:set var="kidsClass" value="${themeName == 'kids' ? ' show5' : ''}"/>
<c:set var="kidsAndPucClassSize" value="${(themeName == 'kids' || themeName == 'puc') ? ' product-slider-atributos' : ''}"/>

<div class="tamanhos ${kidsAndPucClassSize}">
	<h3><spring:theme code="product.size"/></h3>
		
		<c:set var="className" value="${'show'}"/>
		
		<ul id="${product.code}" class="${className} ${kidsClass} ${themeName == 'hering' ? ' bxslider-tamanhos' : ''}">
			<li id="${product.code}" class="InStock-1 active">
				${product.size}
			</li>
		</ul>
		
</div>