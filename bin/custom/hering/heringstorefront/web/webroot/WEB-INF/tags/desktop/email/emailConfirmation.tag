<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div aria-hidden="false" id="emailModal" class="modal fade in">
        <div class="modal-dialog">
            <div class="modal-content">
				<form:form id="updateEmailForm" action="/my-account/update-email" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> E-Mail-Adresse ändern</h4>
					</div>
					<div class="modal-body">
						<p>Füllen Sie folgendes Formular aus, um Ihre E-Mail-Adresse zu ändern:</p>
						
							<div class="form-group">
								<label for="inputNewEmail">Neue E-Mail-Adresse</label>
								<input class="form-control" id="inputNewEmail" name="email" required="true" type="email">
							</div>
							<div class="form-group">
								<label for="inputNewEmailVerify">Neue E-Mail-Adresse wiederholen</label>
								<input class="form-control" id="inputNewEmailVerify" name="chkEmail" required="true" type="email" autocomplete="off">
							</div>
							<div class="form-group">
								<label for="inputPassword">Passwort</label>
								<input class="form-control" id="inputPassword" name="password" required="true" type="password">
							</div>
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
						<button type="submit" class="btn btn-primary" formaction="/my-account/update-email">E-Mail-Adresse ändern</button>
					</div>
				</form:form>
            </div>
        </div>
    </div>