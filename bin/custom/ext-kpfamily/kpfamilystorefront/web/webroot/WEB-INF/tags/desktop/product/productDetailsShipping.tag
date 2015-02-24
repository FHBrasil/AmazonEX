<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    

<div class="col-xs-8 v-bottom">
    <p>
        <b><spring:theme code="product.delivery.freitag.delivered" text="Am Freitag geliefert!"/></b>
        <span class="glyphicon glyphicon-stop text-onstock"></span>
        <br />
        <small>
            <spring:theme code="product.delivery.order.before.freitag"
                    text="Bestellen Sie innerhalb 5:17 Stunden"/>
            <br/>
            <spring:theme code="product.delivery.free.shipping.from.price"
                    text="Kostenlose Lieferung ab 40 &euro;"/>
        </small>
    </p>
</div>