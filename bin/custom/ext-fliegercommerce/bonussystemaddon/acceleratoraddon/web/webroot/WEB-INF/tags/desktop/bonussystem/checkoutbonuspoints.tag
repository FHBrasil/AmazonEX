<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>

<form >
	<section id="other-options">
		<label><input type="checkbox" id="points-option-label" name="metodo_pagamento_opcional" value="points"> <spring:theme code="Utilizar meus pontos"/></label>
		<div class="points-option-field">
			<div class="f-row">
				<label for="cartao-presente">Quantos pontos gostaria de utilizar?</label>
				<p>(100 pontos = R$ 1,00)</p>
				<p>Saldo de pontos atual: ${availableBonusPoints} (R$ <fmt:formatNumber minFractionDigits="2" value="${availableBonusPoints / 100}" />)</p>
			<formElement:formInputBox idKey="points"
				labelKey="" path="points"
				inputCSS="text required-numbers"
				mandatory="false"/>
			<input type="hidden" name="applyBPUrl"
					value="${request.contextPath}/bs/apply-bp/" />
			<a href="#" class="btn applyBP">
				<spring:theme code="checkout.single.payment.useBonusPoints"/>
			</a>
			<label id="bonusPointsUsedLabel">Desconto atual: <fmt:formatNumber minFractionDigits="1" value="${usedBonusPoints}"/> pontos = R$ <fmt:formatNumber minFractionDigits="2" value="${usedBonusPoints / 100}" /></label>
			</div>
		</div>
	</section>
</form>