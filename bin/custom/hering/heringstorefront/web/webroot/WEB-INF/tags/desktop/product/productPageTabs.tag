<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="bazaarvoice" tagdir="/WEB-INF/tags/addons/bazaarvoice/desktop/bazaarvoice"%>
<div>
 	<ul class="nav nav-tabs margin-top" id="tab150219">
        <li class="active"><a data-toggle="tab" href="#sectionA"><spring:theme code="product.product.details" /></a></li>
        <li><a data-toggle="tab" href="#sectionB"><spring:theme code="review.reviews" />&nbsp;<span class="badge">${product.numberOfReviews}</span></a></li>
        <li><a data-toggle="tab" href="#sectionC"><spring:theme code="product.product.technicalDetails" /></a></li>
        <li class="dropdown">
			<a data-toggle="dropdown" class="dropdown-toggle" href="#"><spring:theme code="product.more" /><b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a data-toggle="tab" href="#dropdown1"><spring:theme code="product.answerQuestions" /></a></li>
				<li><a data-toggle="tab" href="#dropdown2"><spring:theme code="product.downloads" />&nbsp;<span class="badge">0</span></a></li>
				<li><a data-toggle="tab" href="#dropdown3"><spring:theme code="product.accessoriesTab" />&nbsp;<span class="badge">0</span></a></li>
			</ul>
		</li>
    </ul>
</div>
<div class="col-xs-12 tab150119">
	<product:productBrand product="${product}" upper="false" />
</div>
	<div class="col-md-6 col-sm-8 tab150119">   
        <div class="tab-content">
            <div id="sectionA" class="tab-pane fade in active">
                <product:productDetailsTab product="${product}" />
            </div>
            <div id="sectionB" class="tab-pane fade">
				<%-- <product:productPageReviewsTab product="${product}" /> --%>
				<bazaarvoice:productPageReviewDetailsTab product="${product}" />
				<div id="BVRRSummaryContainer"></div>
			</div>
			<div id="sectionC" class="tab-pane fade">
				<product:productTechnicalDetails product="${product}"/>
			</div>
			<div id="dropdown1" class="tab-pane fade">
				<product:productAnswerQuestions product="${product}"/>
			</div>
			<div id="dropdown2" class="tab-pane fade">
				<product:productDownloads product="${product}"/>
			</div>
			<div id="dropdown3" class="tab-pane fade">
				<product:productAccessories product="${product}"/>
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
