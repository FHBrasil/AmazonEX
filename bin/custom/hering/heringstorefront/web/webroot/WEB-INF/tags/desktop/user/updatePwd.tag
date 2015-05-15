<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<cms:pageSlot position="SideContent" var="feature" element="div"
    class="span-4 side-content-slot cms_disp-img_slot">
    <cms:component component="${feature}" />
</cms:pageSlot>
<section class="account-edit-password page with-sidebar">
    <section id="acc-password-form">
        <header>
            <h2>Alterar Senha</h2>
        </header>
        <form:form method="post" commandName="updatePwdForm">
            <div class="form-row">
                <formElement:formPasswordBox idKey="updatePwd-pwd" labelKey="updatePwd.pwd"
                    path="pwd" inputCSS="text password strength" mandatory="true" />
            </div>
            <div class="form-row">
                <formElement:formPasswordBox idKey="updatePwd.checkPwd"
                    labelKey="updatePwd.checkPwd" path="checkPwd" inputCSS="text password"
                    mandatory="true" errorPath="updatePwdForm" />
            </div>
            <p class="required-info">
                <spring:theme code="form.required" text="Fields marked * are required" />
            </p>
            <div class="btn-group">
                <ycommerce:testId code="update_update_button">
                    <button class="btn btn-confirmar">
                        <spring:theme code="updatePwd.submit" />
                    </button>
                </ycommerce:testId>
            </div>
        </form:form>
    </section>
</section>
