<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="customer" required="true"
    type="de.hybris.platform.commercefacades.user.data.CustomerData"%>
<%@ attribute name="type" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="panel-heading">
	<span class="glyphicon glyphicon-file"></span> 
	<c:if test="${type == 'billing'}">
		Rechnungsadresse<br>
	</c:if>
	<c:if test="${type == 'delivery'}">
		Lieferadresse<br>
	</c:if>
	<div class="pull-right">
		<small>
			<a href="#invoiceAddressModal" data-toggle="modal">
				<span class="glyphicon glyphicon-cog"></span>
			</a>
		</small>
	</div>
</div>
<div class="panel-body">
	${customer.firstName}&nbsp;${customer.lastName}<br>
	<c:if test="${type == 'billing'}">
		${customer.defaultBillingAddress.formattedAddress}<br>
	</c:if>
	<c:if test="${type == 'delivery'}">
		${customer.defaultShippingAddress.formattedAddress}<br>
	</c:if>
</div>