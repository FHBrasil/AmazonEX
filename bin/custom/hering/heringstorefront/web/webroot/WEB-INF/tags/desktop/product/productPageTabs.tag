<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<div>
 	<ul class="nav nav-tabs margin-top" id="tab150219">
        <li class="active"><a data-toggle="tab" href="#sectionA"><spring:theme code="product.product.details" /></a></li>
        <li><a data-toggle="tab" href="#sectionB"><spring:theme code="review.reviews" /></a></li>
    </ul>
</div>
		<product:productBrand product="${product}" />
	<div class="col-md-6 col-sm-8 tab150119">   
        <div class="tab-content">
            <div id="sectionA" class="tab-pane fade in active">
                <product:productDetailsTab product="${product}" />
            </div>
            <div id="sectionB" class="tab-pane fade">
                <product:productPageReviewsTab product="${product}" />
            </div>
        </div>
    </div>



<%-- code original
<div class="tabs">
    <ul class="tabs-header">
        <li class="active"><a href="#"><spring:theme code="product.product.details" /></a></li>
        <li><a href="#"><spring:theme code="review.reviews" /></a></li>
    </ul>
    <div class="tabs-content">
        <product:productDetailsTab product="${product}" />
        <product:productPageReviewsTab product="${product}" />
    </div>
</div>--%>
