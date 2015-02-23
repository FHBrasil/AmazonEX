<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="format" required="true" type="java.lang.String"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<c:set value="${ycommerce:productImage(product, format)}" var="primaryImage" />

<div class="product-image col-xs-12 col-sm-6 v-bottom">
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
                <c:choose>
                    <c:when test="${not empty primaryImage}">
                        <!-- Following items should be replaced with ajax by hi-res images when user starts carousel -->
                        <img src="product/product-image.jpg" title="${fn:escapeXml(product.name)}"
                                alt="${product.name}" width="100%" />
                    </c:when>
                    <c:otherwise>
<%--                         <theme:image code="img.missingProductImage.${format}" alt="${product.name}" --%>
<%--                                 title="${product.name}" /> --%>
                    </c:otherwise>
                </c:choose>
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
</div>
