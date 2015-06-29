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
		Rechnungsadresse
	</c:if>
	<c:if test="${type == 'delivery'}">
		Lieferadresse
	</c:if>
	<div class="pull-right">
		<small>
			<c:if test="${type == 'billing'}">
				<a href="#billingAddressModal" data-toggle="modal">
					<span class="glyphicon glyphicon-cog"></span>
				</a>
			</c:if>
			<c:if test="${type == 'delivery'}">
				<a href="#deliveryAddressModal" data-toggle="modal">
					<span class="glyphicon glyphicon-cog"></span>
				</a>
			</c:if>
		</small>
	</div>
</div>
<div class="panel-body">
	${customer.firstName}&nbsp;${customer.lastName}<br>
	<c:if test="${type == 'billing'}">
		${customer.defaultBillingAddress.line1}, 
		&nbsp;${customer.defaultBillingAddress.number} 
		&nbsp;${customer.defaultBillingAddress.region.name}<br>	
		${customer.defaultBillingAddress.postalCode},
		&nbsp;${customer.defaultBillingAddress.district}<br>
		${customer.defaultBillingAddress.country.name}
	</c:if>
	<c:if test="${type == 'delivery'}">
		${customer.defaultShippingAddress.line1}, 
		&nbsp;${customer.defaultShippingAddress.number} 
		&nbsp;${customer.defaultShippingAddress.region.name}<br>	
		${customer.defaultShippingAddress.postalCode},
		&nbsp;${customer.defaultShippingAddress.district}<br>
		${customer.defaultShippingAddress.country.name}
	</c:if>
</div>


<c:if test="${type == 'billing'}">
<!-- invoceAddressModal HTML -->
    <div id="billingAddressModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"><span class="glyphicon glyphicon-file"></span> Rechnungsadresse &auml;ndern</h4>
                </div>
                <div class="modal-body">
					<p class="underlinelinks150212">Diese Rechnungsadresse wird f&uuml;r zuk&uuml;nftige Bestellungen verwendet. Um die Adresse f&uuml;r eine laufende Bestellung zu &auml;ndern, wenden Sie sich an unseren <a href="#">Kundenservice</a>.</p>
                    <form>
						<!-- this form is exactly same like at index-register.html ... maybe we should make a component for that? -->
						<div class="form-group">
							<label for="inputInvoiceSalutation">Anrede</label>
							<select class="form-control" id="inputInvoiceSalutation">
								<option>Frau</option>
								<option selected>Herr</option>
								<option>Firma</option>
							</select>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label for="inputInvoiceFirstname">Vorname</label>
								<input type="text" class="form-control" id="inputInvoiceFirstname" required="true" value="${customer.firstName}">
							</div>
							<div class="form-group col-sm-6">
								<label for="inputInvoiceLastname">Nachname</label>
								<input type="text" class="form-control" id="inputInvoiceLastname" required="true" value="${customer.lastName}">
							</div>
						</div>
						<div class="form-group">
							<label for="inputInvoiceAdditionalAddress">Adresszusatz (optional)</label>
							<input type="text" class="form-control" id="inputInvoiceAdditionalAddress" placeholder="${customer.defaultBillingAddress.complement} ">
						</div>
						<div class="row">
							<div class="form-group col-sm-8">
								<label for="inputInvoiceStreet">Stra&szlig;e</label>
								<input type="text" class="form-control" id="inputInvoiceStreet" required="true" value="${customer.defaultBillingAddress.line1} ">
							</div>
							<div class="form-group col-sm-4">
								<label for="inputInvoiceStreetNo">Haus-Nr.</label>
								<input type="text" class="form-control" id="inputInvoiceStreetNo" required="true" value="${customer.defaultBillingAddress.number} ">
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-4">
								<label for="inputInvoiceZipcode">PLZ</label>
								<input type="text" class="form-control" id="inputInvoiceZipcode" required="true" value="${customer.defaultBillingAddress.postalCode} ">
							</div>
							<div class="form-group col-sm-8">
								<label for="inputInvoiceCity">Ort</label>
								<input type="text" class="form-control" id="inputInvoiceCity" required="true" value="${customer.defaultBillingAddress.district} ">
							</div>
						</div>
						<div class="form-group">
							<label for="inputInvoiceCountry">Land</label>
							<select class="form-control" id="inputInvoiceCountry">
								<option>Deutschland</option>
								<option selected>Brasil</option>
								<option>more...</option>
							</select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
					<button type="button" class="btn btn-primary">Speichern</button>
				</div>
			</div>
        </div>
    </div>
</c:if>

<c:if test="${type == 'delivery'}">
<!-- invoceAddressModal HTML -->
    <div id="deliveryAddressModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"><span class="glyphicon glyphicon-file"></span> Rechnungsadresse &auml;ndern</h4>
                </div>
                <div class="modal-body">
					<p class="underlinelinks150212">Diese Rechnungsadresse wird f&uuml;r zuk&uuml;nftige Bestellungen verwendet. Um die Adresse f&uuml;r eine laufende Bestellung zu &auml;ndern, wenden Sie sich an unseren <a href="#">Kundenservice</a>.</p>
                    <form>
						<!-- this form is exactly same like at index-register.html ... maybe we should make a component for that? -->
						<div class="form-group">
							<label for="inputInvoiceSalutation">Anrede</label>
							<select class="form-control" id="inputInvoiceSalutation">
								<option>Frau</option>
								<option selected>Herr</option>
								<option>Firma</option>
							</select>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label for="inputInvoiceFirstname">Vorname</label>
								<input type="text" class="form-control" id="inputInvoiceFirstname" required="true" value="${customer.firstName}">
							</div>
							<div class="form-group col-sm-6">
								<label for="inputInvoiceLastname">Nachname</label>
								<input type="text" class="form-control" id="inputInvoiceLastname" required="true" value="${customer.lastName}">
							</div>
						</div>
						<div class="form-group">
							<label for="inputInvoiceAdditionalAddress">Adresszusatz (optional)</label>
							<input type="text" class="form-control" id="inputInvoiceAdditionalAddress" placeholder="${customer.defaultShippingAddress.complement}">
						</div>
						<div class="row">
							<div class="form-group col-sm-8">
								<label for="inputInvoiceStreet">Stra&szlig;e</label>
								<input type="text" class="form-control" id="inputInvoiceStreet" required="true" value="${customer.defaultShippingAddress.line1}">
							</div>
							<div class="form-group col-sm-4">
								<label for="inputInvoiceStreetNo">Haus-Nr.</label>
								<input type="text" class="form-control" id="inputInvoiceStreetNo" required="true" value="${customer.defaultShippingAddress.number}">
							</div>/
						</div>
						<div class="row">
							<div class="form-group col-sm-4">
								<label for="inputInvoiceZipcode">PLZ</label>
								<input type="text" class="form-control" id="inputInvoiceZipcode" required="true" value="${customer.defaultShippingAddress.postalCode}">
							</div>
							<div class="form-group col-sm-8">
								<label for="inputInvoiceCity">Ort</label>
								<input type="text" class="form-control" id="inputInvoiceCity" required="true" value="${customer.defaultShippingAddress.district}">
							</div>
						</div>
						<div class="form-group">
							<label for="inputInvoiceCountry">Land</label>
							<select class="form-control" id="inputInvoiceCountry">
								<option>Deutschland</option>
								<option selected>Brasil</option>
								<option>more...</option>
							</select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
					<button type="button" class="btn btn-primary">Speichern</button>
				</div>
			</div>
        </div>
    </div>
</c:if>