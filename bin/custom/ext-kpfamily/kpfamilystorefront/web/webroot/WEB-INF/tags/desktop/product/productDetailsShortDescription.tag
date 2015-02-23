<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>

<spring:theme code="text.addToCart" var="addToCartText" />

<div class="col-sm-12 text-right hidden-xs">
    <a href="#">
        <img src="product/brand.jpg">
    </a>
</div>
<h1>
    <b>${product.manufacturer}</b> ${product.name}
</h1>
<!--     <p>product rating -->
<!--         <span class="glyphicon stars">&#57350;&#57350;&#57350;&#57350;&#57350;<span -->
<!--             style="width: 90%">&#57350;&#57350;&#57350;&#57350;&#57350;</span></span> 4.8 (17) <a -->
<!--             href="#">Bewertung schreiben</a> -->
<!--     </p> -->
<p>
    <%--         ${product.description} -short description --%>
    <!--         <a href="#">read moreweiterlesen</a> -->
</p>