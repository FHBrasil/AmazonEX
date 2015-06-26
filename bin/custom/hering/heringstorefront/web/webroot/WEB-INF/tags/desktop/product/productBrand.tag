<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
	<div class="col-xs-12 tab150119">
         <h2><b>[BRAND]</b>${product.name}</h2>
         <p>[FIXED]<span class="glyphicon stars">&#57350;&#57350;&#57350;&#57350;&#57350;<span style="width:90%">&#57350;&#57350;&#57350;&#57350;&#57350;</span></span><small> 4.8 (17) <a href="#">Bewertung schreiben</a></small></p>
    </div>
