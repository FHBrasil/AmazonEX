<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="galleryImages" required="true" type="java.util.List"%>
<c:set var="productMainImagesUrl" value="${galleryImages[0].zoom.url}" />
<c:set var="productZoomImagesUrl" value="${galleryImages[0].superZoom.url}" />

<c:if test="${not empty galleryImages}">
	<ol class="carousel-indicators visible-xs visible-sm">
		<c:forEach items="${galleryImages}" var="image" varStatus="status">
			<li data-target="#mainCarousel" data-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}"></li>
		</c:forEach>
	</ol>
	<div class="carousel-inner">
		<c:forEach items="${galleryImages}" var="image" varStatus="status">		
			<div class="item ${status.index == 0 ? 'active' : ''}">
				<img src="${image.zoom.url}" width="500" />
			</div>
		</c:forEach>
	</div>			
	<%-- Carousel nav --%>
	<a class="carousel-control left" href="#mainCarousel" data-slide="prev">
		<span class="glyphicon glyphicon-chevron-left"></span>
	</a>
	<a class="carousel-control right" href="#mainCarousel" data-slide="next">
		<span class="glyphicon glyphicon-chevron-right"></span>
	</a>
</c:if>
	