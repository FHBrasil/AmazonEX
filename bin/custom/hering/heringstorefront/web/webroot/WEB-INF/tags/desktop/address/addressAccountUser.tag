<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="address" required="true" type="de.hybris.platform.commercefacades.user.data.AddressData"%>
<%@ attribute name="type" required="true"%>
<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address"%>

<div class="panel-heading">
	<span class="glyphicon glyphicon-file"></span> 
	<c:if test="${type == 'billing'}">
		<spring:theme code="text.account.addressBilling" />
	</c:if>
	<c:if test="${type == 'delivery'}">
		<spring:theme code="text.account.addressDelivery" />
	</c:if>
	<div class="pull-right">
		<small>
			<a href="#deliveryAddressModal" data-toggle="modal" class="btn-editar ${type == 'billing' ? type : ''}">
				<span class="glyphicon glyphicon-cog"></span>
			</a>			
		</small>
	</div>
</div>
<div class="panel-body">
	${address.receiver}<br />
    ${address.line1},&nbsp;${address.number}<br />
    <c:if test="${not empty address.complement}">
    	${address.complement}<br />
    </c:if>
    <c:if test="${not empty address.reference}">
    	${address.reference}<br />
    </c:if>
    ${address.postalCode}&nbsp;${address.town}<br />
   	<c:if test="${not empty address.region.name}">
    	${address.region.name}<br />
    </c:if>                
    ${address.country.name}<br />
</div>
<address:addressFormSelector regions="${regions}" country="${country}"/>