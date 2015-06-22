<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="false" type="java.lang.String"%>
<%@ attribute name="tabIndex" required="false" type="java.lang.Integer"%>
<%@ attribute name="page" required="false" type="java.lang.String"%>
<%@ attribute name="cancelUrl" required="false" type="java.lang.String"%>
<%@ attribute name="type" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<c:choose>
	<c:when test="${type == 'packStation'}">
		<p>Sie sind noch nicht f&uuml;r die Packstation registriert? <a target="_blank" href="https://partner.dhl.de/klick.php?bannerid=48&pid=1419&prid=1">Hier anmelden!</a></p>
		<div class="form-group">
			<label for="inputPackstationSalutation">Anrede</label>
			<select class="form-control" id="inputPackstationSalutation">
				<option>Frau</option>
				<option>Herr</option>
				<option>Firma</option>
			</select>
		</div>
		<div class="row">
			<div class="form-group col-sm-6">
				<label for="address.firstName"><spring:theme code="address.firstName"/></label>
				<input type="text" class="form-control" id="address.firstName" required>   
			</div>
			<div class="form-group col-sm-6">
				<label for="address.surname"><spring:theme code="address.surname"/></label>
				<input type="text" class="form-control" id="address.surname" required>
			</div>
		</div>
		<div class="form-group">
			<label for="address.phone1"><spring:theme code="address.phone1"/></label>
			<input type="number" class="form-control" id="address.phone1" placeholder="z.B. 12345678" required>
		</div>
		<div class="row">
			<div class="form-group col-sm-8">
				<label for="inputPackstationStreet">Packstation</label>
				<input type="text" class="form-control" id="inputPackstationStreet" value="Packstation" readonly="true">
			</div>
			<div class="form-group col-sm-4">
				<label for="address.number"><spring:theme code="address.number"/></label>
				<input type="number" class="form-control" id="address.number" placeholder="z.B. 123" required>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-4">
				<label for="address.postcode"><spring:theme code="address.postcode"/></label>
				<input type="number" class="form-control" id="address.postcode" required>
		   	</div>
		   	<div class="form-group col-sm-8">
		   		<label for="address.townCity"><spring:theme code="address.townCity"/></label>
				<input type="text" class="form-control" id="address.townCity" required>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPackstationCountry">Land</label>
			<input type="text" class="form-control" id="inputPackstationCity" value="Deutschland" readonly="true">
		</div>
		<div class="checkbox text-right">
			<label ><input type="checkbox" checked="true"> in Adressbuch merken</label>
		</div>
	</c:when>
	<c:otherwise>
		<c:if test="${enableEditAddressAnchor}">
   	 		<div id="editAnchor"></div>
		</c:if>
		<form:hidden path="countryIso" />
		
		<div class="form-group col-sm-6">
		    <formElement:formInputBox idKey="address.firstName" labelKey="address.firstName"
		        path="firstName" inputCSS="text form-control" mandatory="true" />    
		</div>
		<div class="form-group col-sm-6">
		    <formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="lastName"
		        inputCSS="text form-control" mandatory="true" />
		</div>
		<div class="form-group col-sm-6">
		    <formElement:formInputBox idKey="address.phone1" labelKey="address.phone1" path="phone"
		        inputCSS="text form-control" mandatory="true" autocomplete="off" />
		</div>
		<div class="form-group col-sm-6">
		    <formElement:formInputBox idKey="address.phone2" labelKey="address.phone2" path="celPhone"
		        inputCSS="text form-control" mandatory="false" autocomplete="off" />
		</div>
		<div class="form-group col-sm-12">
		    <formElement:formInputBox idKey="address.receiver" labelKey="address.receiver" path="receiver"
		        inputCSS="text form-control" mandatory="false" />
		</div>
		<div class="form-group col-sm-7">
		    <formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="line1"
		        inputCSS="text form-control" mandatory="true" />
		</div>
		<div class="form-group col-sm-5">
		    <formElement:formInputBox idKey="address.number" labelKey="address.number" path="number"
		        inputCSS="text form-control" mandatory="true" />
		</div>
		<div class="form-group col-sm-4">
		    <formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode"
		        inputCSS="text cep form-control" mandatory="true" autocomplete="off" size="8" />
		</div>
		<%-- 
		<div class="f-row-50">
		    <a target="_blank" class="nao-sei"
		        href="http://www.buscacep.correios.com.br/servicos/dnec/menuAction.do?Metodo=menuEndereco">
		        <spring:theme code="checkout.single.address.postalCode.dontKnow" />
		    </a>
		</div>
		--%>
		<div class="form-group col-sm-8">
		    <formElement:formInputBox idKey="address.district" labelKey="address.district"
		        path="neighborhood" inputCSS="text form-control" mandatory="true" />
		</div>
		<div class="form-group col-sm-6">
		    <formElement:formInputBox idKey="address.complement" labelKey="address.complement"
		        path="complement" inputCSS="text form-control" mandatory="false" />
		</div>
		<div class="form-group col-sm-6">
		    <formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="townCity"
		        inputCSS="text form-control" mandatory="true" />
		</div>
		<div class="form-group col-sm-12">
		    <formElement:formSelectBox idKey="address.state" labelKey="address.state" items="${regions}"
		        path="regionIso" itemValue="${useShortRegionIso ? 'isocodeShort' : 'isocode'}"
		        mandatory="true" selectCSSClass="form-control"/>
		</div>
		<div class="form-group col-sm-6">
		    <formElement:formInputBox idKey="address.reference" labelKey="address.reference"
		        path="reference" inputCSS="text form-control" mandatory="false" />
		</div>
		<div class="form-group col-sm-6">
		    <formElement:formSelectBox idKey="address.addressType" labelKey="address.addressType"
		        path="addressType" mandatory="true" skipBlank="true" items="${addressTypes}"
		        selectedValue="${heringAddressForm.addressType}" selectCSSClass="form-control" />
		</div>
		<c:if test="${page == 'ACCOUNT' }">
		    <div class="f-row">
		        <formElement:formCheckbox idKey="address.defaultAddress" labelKey="address.default"
		            path="defaultAddress" mandatory="false" />
		    </div>
		    <div class="f-row">
		        <formElement:formCheckbox idKey="address.billing" labelKey="address.billing" path="billing"
		            mandatory="false" />
		    </div>
		</c:if>
	</c:otherwise>
</c:choose>