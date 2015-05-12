<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="galleryImages" required="true" type="java.util.List" %>

<c:if test="${themeName == 'black'}">
	<div id="slider" class="span-14 productImage">
		<div class="productImageGallery">
			<ul class="jcarousel-skin">
				<c:forEach items="${galleryImages}" var="container" varStatus="varStatus">
					<li>	
						<img 	class="cloudzoom-gallery"
								src="${container.thumbnail.url}"
								alt="${container.thumbnail.altText}" 
								title="${container.thumbnail.altText}"
								data-primaryimagesrc="${container.zoom.url}" 
								data-galleryposition="${varStatus.index}"
								data-cloudzoom="useZoom:'#productZoom',image:'${container.zoom.url}',zoomImage:'${container.superZoom.url}'">
		                </li>
				</c:forEach>
			</ul>
		</div>
		<div class="productImagePrimary" id="primary_image">
			<c:if test="${fn:contains(product.url, '?sku=')}">
				<c:url value="${fn:substringBefore(product.url, '?sku=')}/zoomImages" var="productZoomImagesUrl"/>
			</c:if>
			<c:if test="${not fn:contains(product.url, '?sku=')}">
				<c:url value="${product.url}/zoomImages" var="productZoomImagesUrl"/>
			</c:if>
			<a class="productImagePrimaryLink" id="imageLink" href="${productZoomImagesUrl}" data-href="${productZoomImagesUrl}" target="_blank" title="<spring:theme code="general.zoom"/>">
				<product:productZoomImage product="${product}" format="zoom"/>
			</a>
			<ycommerce:testId code="productDetails_zoomImage_button">
				<a class="productImageZoomLink"  id="zoomLink" href="${productZoomImagesUrl}" data-href="${productZoomImagesUrl}"  target="_blank" title="<spring:theme code="general.zoom"/>">	</a>
			</ycommerce:testId>
		</div>
	</div>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<div class="left product-thumbnails">
		<c:if test="${themeName ne 'foryou'}">
			<ul>
				<c:forEach items="${galleryImages}" var="container" varStatus="status">
					<li <c:if test="${status.first}">class="active"</c:if>>
						<a href="#" data-image-source="${container.superZoom.url}" data-zoom-image-source="${container.zoom.url}">
							<img src="${container.thumbnail.url}" alt="${container.thumbnail.altText}"/>
						</a>
					</li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${themeName eq 'foryou'}">
			<product:produtForYouImagens product="${product}" imagens="${galleryImages}"/>
		</c:if>
		
	<div class="product-main-img-mask">
		<i class="lupa fa fa-search"></i>
	</div>
	
	</div>
</c:if>
