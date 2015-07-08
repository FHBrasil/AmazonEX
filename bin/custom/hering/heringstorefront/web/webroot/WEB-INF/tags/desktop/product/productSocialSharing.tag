<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

    <c:set var="productUrl" value="${pageContext.request.serverName}${product.url}" />
    <c:set var="productZoomImagesUrl" value="${galleryImages[0].zoom.url}" />
<style type="text/css">
.fb_iframe_widget {overflow: hidden !important;}
</style>
    <p class="h4 text-left">
        <spring:theme code="product.share" />:
    </p>
   
<div class="social150126 text-left">
<c:set var="productUrl" value="${pageContext.request.serverName}${product.url}" />
    <c:set var="productZoomImagesUrl" value="${galleryImages[0].zoom.url}" />

    <a href="//www.facebook.com/sharer/sharer.php?u=${productUrl}&title=${pageTitle}">
        <span class="babicon babicon-facebook"> </span>
    </a>

    <a href="//plus.google.com/share?url=${productUrl}">
        <span class="babicon babicon-google"> </span>
    </a>

    <a href="//twitter.com/intent/tweet?status=${productUrl}">
        <span class="babicon babicon-twitter"> </span>
    </a>
    
    <a href="//www.pinterest.com/pin/create/link/?url=${productUrl}&media=${productZoomImagesUrl}&description=Pinterest" data-pin-config="none">
        <span class="babicon babicon-pinterest"> </span>
    </a>
    <script type="text/javascript" async src="//assets.pinterest.com/js/pinit.js"></script>
</div>

<%-- code original 


<div class="compartilhar">
    <c:set var="productUrl" value="${pageContext.request.serverName}${product.url}" />
    <c:set var="productZoomImagesUrl" value="${galleryImages[0].zoom.url}" />
    <h3>
        <spring:theme code="product.share" />
        :
    </h3>
    <style type="text/css">
.fb_iframe_widget {
    overflow: hidden !important;
}
</style>
<div class="fb-like pin-div" data-href="${productUrl}" data-layout="button"
        data-action="like" data-show-faces="false" data-share="true" data-width="100"></div>
    <!-- Pinterest -->
    <a
        href="//www.pinterest.com/pin/create/button/?url=${productUrl}&media=${productZoomImagesUrl}&description=Pinterest"
        data-pin-do="buttonPin" data-pin-config="none"> <img
        src="//assets.pinterest.com/images/pidgets/pinit_fg_en_rect_gray_20.png" /> <i
        class="fa fa-fw fa-pinterest"></i>
    </a>
    <script type="text/javascript" async src="//assets.pinterest.com/js/pinit.js"></script>
    <!-- /Pinterest -->
</div>

--%>