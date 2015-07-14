<%@ taglib prefix="spring"		uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce"	uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="format" 		tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" 		uri="http://www.springframework.org/tags/form"%>

<div class="col-sm-4">
	<%-- RENDER ONLY if e-mail-address not has customer account --%>
	<c:if test="${orderData.guestCustomer && !accountCreatedSucess}">
		<h2>
			<spring:theme code="text.fliegercommerce.texto157" />&nbsp;
			[FIXED]<span class="fox150706">+840</span>&nbsp;
			<spring:theme code="text.fliegercommerce.texto158" />
		</h2>
		<p>
			<spring:theme code="text.fliegercommerce.texto159"/>&nbsp;
			<b><spring:theme code="text.fliegercommerce.texto160"/>&nbsp;[FIXED]+840&nbsp;<spring:theme code="text.fliegercommerce.texto161"/></b>,&nbsp;
			<spring:theme code="text.fliegercommerce.texto162"/>
		</p>
		<p><spring:theme code="text.fliegercommerce.texto163"/></p>
		<div class="panel panel-default panel-secure150203">
			<div class="panel-body">
				<form:form id="formRegisterGuest" commandName="guestRegisterForm" method="POST">
					<form:hidden path="uid"/>
					<div class="form-group">
						<label for="inputEmail"><spring:theme code="text.fliegercommerce.texto164"/></label>
						<input type="email" class="form-control" id="inputEmail" value="${email}" readonly="true">
					</div>
					<div class="form-group">						
						<formElement:formPasswordBox labelKey="text.fliegercommerce.texto165" idKey="pwd" 
							inputCSS="form-control" path="pwd" placeholder="Passwort" mandatory="true"/>
					</div>
					<div class="form-group">
						<formElement:formPasswordBox labelKey="text.fliegercommerce.texto166" idKey="checkPwd" 
							inputCSS="form-control" path="checkPwd" placeholder="Passwort widerholen" mandatory="true"/>
					</div>				
					<%-- RENDER ONLY if user not has opt-in --%>
					<div class="checkbox">
						<label><input type="checkbox" checked="true"><spring:theme code="text.fliegercommerce.texto167" />*</label>
					</div>
					<%-- END RENDER ONLY --%>
					<button type="submit" class="btn btn-primary">[FIXED]+840&nbsp;<spring:theme code="text.fliegercommerce.texto168" /></button>
				</form:form>
			</div>
		</div>
		<%-- RENDER ONLY if user not has opt-in --%>
		<p class="small margin-top text-muted">* Meine E-Mail-Adresse wird nicht an Dritte weitergegeben. Diese Einwilligung zur Speicherung und Nutzung meiner E-Mail-Adresse f&uuml;r Werbezwecke kann ich jederzeit mit Wirkung f&uuml;r die Zukunft widerrufen, ohne dass hierf&uuml;r andere als die &Uuml;bermittlungskosten nach den Basistarifen entstehen. Datenschutzhinweise</p>
		<%-- END RENDER ONLY --%>
		<%-- END RENDER ONLY --%>		
	</c:if>
	<c:if test="${accountCreatedSucess}">
		<%-- RENDER IF form sent sucessfully --%>
		<h2>Konto wurde erstellt</h2>
		<p><b>+840 Punkte</b> wurden auf das Konto ${email} gutgeschrieben. Loggen Sie sich einfach bei der n&auml;chsten Bestellung mit E-Mail und Passwort ein und sparen Sie bei jedem Einkauf!</p>
		<%-- END RENDER IF --%>
	</c:if>
	<!-- RENDER IF (customer has account) and (customer not has birthdate) -->
	<h2>Newsletter f&uuml;r Philipp</h2>
	<p>Abonnieren Sie unseren personalisierten Newsletter mit interessanten Infos zu Ihrer SSW und passenden Tipps zum Alter Ihres Kindes. Selbstverst&auml;ndlich jederzeit abbestellbar:</p>
	<div class="panel panel-default panel-secure150203">
		<div class="panel-body">
			<form>
				<div class="form-group">
					<label for="inputEmail">E-Mail</label>
					<input type="email" class="form-control" id="inputEmail" value="phil@oidudniudd.de">
				</div>
				<div class="form-group">
					<label for="birthday" class="control-label">Entbindungstermin / Geburtstag</label>
					<div class="input-group input-append date" id="birthday" data-date="01.01.2015" data-date-format="dd.mm.yyyy">
						<input type="text" size="16" class="form-control span2" value="01.01.2015">
						<span class="input-group-btn add-on">
							<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-calendar"></span></button>
						</span>
					</div>	
				</div>					
				<button type="submit" class="btn btn-primary">Kostenlos abonnieren</button>
			</form>
		</div>
	</div>
	<p class="small margin-top text-muted">* Meine E-Mail-Adresse wird nicht an Dritte weitergegeben. Diese Einwilligung zur Speicherung und Nutzung meiner E-Mail-Adresse f&uuml;r Werbezwecke kann ich jederzeit mit Wirkung f&uuml;r die Zukunft widerrufen, ohne dass hierf&uuml;r andere als die &Uuml;bermittlungskosten nach den Basistarifen entstehen. Datenschutzhinweise</p>
	<!-- END RENDER IF -->
	<!-- RENDER IF form sent sucessfully -->
	<h2>Newsletter abonniert</h2>
	<p>Super! Sie erhalten ab sofort w&ouml;chentlich unseren Newsletter!</p>
	<!-- END RENDER IF -->
	<!-- RENDER IF (customer has account) and (customer has birthdate) -->
	<h2>Werde Teil unserer Community!</h2>
	<p>Verbinde dich mit unseren sozialen Netzwerken, erhalte News und Infos rund ums Baby und werde Teil einer Gemeinschaft stolzer Eltern!</p>
	<p class="social150121 becomesocial margin-top">
		<a href="#"><span class="babicon babicon-facebook"> </span></a>
		<a href="#"><span class="babicon babicon-google"> </span></a>
		<a href="#"><span class="babicon babicon-twitter"> </span></a>
		<a href="#"><span class="babicon babicon-wordpress"> </span></a>
		<a href="#"><span class="babicon babicon-youtube"> </span></a>
		<a href="#"><span class="babicon babicon-instagram"> </span></a>
		<a href="#"><span class="babicon babicon-pinterest"> </span></a>
	</p>

	<!-- END RENDER IF -->
</div>