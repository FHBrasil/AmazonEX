<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="formElement"	tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="name" value="${user.firstName} ${user.lastName}" />
<c:if test="${themeName == 'black'}">
	<style>
	
	.button-add {
		width: 95px !important; 
		height: 22px !important; 
		float: right; 
		margin: 3px 0 0 0; 
		line-height: 10% !important
	}
	
	.form-avise {padding: 0 0 10px 0;}
	
	.input-t2 {width: 136px; height: 16px;}
	
	</style>
	
	<form:form action="${request.contextPath}/notifyme/notifyme-register" method="POST" class="form-avise">
		<input type="hidden" name="code" value="${product.code}" />
		<input type="hidden" name="baseStore" value="dzarm" />
		<div class="product-code">
			<spring:theme code="notifyme.descriptionDays" />
		</div>
		<input type="text" placeholder="Digite seu nome" name="nameNotify" class="nameaddressnotify input-t2" id="name_notify" value="${name!='Anonymous '? name: ''}" />
		<input type="email" name="emailNotify" placeholder="Digite seu e-mail" class="emailaddressnotify input-t2" value="${user.firstName!='Anonymous'?user.uid : ''}"/>
		<button class="positive submit_form_notify button-add" type="submit">
			<spring:theme code="notifyme.notify" text="Notify me" />
		</button>
		<div class="radioDays">
			<div class="product-code">
				<input type="radio" name="daysNotify" value="7" id="days7"><label for="days7"><spring:theme code="notifyme.7days" /></label>
				<input type="radio" name="daysNotify" value="14" id="days14"><label for="days14"><spring:theme code="notifyme.14days" /></label>
				<input type="radio" name="daysNotify" value="21" id="days21" checked="checked"><label for="days21"><spring:theme code="notifyme.21days" /></label>
			</div>
		</div>
		
		<c:if test="${notifymeregistration}">
			<div class="lightbox_colorbox">
				<div class="lightbox-overflow"></div>
				<div class="popup-newsletter lightbox-box">
					<div class="lightbox-wrap">
						<div class="lightbox-content">
							<div class="lightbox-image">
				     			<img src="${commonResourcePath}/images/popup-notifyme.jpg" width="478px" height="270px" />
				     		</div>
				     	</div>
				     </div>
				</div>
			</div>
		</c:if>
	</form:form>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<form:form class="esgotado-form" action="${request.contextPath}/notifyme/notifyme-register" method="POST">
		<input type="hidden" name="code" value="${product.code}" />
		<input type="hidden" name="baseStore" value="${themeName}" />
		<h3><spring:theme code="notifyme.whenAvailableHering" /></h3>
		<input type="text" data-placefocus="Nome" name="nameNotify" id="name_notify" value="${name!='Anonymous '? name: ''}" />
		<input type="email" data-placefocus="E-mail" name="emailNotify" value="${user.firstName!='Anonymous'?user.uid : ''}" />
		<div class="dias">
			<label><input type="radio" name="daysNotify" value="7" id="days7" checked><spring:theme code="notifyme.7daysHering" /></label>
			<label><input type="radio" name="daysNotify" value="14" id="days14"><spring:theme code="notifyme.14daysHering" /></label>
			<label><input type="radio" name="daysNotify" value="21" id="days21"><spring:theme code="notifyme.21daysHering" /></label>
		</div>
		<c:set value="${themeName eq 'foryou' ? 'btn btn-big add' : 'btn btn-square btn-transparent-blue-border'}" var="classBotao" />
		<button class="${classBotao}"><spring:theme code="notifyme.confirmRequestHering" text="Confirm Request" /></button>
	</form:form>
</c:if>