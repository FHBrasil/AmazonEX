<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="customer" required="true"
    type="de.hybris.platform.commercefacades.user.data.CustomerData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="panel-heading">
	<span class="glyphicon glyphicon-user"></span> 
	Konto ${customer.titleCode}
	<div class="pull-right">
		<small>
			<a href="#accountModal" data-toggle="modal">
				<span class="glyphicon glyphicon-cog"></span>
			</a>
		</small>
	</div>
</div>
<div class="panel-body">
	E-Mail: ${customer.uid} <br>
	Telefon: (${customer.defaultShippingAddress.dddPhone}) ${customer.defaultShippingAddress.phone}<br>
	Passwort: ********
</div>