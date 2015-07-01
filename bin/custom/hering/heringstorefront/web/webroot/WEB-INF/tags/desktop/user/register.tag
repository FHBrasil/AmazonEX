<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<!-- <section class="col-1 column"> -->
    <%-- cabeçalho do formulário --%>
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
	<form:form method="post" cssClass="pf" commandName="heringRegisterForm" action="${action}">
		<form:hidden path="pessoaFisica" value="true" />
		<div class="form-group">
			<label for="inputInvoiceSalutation">Anrede</label>
			<select class="form-control" id="inputInvoiceSalutation">
				<option>Frau</option>
				<option>Herr</option>
				<option>Firma</option>
			</select>
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
			<input type="text" class="form-control" id="inputInvoiceAdditionalAddress" placeholder="z.B. Mami &amp; Co. GmbH, 2. Stock">
		</div>
		<div class="row">
			<div class="form-group col-sm-8">
				<label for="inputInvoiceStreet">Straße</label>
				<input type="text" class="form-control" id="inputInvoiceStreet" required="true">
			</div>
			<div class="form-group col-sm-4">
				<label for="inputInvoiceStreetNo">Haus-Nr.</label>
				<input type="text" class="form-control" id="inputInvoiceStreetNo" required="true">
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-4">
				<label for="inputInvoiceZipcode">PLZ</label>
				<input type="text" class="form-control" id="inputInvoiceZipcode" required="true">
			</div>
			<div class="form-group col-sm-8">
				<label for="inputInvoiceCity">Ort</label>
				<input type="text" class="form-control" id="inputInvoiceCity" required="true">
			</div>
			</div>
		<div class="form-group">
			<label for="inputInvoiceCountry">Land</label>
			<select class="form-control" id="inputInvoiceCountry">
				<option>Deutschland</option>
				<option>Brasil</option>
				<option>more...</option>
			</select>
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
			<input type="tel" class="form-control" id="inputPhone" placeholder="Telefonnummer">
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
	</div>
<%--         campo CPF --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formInputBox idKey="register.cpfcnpj" labelKey="register.cpf" --%>
<%--                 path="cpfcnpj" inputCSS="text" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         campo RG/IE --%>
<!--         			<div class="f-row"> -->
<%--         				<formElement:formInputBox idKey="register.rgIe" labelKey="register.rg" path="rgIe" inputCSS="text" mandatory="false" hideInputErrorOnField="true" /> --%>
<!--         			</div> -->
<!-- 			campo email --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formInputBox idKey="register.email" labelKey="register.email" path="email" --%>
<%--                 inputCSS="text" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         campo data de nascimento --%>
<!--         <div class="f-row nascimento"> -->
<%--             <formElement:formInputBox idKey="register.birthday" labelKey="register.birthday" --%>
<%--                 path="birthday" inputCSS="text" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         campo sexo --%>
<!--         <div class="f-row sexo"> -->
<%--             <p class="label"><spring:theme code="text.fliegercommerce.texto33"/></p> --%>
<%--             <form:radiobutton cssClass="registerRadioFemale" idKey="register.female" path="gender" --%>
<%--                 value="FEMALE" label="Feminino" checked="true" hideInputErrorOnField="true" /> --%>
                
<%--             <form:radiobutton cssClass="registerRadioMale" idKey="register.male" path="gender" --%>
<%--                 value="MALE" label="Masculino" /> --%>
<!--         </div> -->
<%--         campo senha --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formPasswordBox idKey="password" labelKey="register.pwd" path="pwd" --%>
<%--                 inputCSS="text password strength" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         campo confirmar senha --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formPasswordBox idKey="register.checkPwd" labelKey="register.checkPwd" --%>
<%--                 path="checkPwd" inputCSS="text password" mandatory="true" --%>
<%--                 hideInputErrorOnField="true" /> --%>
<!--         </div> -->


<%--         mensagem de campo obrigatï¿½rio --%>
<%--         <small><spring:theme code="login.required.message" /></small> --%>
<%--         botï¿½o de cadastrar --%>
<%--         <ycommerce:testId code="register_Register_button"> --%>
<!--             <button type="submit" class="btn btn-registro"> -->
<%--                 <spring:theme code="text.fliegercommerce.texto74"/> --%>
<!--             </button> -->
<%--         </ycommerce:testId> --%>
    </form:form>
<%--     <form:form method="post" cssClass="pj" commandName="heringRegisterForm" action="${action}"> --%>
<%--         <form:hidden path="pessoaFisica" value="false" /> --%>
<!--         <div class="f-row"> -->
<%--             <small><spring:theme code="register.pj.message" /></small> --%>
<!--         </div> -->
<%--         campo CPF --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formInputBox idKey="register.cpfcnpj" labelKey="register.cnpj" --%>
<%--                 path="cpfcnpj" inputCSS="text" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         campo RG/IE --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formInputBox idKey="register.rgIe" labelKey="register.ie" path="rgIe" --%>
<%--                 inputCSS="text" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         campo Region of the IE --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formSelectBox idKey="register.state" labelKey="register.state" --%>
<%--                 items="${regions}" path="ufIe" allowEmpty="true" --%>
<%--                 itemValue="${useShortRegionIso ? 'isocodeShort' : 'isocode'}" /> --%>
<!--         </div> -->
<%--         campo nome --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formInputBox size="30" idKey="register.firstName" --%>
<%--                 labelKey="register.razaoSocial" path="firstName" inputCSS="text" mandatory="true" --%>
<%--                 hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         campo sobrenome --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formInputBox idKey="register.lastName" labelKey="register.nomeFantasia" --%>
<%--                 path="lastName" inputCSS="text" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         campo email --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formInputBox idKey="register.email" labelKey="register.email" path="email" --%>
<%--                 inputCSS="text" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         campo data de nascimento --%>
<!--         <div class="f-row nascimento"> -->
<%--             <formElement:formInputBox idKey="register.birthday" labelKey="register.dataFundacao" --%>
<%--                 path="birthday" inputCSS="text" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         campo senha --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formPasswordBox idKey="password" labelKey="register.pwd" path="pwd" --%>
<%--                 inputCSS="text password strength" mandatory="true" hideInputErrorOnField="true" /> --%>
<!--         </div> -->
<%--         campo confirmar senha --%>
<!--         <div class="f-row"> -->
<%--             <formElement:formPasswordBox idKey="register.checkPwd" labelKey="register.checkPwd" --%>
<%--                 path="checkPwd" inputCSS="text password" mandatory="true" --%>
<%--                 hideInputErrorOnField="true" /> --%>
<!--         </div> -->


<%--         mensagem de campo obrigatï¿½rio --%>
<%--         <small><spring:theme code="login.required.message" /></small> --%>
<%--         botï¿½o de cadastrar --%>
<%--         <ycommerce:testId code="register_Register_button"> --%>
<!--             <button type="submit" class="btn btn-registro"> -->
<%--                 <spring:theme code="text.fliegercommerce.texto74"/> --%>
<!--             </button> -->
<%--         </ycommerce:testId> --%>
<%--     </form:form> --%>
<!-- </section> -->
