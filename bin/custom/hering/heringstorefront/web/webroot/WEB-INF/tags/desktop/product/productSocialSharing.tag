<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="productUrl" value="${pageContext.request.serverName}${product.url}" />
<c:set var="productZoomImagesUrl" value="${galleryImages[0].zoom.url}" />


<p class="h4 text-left">
    <spring:theme code="product.share" />:
</p>


<div class="social150126 text-left">
    <a href="http://www.facebook.com/sharer/sharer.php?u=${productUrl}&title=${pageTitle}" target="popup" onclick="fbashare()">
        <span class="babicon babicon-facebook"> </span>
    </a>
    <a href="http://plus.google.com/share?url=${productUrl}" target="popup" onclick="gbashare()">
        <span class="babicon babicon-google"> </span>
    </a>
    <a href="http://twitter.com/intent/tweet?status=${productUrl}" target="popup" onclick="tbashare()">
        <span class="babicon babicon-twitter"> </span>
    </a>
    <a href="http://www.pinterest.com/pin/create/link/?url=${productUrl}&media=${productZoomImagesUrl}&description=Pinterest" target="popup" onclick="pbashare()">
        <span class="babicon babicon-pinterest"> </span>
    </a>
</div>