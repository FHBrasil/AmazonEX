<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="product" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<div class="cores ${themeName == 'kids' || themeName == 'puc' ? ' product-slider-atributos product-tooltip-preview' : ''}"> 
	<h3><spring:theme code="showcase.colors.title"/></h3>
	<ul id="box-color" class="${themeName == 'kids' || themeName == 'puc' ? 'show5' : ''}">
		<li
			style="background-color:${product.color.RGB}" 
			data-tooltip="${product.color.basicDescription}"
			data-code="${product.code}">
		</li>
	</ul>
</div> 