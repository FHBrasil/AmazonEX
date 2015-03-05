<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<%-- <spring:escapeBody javaScriptEscape="true"> --%>

{
    "variantCode" : "${product.code}"
  , "variantStock" : "${product.stock.stockLevel}"
  , "variantPrimaryImageUrl" : "<c:set var='primaryImage' value='${ycommerce:productImage(product, \'zoom\')}' /> ${primaryImage.url}"
  , "variantGalleryImages" : "${galleryImages}"
  , "variantTabContent" : []
}
