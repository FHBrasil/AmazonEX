<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="galleryImages" required="true" type="java.util.List"%>

<c:if test="${fn:contains(product.url, '?sku=')}">
    <c:url value="${fn:substringBefore(product.url, '?sku=')}/zoomImages"
        var="productZoomImagesUrl" />
</c:if>
<c:if test="${not fn:contains(product.url, '?sku=')}">
    <c:url value="${product.url}/zoomImages" var="productZoomImagesUrl" />
</c:if>


<div id="mainCarousel" class="carousel slide" data-ride="carousel" data-interval="false">
    <!-- Carousel indicators -->
<!--         <ol class="carousel-indicators visible-xs visible-sm"> -->
<!--             <li data-target="#mainCarousel" data-slide-to="0" class="active"></li> -->
<!--             <li data-target="#mainCarousel" data-slide-to="1"></li> -->
<!--             <li data-target="#mainCarousel" data-slide-to="2"></li> -->
<!--             <li data-target="#mainCarousel" data-slide-to="3"></li> -->
<!--             <li data-target="#mainCarousel" data-slide-to="4"></li> -->
<!--             <li data-target="#mainCarousel" data-slide-to="5"></li> -->
<!--         </ol> -->
    <!-- Carousel items -->
    <div class="carousel-inner">
        <div class="active item">
            <product:productPrimaryImage product="${product}" format="zoom" />
        </div>
    </div>
    <!-- Carousel nav -->
    <a class="carousel-control left" href="#mainCarousel" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
    </a>
    <a class="carousel-control right" href="#mainCarousel" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
    </a>
</div>





    


<!-- <div class="span-14 productImage"> -->
<!-- 	<div class="productImageGallery"> -->
<!-- 		<ul class="jcarousel-skin"> -->
<%-- 			<c:forEach items="${galleryImages}" var="container" varStatus="varStatus"> --%>
<!-- 				<li> -->
<%-- 					<span class="thumb ${(varStatus.index==0)? "active":""}"> --%>
<%-- 						<img src="${container.thumbnail.url}" data-primaryimagesrc="${container.product.url}" data-galleryposition="${varStatus.index}" alt="${container.thumbnail.altText}" title="${container.thumbnail.altText}" />	 --%>
<!-- 					</span> -->
<!-- 				</li> -->
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<!-- 	</div> -->

<!-- 	<div class="productImagePrimary" id="primary_image"> -->
<%-- 		<c:if test="${fn:contains(product.url, '?sku=')}"> --%>
<%-- 			<c:url value="${fn:substringBefore(product.url, '?sku=')}/zoomImages" var="productZoomImagesUrl"/> --%>
<%-- 		</c:if> --%>
<%-- 		<c:if test="${not fn:contains(product.url, '?sku=')}"> --%>
<%-- 			<c:url value="${product.url}/zoomImages" var="productZoomImagesUrl"/> --%>
<%-- 		</c:if> --%>
<%-- 		<a class="productImagePrimaryLink" id="imageLink" href="${productZoomImagesUrl}" data-href="${productZoomImagesUrl}" target="_blank" title="<spring:theme code="general.zoom"/>"> --%>
<%-- 			<product:productPrimaryImage product="${product}" format="zoom"/> --%>
<!-- 		</a> -->
<%-- 		<ycommerce:testId code="productDetails_zoomImage_button"> --%>
<%-- 			<a class="productImageZoomLink"  id="zoomLink" href="${productZoomImagesUrl}" data-href="${productZoomImagesUrl}"  target="_blank" title="<spring:theme code="general.zoom"/>">	</a> --%>
<%-- 		</ycommerce:testId> --%>
<!-- 	</div> -->
<!-- </div> -->
