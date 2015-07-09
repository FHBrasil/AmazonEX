<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="customer" required="true"
    type="de.hybris.platform.commercefacades.user.data.CustomerData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="panel-heading">
	<span class="glyphicon glyphicon-user"></span> 
	Konto ${customer.titleCode}
	<div class="pull-right">
		<small>
			<a href="#accountModal" data-toggle="modal">
				<span class="glyphicon glyphicon-cog"></span>
			</a>
		</small>
	</div>
</div>
<div class="panel-body">
	E-Mail: ${customer.uid} <br>
	Telefon: ${customer.defaultPhoneNumber}<br>
	Passwort: ********
</div>

<!-- accountModal HTML -->
    <div id="accountModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> Konto &auml;ndern</h4>
                </div>
                <div class="modal-body">
                    <form:form method="post">
						<!-- this form is exactly same like at index-register.html ... maybe we should make a component for that? -->
						<div class="form-group">
							<label for="inputEmail">E-Mail-Adresse</label>
							<input type="email" class="form-control" id="inputEmail" value="${customer.uid}" required="true">
						</div>
						 <div class="form-group">
							<label for="inputPassword">Passwort</label>
							<input type="password" class="form-control" id="password">
						</div> 
						<div class="form-group">
							<button type="submit" id="changeEmail" class="btn btn-primary" formaction="/my-account/update-email" formmethod="post">Speichern</button>
						</div>
					</form:form>
					

					<form:form id="changePhone" method="POST" action="/my-account/change-phonenumber" >						
						<div class="form-group">
						<label>Telefon (optional)</label>
						<input type="tel" name="phone" class="form-control" value="${customer.defaultPhoneNumber}"></input>
						</div>
						<div class="form-group">
							<button class="btn btn-primary">Speichern</button>
						</div>
					</form:form>
					
					<form:form id="updatePassword" method="GET" action="/my-account/update-password">
						<div class="form-group">
							<label >Passwort &auml;ndern</label><br />
							<p>Bitte klicken Sie den folgenden Button, wenn Sie Ihr Passwort zu &auml;ndern m&ouml;chten.</p>
							<button id="changePassword" class="btn btn-default" data-target="#passwordModal" data-toggle="modal">Passwort &auml;ndern</button>
						</div>
					</form:form>
					
					<form id="deleteAccount">
						<div class="form-group">
							<label >Konto l&ouml;schen</label><br />
							<p>Das L&ouml;schen Ihres Konto l&auml;sst sich nicht r&uuml;ckg&auml;ngig machen.</p>
							<button id="deleteAccount" class="btn btn-default" data-dismiss="modal" data-target="#deleteAccountModal" data-toggle="modal">Konto l&ouml;schen</button>
						</div>
					</form>	
					
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
                </div>
            </div>
        </div>
    </div>
    
<order:deleteAccountConfirmation customer="${customerData}"/>    
