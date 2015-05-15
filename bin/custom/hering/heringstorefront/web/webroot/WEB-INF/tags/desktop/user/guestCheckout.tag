<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<section>
    <header>
        <h2>
            <spring:theme code="guest.checkout" />
        </h2>
    </header>
    <form:form action="${action}" method="post" commandName="heringGuestForm">
        <%-- email --%>
        <div class="f-row">
            <formElement:formInputBox idKey="guest.email" labelKey="guest.email" path="email"
                inputCSS="text" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- cpf --%>
        <div class="f-row">
            <formElement:formInputBox idKey="guest.cpfcnpj" labelKey="guest.cpfcnpj" path="cpfcnpj"
                inputCSS="text" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- data de nascimento --%>
        <div class="f-row nascimento">
            <formElement:formInputBox idKey="register.birthday" labelKey="register.birthday"
                path="birthday" inputCSS="text" mandatory="true" hideInputErrorOnField="true" />
        </div>
        <%-- sexo --%>
        <div class="f-row sexo">
            <p class="label">Sexo</p>
            <form:radiobutton cssClass="registerRadioFemale" idKey="guest.gender"
                labelKey="guest.gender" path="gender" value="FEMALE" checked="true" />
            <spring:theme code="guest.female" text="Feminino" />
            <form:radiobutton cssClass="registerRadioMale" idKey="guest.gender"
                labelKey="guest.gender" path="gender" value="MALE" />
            <spring:theme code="guest.male" text="Masculino" />
        </div>
        <%-- requerido --%>
        <small><spring:theme code="form.required" /></small>
        <button class="btn btn-registro">Continuar como visitante</button>
    </form:form>
</section>
