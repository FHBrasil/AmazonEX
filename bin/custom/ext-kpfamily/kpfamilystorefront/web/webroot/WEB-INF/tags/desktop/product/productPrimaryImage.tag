<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="format" required="true" type="java.lang.String"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<c:set var="primaryImage" value="${ycommerce:productImage(product, format)}" />
<c:url var="productImageUrl" value="${primaryImage.url}" />

<c:choose>
    <c:when test="${not empty primaryImage}">
        <c:choose>
            <c:when test="${not empty primaryImage.altText}">
                <img id="productDetailImage" src="${productImageUrl}"
                        title="${fn:escapeXml(primaryImage.altText)}"
                        alt="${fn:escapeXml(primaryImage.altText)}" width="100%" />
            </c:when>
            <c:otherwise>
                <img id="productDetailImage" src="${productImageUrl}"
                        title="${fn:escapeXml(product.name)}" alt="${fn:escapeXml(product.name)}"
                        width="100%" />
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <theme:image id="productDetailImage" code="img.missingProductImage.${format}"
                alt="${fn:escapeXml(product.name)}" title="${fn:escapeXml(product.name)}"/>
    </c:otherwise>
</c:choose>
