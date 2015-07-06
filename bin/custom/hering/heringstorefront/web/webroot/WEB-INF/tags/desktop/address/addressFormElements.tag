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
<%-- 
<c:if test="${enableEditAddressAnchor}">
   	 <div id="editAnchor"></div>
</c:if>
--%>

<c:choose>
	<c:when test="${type == 'packStation'}">
		<form:hidden path="countryIso" />
		<p>
			<spring:theme code="checkout.single.packstation.registeredPackstation"/>&nbsp;
			<a target="_blank" href="https://partner.dhl.de/klick.php?bannerid=48&pid=1419&prid=1">
				<spring:theme code="checkout.single.packstation.registerHere"/>
			</a>
		</p>
		<div class="form-group">
			<formElement:formSelectBox idKey="address.title" labelKey="address.title"
		        	path="titleCode" mandatory="true" skipBlank="true" items="${titles}"
		        	selectedValue="${packstationAddressForm.titleCode}" selectCSSClass="form-control" />
		</div>
		<div class="row">
			<div class="form-group col-sm-6">
		    <formElement:formInputBox idKey="address.firstName" labelKey="address.firstName"
		        path="firstName" inputCSS="text form-control" mandatory="true" />    
			</div>
			<div class="form-group col-sm-6">
			    <formElement:formInputBox idKey="address.lastName" labelKey="address.lastName" path="lastName"
			        inputCSS="text form-control" mandatory="true" />
			</div>
		</div>
		<div class="form-group">
			<formElement:formInputBox idKey="packstation.postNumber" labelKey="packstation.postNumber"
		        path="reference" inputCSS="text form-control" mandatory="true" />
		</div>
		<div class="row">
			<div class="form-group col-sm-8">
				<formElement:formSelectBox idKey="packstation.packstation" labelKey="packstation.packstation"
		        	path="addressType" mandatory="true" skipBlank="true" items="${packstationType}"
		        	selectedValue="${packstationAddressForm.addressType}" selectCSSClass="form-control" readOnly="true" />
			</div>
			<div class="form-group col-sm-4">
				<formElement:formInputBox idKey="address.number" labelKey="address.number" path="number"
		        	inputCSS="text form-control" mandatory="true" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-4">
				 <formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode"
		        	inputCSS="text cep form-control" mandatory="true" autocomplete="off" size="8" />
		   	</div>
		   	<div class="form-group col-sm-8">
		   		<formElement:formInputBox idKey="address.place" labelKey="address.place" path="townCity"
		        	inputCSS="text form-control" mandatory="true" />
			</div>
		</div>
		<div class="form-group">
			<formElement:formInputBox idKey="text.fliegercommerce.texto95" labelKey="text.fliegercommerce.texto95"
		        path="countryIso" inputCSS="text form-control" mandatory="false" readOnly="true"/>
		</div>
	</c:when>
	<c:otherwise>		
		<div class="form-group">
			<formElement:formSelectBox idKey="address.title" labelKey="address.title"
		        	path="titleCode" mandatory="true" skipBlank="true" items="${titles}"
		        	selectedValue="${heringAddressForm.titleCode}" selectCSSClass="form-control" />
		</div>
		<div class="row">
			<div class="form-group col-sm-6">
			    <formElement:formInputBox idKey="address.firstName" labelKey="address.firstName"
			        path="firstName" inputCSS="text form-control" mandatory="true" />    
			</div>
			<div class="form-group col-sm-6">
			    <formElement:formInputBox idKey="address.lastName" labelKey="address.lastName" path="lastName"
			        inputCSS="text form-control" mandatory="true" />
			</div>
		</div>
		<p>
			<a data-toggle="collapse" href="#toggleDeliveryAdditionalAddress"><span class="glyphicon glyphicon-triangle-right"></span> 
			<spring:theme code="address.addressExtension"/>&nbsp;(<spring:theme code="address.optional"/>)</a>
		</p>
		<div id="toggleDeliveryAdditionalAddress" class="collapse out form-group">
			<formElement:formInputBox idKey="address.complement" labelKey="" placeholder="z.B. Mami & Co. GmbH, 2. Stock" path="complement" inputCSS="text form-control" mandatory="false" />
		</div>
		<%-- 
		<div class="form-group col-sm-6">
		    <formElement:formInputBox idKey="address.phone2" labelKey="address.phone2" path="celPhone"
		        inputCSS="text form-control" mandatory="false" autocomplete="off" />
		</div>
		
		<div class="form-group col-sm-12">
		    <formElement:formInputBox idKey="address.receiver" labelKey="address.receiver" path="receiver"
		        inputCSS="text form-control" mandatory="false" />
		</div>
		--%>
		<div class="row">
			<div class="form-group col-sm-8">
			    <formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="line1"
			        inputCSS="text form-control" mandatory="true" />
			</div>
			<div class="form-group col-sm-4">
			    <formElement:formInputBox idKey="address.number" labelKey="address.number" path="number"
			        inputCSS="text form-control" mandatory="true" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-4">
			    <formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode"
			        inputCSS="text cep form-control" mandatory="true" autocomplete="off" size="8" />
			</div>
			<div class="form-group col-sm-8">
			    <formElement:formInputBox idKey="address.place" labelKey="address.place" path="townCity"
			        inputCSS="text form-control" mandatory="true" />
			</div>
		</div>
		<%-- 
		<div class="form-group col-sm-8">
		    <formElement:formInputBox idKey="address.place" labelKey="address.place"
		        path="neighborhood" inputCSS="text form-control" mandatory="true" />
		</div>
		
		<div class="f-row-50">
		    <a target="_blank" class="nao-sei"
		        href="http://www.buscacep.correios.com.br/servicos/dnec/menuAction.do?Metodo=menuEndereco">
		        <spring:theme code="checkout.single.address.postalCode.dontKnow" />
		    </a>
		</div>
		--%>
		
		<div class="form-group">
		    <formElement:formSelectBox idKey="address.country" labelKey="address.country" items="${countries}"
		        path="countryIso" itemValue="isocode" mandatory="true" selectCSSClass="form-control"/>
		</div>
		<%-- 
		<div class="form-group col-sm-6">
		    <formElement:formInputBox idKey="address.reference" labelKey="address.reference"
		        path="reference" inputCSS="text form-control" mandatory="false" />
		</div>
		--%>
		<div class="form-group" style="display: none !important;">
		    <formElement:formSelectBox idKey="address.addressType" labelKey="address.addressType"
		        path="addressType" mandatory="true" skipBlank="true" items="${addressTypes}"
		        selectedValue="${heringAddressForm.addressType}" selectCSSClass="form-control" />
		</div>
		<p>
			<a data-toggle="collapse" data-target="#togglePhone"><span class=" glyphicon glyphicon-collapse-down"></span> 
			<spring:theme code="address.phone1"/>&nbsp;(<spring:theme code="address.optional"/>)</a><small> 
			<a href="#"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;<spring:theme code="address.why"/></small></a>
		</p>
		<div id="togglePhone" class="form-group collapse out">
			<formElement:formInputBox idKey="address.phone1" labelKey="" path="phone"
		        inputCSS="text form-control" mandatory="false" autocomplete="off" placeholder="Telefonnummer" />
		</div>		
	</c:otherwise>
