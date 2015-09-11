<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="klarnaMonthlyCosts" required="true" type="de.hybris.platform.commercefacades.product.data.PriceData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="/WEB-INF/tld/ycommercetags.tld" %>

<style type="text/css">
.prod .prod_review .klarna-link
{
	float: left;
	margin-right: 6px;
}
</style>

<c:if test="${not empty klarnaMonthlyCosts}">
	<div class="prod">
		<p>				
		<spring:theme code="product.klarna.account.text" arguments="${klarnaMonthlyCosts.formattedValue}" argumentSeparator="#~/@!£$%^" />
		</p>				
		<div class="prod_review">		
			<div id="product_klarna_link_to_account">
				<div style="float: left; margin-right: 6px;"><img src="${commonResourcePath}${countrySpecificKlarnaAccountLogo}"></div><div style="float: left; margin-right: 6px;"></div>
			</div>
			<div>
			<br/>
			<c:if test="${not empty creditWarningLogoNL}"><img width="215px" height="25px" border="2" src="${commonResourcePath}${creditWarningLogoNL}"></c:if>
			</div>
			<script src="https://cdn.klarna.com/public/kitt/core/v1.0/js/klarna.min.js" ></script>  
    		<script src="https://cdn.klarna.com/public/kitt/toc/v1.1/js/klarna.terms.min.js" ></script>
			<script type="text/javascript">
             new Klarna.Terms.Account({
                 el: 'product_klarna_link_to_account',
                 eid: ${merchantId},
                 country: '${currentKlarnaCountryIsoCode}',
                 charge: 0
             })
			</script>		
		</div>		
		<div style="clear: both;"></div>		
	</div>
</c:if>