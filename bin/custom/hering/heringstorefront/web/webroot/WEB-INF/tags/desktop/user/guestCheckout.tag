<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<!-- <section> -->
<!--     <header> -->
<!--         <h2> -->
<%--             <spring:theme code="guest.checkout" /> --%>
<!--         </h2> -->
<!--     </header> -->
<%--     <form:form action="${action}" method="post" commandName="heringGuestForm"> --%>
<%--         email --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formInputBox idKey="guest.email" labelKey="guest.email" path="email" --%>
<%--                 inputCSS="text" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         cpf --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formInputBox idKey="guest.cpfcnpj" labelKey="guest.cpfcnpj" path="cpfcnpj" --%>
<%--                 inputCSS="text" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         data de nascimento --%>
<!--         <div class="f-row nascimento"> -->
<%--             <formElement:formInputBox idKey="register.birthday" labelKey="register.birthday" --%>
<%--                 path="birthday" inputCSS="text" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         sexo --%>
<!--         <div class="f-row sexo"> -->
<%--             <p class="label"><spring:theme code="text.fliegercommerce.texto33"/></p> --%>
<%--             <form:radiobutton cssClass="registerRadioFemale" idKey="guest.gender" --%>
<%--                 labelKey="guest.gender" path="gender" value="FEMALE" checked="true" /> --%>
<%--             <spring:theme code="guest.female" /> --%>
<%--             <form:radiobutton cssClass="registerRadioMale" idKey="guest.gender" --%>
<%--                 labelKey="guest.gender" path="gender" value="MALE" /> --%>
<%--             <spring:theme code="guest.male" /> --%>
<!--         </div> -->
<%--         requerido --%>
<%--         <small><spring:theme code="form.required" /></small> --%>
<%--         <button class="btn btn-registro"><spring:theme code="text.fliegercommerce.texto87"/></button> --%>
<%--     </form:form> --%>
<!-- </section> -->

<h2>Neukunden</h2>
<p>Bestellen Sie als Gast, wenn Sie zum ersten Mal bei uns einkaufen:</p>
<form>
	<a href="#" onclick="$('#registerForm').removeClass('out').addClass('in');" 
		class="btn btn-primary btn-registerForm">Als Gast bestellen</a>
</form>
<form class="f-row pfpj" style="display:none">
	<label>
		<input type="radio" id="radiopf" name="tipo" ${pf ? 'checked' : ''}>
		<spring:theme code="text.fliegercommerce.texto31"/> 
	</label> 
	<label>
		<input type="radio" id="radiopj" name="tipo" ${!pf ? 'checked' : ''}>
		<spring:theme code="text.fliegercommerce.texto32"/>
	</label>
</form>
<div class="panel-collapse collapse out" id="registerForm" name="registerForm">
	<h3>Adresse eingeben</h3>
	<p>Bitte geben Sie Ihre Rechnungsadresse ein. Sie können später eine abweichende Lieferadresse eingeben.</p>
	<form:form method="post" cssClass="pf" commandName="heringGuestForm" action="${action}">
		<div class="form-group">
			<formElement:formSelectBox idKey="address.title" labelKey="address.title" 
				path="titleCode" mandatory="true" skipBlank="false" 
				skipBlankMessageKey="address.title.pleaseSelect" items="${titles}" 
				selectedValue="${addressForm.titleCode}" selectCSSClass="form-control" />
		</div>
		<div class="row">
			<div class="form-group col-sm-6">
				<formElement:formInputBox size="30" idKey="register.firstName"
					labelKey="register.firstName" path="firstName" inputCSS="text required-letters form-control"
			        mandatory="true" hideInputErrorOnField="true" />
			</div>
			<div class="form-group col-sm-6">
				<formElement:formInputBox idKey="register.lastName" labelKey="register.lastName"
			        path="lastName" inputCSS="text required-letters form-control" mandatory="true"
			        hideInputErrorOnField="true" />
			</div>
		</div>
		<p>
			<a data-toggle="collapse" href="#toggleInvoiceAdditionalAddress" class="collapsed">
				<span class="glyphicon glyphicon-triangle-right"></span> 
				Adresszusatz (optional)
			</a>
		</p>
	    <div id="toggleInvoiceAdditionalAddress" class="out form-group collapse" style="height: 0px;">
			<formElement:formInputBox idKey="register.complement" labelKey="register.complement"
			        path="complement" inputCSS="text required-letters form-control" mandatory="true"
			        hideInputErrorOnField="true" />
		</div>
		<div class="row">
			<div class="form-group col-sm-8">
				<formElement:formInputBox idKey="register.street" labelKey="register.street"
			        path="street" inputCSS="text required-letters form-control" mandatory="true"
			        hideInputErrorOnField="true" />
			</div>
			<div class="form-group col-sm-4">
			<formElement:formInputBox idKey="register.number" labelKey="register.number"
			        path="number" inputCSS="text required-numbers form-control" mandatory="true"
			        hideInputErrorOnField="true" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-4">
				<formElement:formInputBox idKey="register.zipCode" labelKey="register.zipCode"
			        path="zipCode" inputCSS="text required-numbers form-control" mandatory="true"
			        hideInputErrorOnField="true" />
			</div>
			<div class="form-group col-sm-8">
				<formElement:formInputBox idKey="register.place" labelKey="register.place"
			        path="place" inputCSS="text required-letters form-control" mandatory="true"
			        hideInputErrorOnField="true" />
			</div>
		</div>
		<div class="form-group">
			<formElement:formSelectBox idKey="register.country" labelKey="address.country" 
				path="country" mandatory="true" skipBlank="false" 
				skipBlankMessageKey="address.selectCountry" items="${countries}" 
				itemValue="isocode" tabindex="18" selectCSSClass="form-control" />
		</div>
		<p>
			<a data-toggle="collapse" data-target="#togglePhone">
				<span class=" glyphicon glyphicon-collapse-down"></span> 
				Telefonnummer (optional)
			</a>
			<small> 
				<a href="#">
					<span class="glyphicon glyphicon-info-sign"></span> 
					Warum?
				</a>
			</small>
		</p>
		<div id="togglePhone" class="form-group out collapse in">
				<formElement:formInputBox idKey="register.telephone" labelKey="register.telephone"
			        path="telephone" inputCSS="text required-numbers form-control" mandatory="true"
			        hideInputErrorOnField="true" />
		</div>
		<div class="form-group">
	        <div class="f-row">
	            <formElement:formInputBox idKey="register.email" labelKey="register.email" path="email"
	                inputCSS="text form-control" mandatory="true" hideInputErrorOnField="true" />
	        </div>
		</div>
	    <ycommerce:testId code="register_Register_button">
	        <button type="submit" class="btn btn-primary btn-lg">
	            <spring:theme code="text.fliegercommerce.texto74"/>
	        </button>
	    </ycommerce:testId>
	</form:form>
</div>