</c:choose>

<%-- OLD CODE
<c:if test="${enableEditAddressAnchor}">
    <div id="editAnchor"></div>
</c:if>
<form:hidden path="countryIso" />
<div class="form-row">
    <formElement:formInputBox idKey="address.receiver" labelKey="address.receiver" path="receiver"
        inputCSS="text" mandatory="false" />
</div>
<div class="form-row">
    <formElement:formInputBox idKey="address.phone1" labelKey="address.phone1" path="phone"
        inputCSS="text" mandatory="true" autocomplete="off" />
    <div class="rightForm">
        <formElement:formInputBox idKey="address.phone2" labelKey="address.phone2" path="celPhone"
            inputCSS="text" mandatory="false" autocomplete="off" />
    </div>
</div>
<div class="form-row">
    <formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode"
        inputCSS="text cep" mandatory="true" autocomplete="off" size="8" />
    <a href="javascript:void(0);" class="btn btn-ok">ok</a> <a target="_blank" class="nao-sei"
        href="http://www.buscacep.correios.com.br/servicos/dnec/menuAction.do?Metodo=menuEndereco"><spring:theme
            code="address.search.dont.know" /></a>
</div>
<div class="form-row" style="display: none">
    <formElement:formInputBox idKey="address.firstName" labelKey="address.firstName"
        path="firstName" inputCSS="text" mandatory="true" />
    <formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="lastName"
        inputCSS="text" mandatory="true" />
</div>
<div class="form-row">
    <formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="line1"
        inputCSS="text" mandatory="true" />
    <formElement:formInputBox idKey="address.number" labelKey="address.number" path="number"
        inputCSS="text" mandatory="true" />
</div>
<div class="form-row">
    <formElement:formInputBox idKey="address.district" labelKey="address.district"
        path="neighborhood" inputCSS="text" mandatory="true" />
    <div class="rightForm">
        <formElement:formInputBox idKey="address.complement" labelKey="address.complement"
            path="complement" inputCSS="text" mandatory="false" />
    </div>
</div>
<div class="form-row">
    <formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="townCity"
        inputCSS="text" mandatory="true" />
    <div class="rightForm">
        <formElement:formInputBox idKey="address.reference" labelKey="address.reference"
            path="reference" inputCSS="text" mandatory="false" />
    </div>
</div>
<div class="form-row">
    <formElement:formSelectBox idKey="address.addressType" labelKey="address.addressType"
        path="addressType" mandatory="true" skipBlank="true" items="${addressTypes}"
        selectedValue="${heringAddressForm.addressType}" />
    <div class="rightForm">
        <formElement:formSelectBox idKey="address.state" labelKey="address.state" items="${regions}"
            path="regionIso" itemValue="${useShortRegionIso ? 'isocodeShort' : 'isocode'}"
            mandatory="true" />
    </div>
</div>
<div class="form-row">
    <c:if test="${page == 'ACCOUNT' }">
        <formElement:formCheckbox idKey="address.defaultAddress" labelKey="address.default"
            path="defaultAddress" mandatory="false" />
        <formElement:formCheckbox idKey="address.billing" labelKey="address.billing" path="billing"
            mandatory="false" />
    </c:if>
</div>
<p class="required-info">*<spring:theme code="text.fliegercommerce.texto88"/></p>
--%>