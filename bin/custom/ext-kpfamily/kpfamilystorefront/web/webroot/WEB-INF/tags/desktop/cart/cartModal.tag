<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- BEGIN Modal HTML (This part should be loaded asynchronous until golive --%>
<%-- Maybe load this modal with fragment/json? --%>
<div id="cartModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    <spring:theme code="popup.cart.modal.added"
                        text="Gute Wahl! Dein Artikel liegt jetzt im Warenkorb"/>
                </h4>
            </div>
            <div class="modal-body">
                <p>
                    <spring:theme code="popup.cart.modal.question.next"
                            text="Was möchtest du als nächstes tun?"/>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <spring:theme code="popup.cart.modal.continue.shopping" text="Weiter shoppen"/>
                </button>
                <button type="button" class="btn btn-primary">
                    <spring:theme code="popup.cart.modal.to.cart" text="Zum Warenkorb"/>
                </button>
            </div>
        </div>
    </div>
</div>