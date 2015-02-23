<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="galleryImages" required="true" type="java.util.List"%>
<div class="col-xs-12 col-sm-6 v-bottom">
    <c:if test="${fn:contains(product.url, '?sku=')}">
        <c:url value="${fn:substringBefore(product.url, '?sku=')}/zoomImages"
            var="productZoomImagesUrl" />
    </c:if>
    <c:if test="${not fn:contains(product.url, '?sku=')}">
        <c:url value="${product.url}/zoomImages" var="productZoomImagesUrl" />
    </c:if>
    <product:productPrimaryImage product="${product}" format="zoom" />
<!--            <ul class="jcarousel-skin"> -->
<%--     <c:forEach items="${galleryImages}" var="container" varStatus="varStatus"> --%>
<%--         <li><span class="thumb ${(varStatus.index==0)? "active":""}"> <img --%>
<%--                 src="${container.thumbnail.url}" data-primaryimagesrc="${container.product.url}" --%>
<%--                 data-galleryposition="${varStatus.index}" alt="${container.thumbnail.altText}" --%>
<%--                 title="${container.thumbnail.altText}" /> --%>
<!--         </span></li> -->
<%--     </c:forEach> --%>
<!--            </ul> -->
</div>
