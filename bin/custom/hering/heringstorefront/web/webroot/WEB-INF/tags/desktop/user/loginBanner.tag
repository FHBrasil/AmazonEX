<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>

<%-- LOGIN TAG FOR HERING STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<section class="col-3 column">
		<div class="banner">
			<c:choose>
				<c:when test="${themeName == 'dzarm'}">
					<img src="/store/_ui/desktop/theme-dzarm/images/DZM_Identificacao_foto.jpg" alt="DZARM">
				</c:when>
				<c:when test="${themeName == 'foryou'}">
					<img src="/store/_ui/desktop/theme-foryou/images/identificacao-banner.jpg" alt="Hering For You">
				</c:when>
				<c:otherwise>
					<img src="/store/_ui/desktop/theme-hering/images/identificacao-banner.jpg" alt="Cia. Hering">
				</c:otherwise>
			</c:choose>
			<p>Faça o seu cadastro e aproveite as vantagens em ser o nosso cliente.</p>
			<p><strong>Segurança:</strong> Site 100% seguro.</p>
		</div>
	</section>
</c:if>