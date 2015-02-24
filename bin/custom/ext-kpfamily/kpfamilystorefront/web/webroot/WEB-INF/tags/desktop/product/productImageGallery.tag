<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="galleryImages" required="true" type="java.util.List"%>

<ol class="hidden-xs hidden-sm col-sm-6 carouselNav150219">
    <li class="col-xs-2" data-target="#mainCarousel" data-slide-to="0">
        <img src="product/product-image-t110.jpg" width="100%" alt="Produktbild 1">
    </li>
    <li class="col-xs-2" data-target="#mainCarousel" data-slide-to="1" >
        <img src="product/product-image-d1-t65.jpg" width="100%" alt="Produktbild 2">
    </li>
    <li class="col-xs-2" data-target="#mainCarousel" data-slide-to="2">
        <img src="product/product-image-d2-t65.jpg" width="100%" alt="Produktbild 3">
    </li>
    <li class="col-xs-2" data-target="#mainCarousel" data-slide-to="3">
        <img src="product/product-image-d3-t65.jpg" width="100%" alt="Produktbild 4">
    </li>
    <li class="col-xs-2" data-target="#mainCarousel" data-slide-to="4">
        <img src="product/product-image-d4-t65.jpg" width="100%" alt="Produktbild 5">
    </li>
    <li class="col-xs-2" data-target="#mainCarousel" data-slide-to="5">
        <img src="product/product-image-d5-t65.jpg" width="100%" alt="Produktbild 6">
    </li>
    <li class="col-xs-2" data-target="#mainCarousel" data-slide-to="4">
        <img src="product/product-image-d4-t65.jpg" width="100%" alt="Produktbild 5">
    </li>
    <li class="col-xs-2" data-target="#mainCarousel" data-slide-to="5">
        <img src="product/product-image-d5-t65.jpg" width="100%" alt="Produktbild 6">
    </li>
</ol>