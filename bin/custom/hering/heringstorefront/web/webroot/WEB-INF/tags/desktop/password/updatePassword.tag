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

<div aria-hidden="false" id="passwordModal" class="modal fade in">
        <div class="modal-dialog">
            <div class="modal-content">
				<form:form id="updatePasswordForm" action="/my-account/update-password" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> Passwort ändern</h4>
					</div>
					<div class="modal-body">
						<p>Füllen Sie folgendes Formular aus, um Ihr Passwort zu ändern:</p>
						
							<div class="form-group">
								<label for="inputPassword">Altes Passwort</label>
								<input class="form-control" id="inputPassword" name="currentPassword" required="true" type="password">
							</div>
							<div class="form-group">
								<label for="inputNewPassword">Neues Passwort</label>
								<input class="form-control" id="inputNewPassword" name="newPassword" required="true" type="password">
							</div>
							<div class="form-group">
								<label for="inputNewPasswordVerify">Neues Passwort widerholen</label>
								<input class="form-control" id="inputNewPasswordVerify" name="checkNewPassword" required="true" type="password">
							</div>
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
						<button type="submit" class="btn btn-primary" formaction="/my-account/update-password">Passwort ändern</button>
					</div>
				</form:form>
            </div>
        </div>
    </div>