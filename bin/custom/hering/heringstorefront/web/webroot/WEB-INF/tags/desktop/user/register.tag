<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="newsletter" tagdir="/WEB-INF/tags/desktop/newsletter"%>
<section class="col-1 column">
    <%-- cabe�alho do formul�rio --%>
    <header>
        <h2>
            <spring:theme code="register.new.customer" />
        </h2>
    </header>
    <form class="f-row pfpj">
        <label><input type="radio" id="radiopf" name="tipo" ${pf ? 'checked' : ''}><spring:theme code="text.fliegercommerce.texto31"/> </label> <label><input type="radio" id="radiopj" name="tipo"
            ${!pf ? 'checked' : ''}><spring:theme code="text.fliegercommerce.texto32"/></label>
    </form>
    <form:form method="post" cssClass="pf" commandName="heringRegisterForm" action="${action}">
        <form:hidden path="pessoaFisica" value="true" />
        <%-- campo CPF --%>
        <div class="f-row">
            <formElement:formInputBox idKey="register.cpfcnpj" labelKey="register.cpf"
                path="cpfcnpj" inputCSS="text" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- campo RG/IE --%>
        <!-- 			<div class="f-row"> -->
        <%-- 				<formElement:formInputBox idKey="register.rgIe" labelKey="register.rg" path="rgIe" inputCSS="text" mandatory="false" hideInputErrorOnField="true" /> --%>
        <!-- 			</div> -->
        <%-- campo nome --%>
        <div class="f-row">
            <formElement:formInputBox size="30" idKey="register.firstName"
                labelKey="register.firstName" path="firstName" inputCSS="text required-letters"
                mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- campo sobrenome --%>
        <div class="f-row">
            <formElement:formInputBox idKey="register.lastName" labelKey="register.lastName"
                path="lastName" inputCSS="text required-letters" mandatory="true"
                hideInputErrorOnField="true" />
        </div>
        <%-- campo email --%>
        <div class="f-row">
            <formElement:formInputBox idKey="register.email" labelKey="register.email" path="email"
                inputCSS="text" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- campo data de nascimento --%>
        <div class="f-row nascimento">
            <formElement:formInputBox idKey="register.birthday" labelKey="register.birthday"
                path="birthday" inputCSS="text" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- campo sexo --%>
        <div class="f-row sexo">
            <p class="label"><spring:theme code="text.fliegercommerce.texto33"/></p>
            <form:radiobutton cssClass="registerRadioFemale" idKey="register.female" path="gender"
                value="FEMALE" label="Feminino" checked="true" hideInputErrorOnField="true" />
                
            <form:radiobutton cssClass="registerRadioMale" idKey="register.male" path="gender"
                value="MALE" label="Masculino" />
        </div>
        <%-- campo senha --%>
        <div class="f-row">
            <formElement:formPasswordBox idKey="password" labelKey="register.pwd" path="pwd"
                inputCSS="text password strength" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- campo confirmar senha --%>
        <div class="f-row">
            <formElement:formPasswordBox idKey="register.checkPwd" labelKey="register.checkPwd"
                path="checkPwd" inputCSS="text password" mandatory="true"
                hideInputErrorOnField="true" />
        </div>
        <%-- campo confirmar newsletter --%>
        <!-- 			<div class="f-row"> -->
        <%-- 				<formElement:formCheckbox idKey="register.subscribeNewsletter" labelKey="profile.subscribeNewsletter.hering" path="subscribeNewsletter" labelCSS="text" mandatory="false"/> --%>
        <!-- 			</div> -->
        <%-- campo confirmar newsletter --%>
        <div class="f-row">
            <newsletter:newsletters bases="${basesCode}" basesChecked="${basesChecked}" id="pf" />
        </div>
        <%-- mensagem de campo obrigat�rio --%>
        <small><spring:theme code="login.required.message" /></small>
        <%-- bot�o de cadastrar --%>
        <ycommerce:testId code="register_Register_button">
            <button type="submit" class="btn btn-registro">
                <spring:theme code="text.fliegercommerce.texto74"/>
            </button>
        </ycommerce:testId>
    </form:form>
    <form:form method="post" cssClass="pj" commandName="heringRegisterForm" action="${action}">
        <form:hidden path="pessoaFisica" value="false" />
        <div class="f-row">
            <small><spring:theme code="register.pj.message" /></small>
        </div>
        <%-- campo CPF --%>
        <div class="f-row">
            <formElement:formInputBox idKey="register.cpfcnpj" labelKey="register.cnpj"
                path="cpfcnpj" inputCSS="text" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- campo RG/IE --%>
        <div class="f-row">
            <formElement:formInputBox idKey="register.rgIe" labelKey="register.ie" path="rgIe"
                inputCSS="text" hideInputErrorOnField="true" />
        </div>
        <%-- campo Region of the IE --%>
        <div class="f-row">
            <formElement:formSelectBox idKey="register.state" labelKey="register.state"
                items="${regions}" path="ufIe" allowEmpty="true"
                itemValue="${useShortRegionIso ? 'isocodeShort' : 'isocode'}" />
        </div>
        <%-- campo nome --%>
        <div class="f-row">
            <formElement:formInputBox size="30" idKey="register.firstName"
                labelKey="register.razaoSocial" path="firstName" inputCSS="text" mandatory="true"
                hideInputErrorOnField="true" />
        </div>
        <%-- campo sobrenome --%>
        <div class="f-row">
            <formElement:formInputBox idKey="register.lastName" labelKey="register.nomeFantasia"
                path="lastName" inputCSS="text" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- campo email --%>
        <div class="f-row">
            <formElement:formInputBox idKey="register.email" labelKey="register.email" path="email"
                inputCSS="text" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- campo data de nascimento --%>
        <div class="f-row nascimento">
            <formElement:formInputBox idKey="register.birthday" labelKey="register.dataFundacao"
                path="birthday" inputCSS="text" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- campo senha --%>
        <div class="f-row">
            <formElement:formPasswordBox idKey="password" labelKey="register.pwd" path="pwd"
                inputCSS="text password strength" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- campo confirmar senha --%>
        <div class="f-row">
            <formElement:formPasswordBox idKey="register.checkPwd" labelKey="register.checkPwd"
                path="checkPwd" inputCSS="text password" mandatory="true"
                hideInputErrorOnField="true" />
        </div>
        <%-- campo confirmar newsletter --%>
        <!-- 			<div class="f-row"> -->
        <%-- 				<formElement:formCheckbox idKey="register.subscribeNewsletter" labelKey="profile.subscribeNewsletter.hering" path="subscribeNewsletter" labelCSS="text" mandatory="false"/> --%>
        <!-- 			</div> -->
        <%-- campo confirmar newsletter --%>
        <div class="f-row">
            <newsletter:newsletters bases="${basesCode}" basesChecked="${basesChecked}" id="pj" />
        </div>
        <%-- mensagem de campo obrigat�rio --%>
        <small><spring:theme code="login.required.message" /></small>
        <%-- bot�o de cadastrar --%>
        <ycommerce:testId code="register_Register_button">
            <button type="submit" class="btn btn-registro">
                <spring:theme code="text.fliegercommerce.texto74"/>
            </button>
        </ycommerce:testId>
    </form:form>
</section>
