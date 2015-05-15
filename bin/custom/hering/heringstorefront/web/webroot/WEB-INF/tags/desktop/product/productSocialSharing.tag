<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="compartilhar">
    <c:url var="productUrl" value="${product.url}" />
    <c:set var="productUrl" value="${pageContext.request.serverName}${productUrl}" />
    <c:set var="productZoomImagesUrl" value="${galleryImages[0].zoom.url}" />
    <c:set var="hostProductUrl" value="${pageContext.request.serverName}/store/pt${product.url}" />
    <h3>
        <spring:theme code="product.share" />
        :
    </h3>
    <style>
.fb_iframe_widget {
	overflow: hidden !important;
}
</style>
    <div class="fb-like pin-div" data-href="${hostProductUrl}" data-layout="button"
        data-action="like" data-show-faces="false" data-share="true" data-width="100"></div>
    <!-- Pinterest -->
    <a
        href="//www.pinterest.com/pin/create/button/?url=${hostProductUrl}&media=${productZoomImagesUrl}&description=Pinterest"
        data-pin-do="buttonPin" data-pin-config="none"> <img
        src="//assets.pinterest.com/images/pidgets/pinit_fg_en_rect_gray_20.png" /> <i
        class="fa fa-fw fa-pinterest"></i>
    </a>
    <script type="text/javascript" async src="//assets.pinterest.com/js/pinit.js"></script>
    <!-- /Pinterest -->
</div>
