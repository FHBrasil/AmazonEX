<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="galleryImages" required="true" type="java.util.List"%>
<c:set var="productMainImagesUrl" value="${galleryImages[0].zoom.url}" />
<c:set var="productZoomImagesUrl" value="${galleryImages[0].zoom.url}" />
<div id="product-main-img" data-image-source="${productMainImagesUrl}"
    data-zoom-image-source="${productZoomImagesUrl}" style="background-position-y: 25px"></div>