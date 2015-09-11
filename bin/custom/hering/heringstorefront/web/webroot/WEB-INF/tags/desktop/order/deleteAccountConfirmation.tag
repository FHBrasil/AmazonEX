<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="customer" required="true"
    type="de.hybris.platform.commercefacades.user.data.CustomerData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="deleteAccountModal" class="modal fade in">
        <div class="modal-dialog">
            <div class="modal-content text-danger">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title"><span class="glyphicon glyphicon-remove"></span> Konto löschen</h4>
                </div>
                <div class="modal-body">
                    <p>Sie sind dabei, Ihr Kundenkonto zu löschen. Sie haben dann keinen Zugriff mehr auf das Konto und damit verknüpften Daten und Informationen. Der Vorgang lässt sich nicht rückgängig machen.</p>
					<p>Sind Sie sicher, dass Sie Ihr Konto löschen möchten?</p>
                </div>               
                <div class="modal-footer">
                <form:form method="get">
                	<button type="button" class="btn btn-primary" data-dismiss="modal">Abbrechen</button>
                    <button type="submit" class="btn btn-default" formaction="/my-account/delete-account">Konto löschen</button>
                </form:form>
                    
                </div>
                
            </div>
        </div>
</div>