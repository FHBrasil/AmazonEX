<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="showPotentialPromotions" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<ol class="list150608">
	<c:set var="sharp" value='#'/>	
	<c:forEach items="${cartData.entries}" var="entry">
    	<li class="item150608 itemCheckout" id="${entry.entryNumber}">
        	<div class="row">
        		<div class="col-xxs-4 col-xs-12 col-sm-4 text-right">
        			<a href="${entry.product.url}" class="image150608"><product:productPrimaryImage product="${entry.product}" format="cartIcon" /></a>
        		</div>
        		<div class="col-xxs-8 col-xs-12 col-sm-8">
	        		<a href="${entry.product.url}"><b>[BRAND]</b>&nbsp;${entry.product.name}</a>
					<br /><small>${sharp}${entry.product.code}</small>
				</div>
        		<%--        		
        			<product:productTitle product="${entry.product}" cart="true"/>
        			 [FIXED] for the time being 
        			<c:if test="${status.index == 0}">
        				<button type="button" class="btn btn-link btn-xs" data-toggle="popover-bulk"><span class="glyphicon glyphicon glyphicon-warning-sign"></span> Sperrgut</button>
        			</c:if>
        			<c:if test="${status.index == 1}">
        				<button type="button" class="btn btn-link btn-xs" data-toggle="popover-forwarding"><span class="glyphicon glyphicon glyphicon-road"></span> Speditionslieferung</button>
        			</c:if>
        		 --%>
        	</div>
        	<div class="row itemdata150608">
        		<%-- QUANTITY PRODUCT --%>
        		<div class="col-xs-4 col-sm-3">
        			<c:url value="/checkout/amazon/update" var="cartUpdateFormAction" />
                    <form:form id="updateCartForm${entry.entryNumber}" action="${cartUpdateFormAction}" method="post"
                    	commandName="updateQuantityForm${entry.entryNumber}">
                    	<input type="hidden" name="entryNumber" value="${entry.entryNumber}" />
                        <input type="hidden" name="productCode" value="${entry.product.code}" />
                        <input type="hidden" name="initialQuantity" value="${entry.quantity}" />
                        <input type="hidden" name="initialQuantity_${entry.entryNumber}" id="initialQuantity_${entry.entryNumber}" value="${entry.quantity}" />
                        <ycommerce:testId code="cart_product_quantity">
                            <form:input disabled="${not entry.updateable}" type="number" size="1"
                                id="quantity${entry.entryNumber}" class="qty" path="quantity"
                                min="1" maxlength="3" required="required" step="1" cssClass="form-control"/>
                        </ycommerce:testId>
                    </form:form>
        		</div>
        		<%-- REMOVE PRODUCT --%>
        		<div class="col-sm-3 hidden-xs">
        			<c:if test="${entry.updateable}">
                    	<ycommerce:testId code="cart_product_removeProduct">                    		
                            <a href="#" class="btn-excluir-produto"
                                id="RemoveProduct_${entry.entryNumber}" class="submitRemoveProduct"
                                title="Remover"
                                onClick="babyartikel.checkoutAmazon.removeProduct('${entry.entryNumber}');">
                                <span class="glyphicon glyphicon-remove-sign"></span>
                                <spring:theme code="basket.removeProduct" />                                
                            </a>
                        </ycommerce:testId>
                	</c:if>
        		</div>
        		<%-- PRICE UNIT --%>
        		<div class="col-xs-4 col-sm-3">
        			<format:fromPrice priceData="${entry.basePrice}" />
        		</div>
        		<%-- TOTAL PRICE --%>
        		<div class="col-xs-4 col-sm-3 text-right">
        			<format:price priceData="${entry.totalPrice}" displayFreeForZero="true" />
        		</div>
            </div>                           
    	</li>
		<%-- Item Express Shipping?
		<li id="toggleDelivery" class="delivery150618 collapse out">
			<small><span class="stock150619 onstock big"><b><spring:theme code="checkout.single.expressShipping"/></b> Lieferung Morgen, 17.08.2015</span></small>
		</li> --%>
	</c:forEach>
	<%-- <li class="delivery150618">
		<small><span class="stock150619 nostock big">Lieferung am Freitag, 17.08.2015</span></small>
	</li> --%>
</ol>
<%-- If has items express shipping 
<div class="checkbox" style="margin-top:-15px;margin-left:-5px;">
	<label><input type="checkbox" data-toggle="collapse" data-target="#toggleDelivery,.itemdelivery160608"><spring:theme code="checkout.single.sendPositionsImmediately"/>&nbsp;(<format:price priceData="${cartData.deliveryCost}" displayFreeForZero="TRUE" />)</label>
</div>--%>