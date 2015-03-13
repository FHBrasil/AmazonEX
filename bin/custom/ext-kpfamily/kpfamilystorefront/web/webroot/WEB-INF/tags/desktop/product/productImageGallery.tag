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
    <c:set var="count" value="${0}"/>
    <c:forEach var="image" items="${galleryImages}">
        <li class="col-xs-2" data-target="#mainCarousel" data-slide-to="${count}">
            <img src="<c:url value='${image.url}'/>" width="100%" alt="Produktbild ${count+1}">
        </li>
        <c:set var="count" value="${count+1}" />
    </c:forEach>
</ol>