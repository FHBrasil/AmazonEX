<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="col-sm-6" style="width:100%;">
    <div class="row">
        <p class="col-xs-4">
            <a href="#">
                <b class="fox24150217">+1496 Punkte</b>
                <small class="hidden-xs hidden-sm">
                    <br />
                    <spring:theme code="text.purchase.point.collect.save"
                            text="Sammeln und beim n&auml;chsten Einkauf sparen!"/>
                </small>
            </a>
        </p>
        <p class="col-xs-8">
            <a href="#">
                <b><spring:theme code="text.installment.from.price"
                        text="Ratenzahlung ab 12,90 &euro;"/></b>
                <small class="hidden-xs hidden-sm">
                    <br/>
                    <spring:theme code="text.installment.with.klarna"
                            text="mit unserem Partner Klarna"/>
                    <br/>
                    <spring:theme code="text.installment.find.out.more.purchase"
                            text="Mehr zum Thema Ratenkauf"/>
                </small>
            </a>
        </p>
    </div>
</div>