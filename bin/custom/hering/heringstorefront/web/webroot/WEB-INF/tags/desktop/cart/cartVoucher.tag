<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<%-- VOUCHER FOR DZARM STORE --%>
<c:if test="${themeName == 'black'}">
	<c:if test="${not hasAppliedVoucher}">
		<form:form action="/store/pt/cart/redeemVoucher" method="get">
			<div class="item_container">
				<input type="text" name="voucherCode" value="DIGITE AQUI SEU CUPOM DE DESCONTO/VALE CREDITO" style="color:#aaa" onfocus="this.value='';this.style.color='#000';this.onfocus='';" placeholder="DIGITE AQUI SEU CUPOM DE DESCONTO/VALE CREDITO"/>
				
				<button class="positive" type="submit">
					<spring:theme code="voucher.calculate" />
				</button>
			</div>
		</form:form>
	</c:if>
	<c:if test="${hasAppliedVoucher}">
		<form:form action="/store/pt/cart/releaseVoucher" method="get">
			<div class="item_container">
				<button class="positive" type="submit">
					<spring:theme code="voucher.remove" />
				</button>
			</div>
		</form:form>
	</c:if>
</c:if>

<%-- VOUCHER FOR HERING STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">	
	
	<%-- caso NAO tenha sido carregado o voucher --%>
	<c:if test="${not hasAppliedVoucher}">
		<form:form action="/store/pt/cart/redeemVoucher" method="get" class="discount-coupon">
			<h3>Cupom de Desconto</h3>
			<div>
				<label>Digite o código:</label>
				<input type="text" name="voucherCode" id="cupom-desconto" required/>
				<button class="btn-ok btn" type="submit">
					<spring:theme code="voucher.calculate" />
				</button>
			</div>
		</form:form>
	</c:if>
	
	<%-- caso tenha sido carregado o voucher --%>
	<c:if test="${hasAppliedVoucher}">
		<form:form action="/store/pt/cart/releaseVoucher" method="get">
			<h3>Cupom de Desconto</h3>
			<div>
				<button class="btn-ok btn" type="submit">
					<spring:theme code="voucher.remove" />
				</button>
			</div>
		</form:form>
	</c:if>
	
</c:if>