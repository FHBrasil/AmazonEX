<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:choose>
	<c:when test="${showGallery and galleryPosition eq 'left'}">
	<div class="span-4 gallery_left">
		<product:productImageCarousel galleryImages="${galleryImages}" />	
	</div>
	<div class="span-${showGallery ? '8' : '11'} gallery_left"><product:productImagePanel product="${product}" /></div>
	</c:when>
	<c:when test="${showGallery and galleryPosition eq 'right'}">
		<div class="span-${showGallery ? '8' : '11'} gallery_right"><product:productImagePanel product="${product}" /></div>
		<div class="span-4 gallery_right">
			<product:productImageCarousel galleryImages="${galleryImages}" />	
		</div>	
	</c:when>
	<c:otherwise>
		<div class="span-11 no_gallery"><product:productImagePanel product="${product}" /></div>
	</c:otherwise>
</c:choose> 
