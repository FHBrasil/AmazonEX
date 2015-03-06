<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- <spring:escapeBody javaScriptEscape="true"> --%>
<c:set var='primaryImage' value='${ycommerce:productImage(product, \'zoom\')}' />

{
    "variantCode" : "${product.code}"
  , "variantStock" : "${product.stock.stockLevel}"
    <c:choose>
        <c:when test="${not empty primaryImage}">
          , "variantPrimaryImageUrl" : "${primaryImage.url}"
          , "variantImageTitle" : "${fn:escapeXml(primaryImage.title)}"
          , "variantImageAltText" : "${fn:escapeXml(primaryImage.altText)}"
        </c:when>
        <c:otherwise>
          , "variantPrimaryImageUrl" : "${primaryImage.url}"
          , "variantImageTitle" : "${fn:escapeXml(product.name)}"
          , "variantImageAltText" : "${fn:escapeXml(product.name)}"
        </c:otherwise>
    </c:choose>
  , "variantGalleryImages" : "${galleryImages}"
  
  , "variantTabContent" : []
}
