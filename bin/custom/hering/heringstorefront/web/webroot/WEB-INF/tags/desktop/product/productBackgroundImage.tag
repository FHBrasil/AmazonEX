<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="galleryImages" required="true" type="java.util.List"%>
<c:set var="productMainImagesUrl" value="${galleryImages[0].zoom.url}" />
<c:set var="productZoomImagesUrl" value="${galleryImages[0].superZoom.url}" />
		

			<!-- Carousel indicators -->
			<ol class="carousel-indicators visible-xs visible-sm">
				<li data-target="#mainCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#mainCarousel" data-slide-to="1"></li>
				<li data-target="#mainCarousel" data-slide-to="2"></li>
				<li data-target="#mainCarousel" data-slide-to="3"></li>
				<li data-target="#mainCarousel" data-slide-to="4"></li>
				<li data-target="#mainCarousel" data-slide-to="5"></li>
			</ol>
			<div class="carousel-inner">
				<div class="active item">
					<img src="${productMainImagesUrl}" galleryImages="${galleryImages}" width="500" />
				</div>
			</div>
			<!-- Carousel nav -->
			<a class="carousel-control left" href="#mainCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</a>
			<a class="carousel-control right" href="#mainCarousel" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span>
			</a>

	<%--<div id="product-main-img" data-image-source="${productMainImagesUrl}" data-zoom-image-source="${productZoomImagesUrl}" style="background-position-y: 25px"></div>--%>
	