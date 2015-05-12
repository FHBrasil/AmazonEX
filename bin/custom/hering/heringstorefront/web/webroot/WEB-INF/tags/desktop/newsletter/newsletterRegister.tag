<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="formElement"	tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form action="${request.contextPath}/newsletter/newsletter-register-dzarm" method="POST">
	<input type="hidden" name="baseStore" value="dzarm" />
	<h4>
		<spring:theme code="newsletter.title" text="ACOMPANHE A DZARM" />
	</h4>
	<fieldset>
		<span class="opSexo"> 
			<label for="Feminino">
				<spring:theme code="newsletter.female" text="Feminino" />
				<input type="radio" id="Feminino" value="FEMALE" name="opcaoSexo" checked="checked" />
			</label>
			<label for="Masculino">
				<spring:theme code="newsletter.male" text="Masculino" />
				<input type="radio"	id="Masculino" value="MALE" name="opcaoSexo" />
			</label>
		</span>
		
		<input type="text" placeholder="Digite seu nome" name="name" id="name_newsletter_footer"/>
		<input type="email" name="email" placeholder="Digite seu e-mail" class="emailaddress"/>
		
		<button class="positive submit_form" type="submit">
			<spring:theme code="newsletter.register" text="Cadastrar" />
		</button>

		<c:if test="${newsletterregistration}">
			
			<div class="lightbox_colorbox">
			<div class="lightbox-overflow"></div>
			<div class="popup-newsletter lightbox-box">
			  <div class="lightbox-wrap">
			   
			    <div class="lightbox-content">
			     <div class="lightbox-image">
			     <c:choose>
			     <c:when test="${newsletterregistrationnegative}">
			     	<img src="${commonResourcePath}/images/popup-newsletter-jaRegistrado.jpg" width="478px" height="270px" />
			     </c:when>
			     <c:otherwise>
			     	<img src="${commonResourcePath}/images/popup-newsletter.jpg" width="478px" height="270px" />
			     </c:otherwise>
			     </c:choose>
			     	
			     </div>
			 		
			    </div>
			  </div>
			</div>
			</div>
		</c:if>
		<div class="description">
			<spring:theme code="newsletter.msg" text="Cadastre-se e fique por dentro das novidades da dzarm." />
		</div>
	</fieldset>
</form:form>